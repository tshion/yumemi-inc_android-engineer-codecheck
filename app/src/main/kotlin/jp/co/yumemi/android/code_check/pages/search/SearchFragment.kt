package jp.co.yumemi.android.code_check.pages.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import jp.co.yumemi.android.code_check.NavGraphEntryPointDirections
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.PageSearchBinding
import jp.co.yumemi.android.code_check.organisms.repository_list_view.RepositoryListViewAdapter
import jp.co.yumemi.android.code_check.pages.detail.DetailViewData
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

/**
 * 検索画面
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
class SearchFragment : Fragment(R.layout.page_search) {

    private var binding: PageSearchBinding? = null

    private val viewModel by viewModels<SearchViewModel> { SearchViewModel.Factory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageSearchBinding.bind(view)

        // region 表示設定
        binding?.pageSearchHeader?.setupWith(findNavController())

        binding?.pageSearchBoxLayout?.setEndIconOnClickListener {
            binding?.apply {
                pageSearchBoxEditor.text = null
                pageSearchList.adapter?.submitList(null)
                pageSearchListEmpty.isVisible = false
            }
        }

        binding?.pageSearchBoxEditor?.setOnEditorActionListener { v, actionId, _ ->
            val shouldConsume = actionId == EditorInfo.IME_ACTION_SEARCH
            if (shouldConsume) {
                // キーボードを隠す
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE)
                    ?.let { it as? InputMethodManager }
                    ?.hideSoftInputFromWindow(v.windowToken, 0)

                // 検索
                viewModel.search(v.text.toString())
            }
            shouldConsume
        }

        binding?.pageSearchList?.adapter = RepositoryListViewAdapter {
            val action = SearchFragmentDirections.navGoDetail(DetailViewData.parse(it, resources))
            findNavController().navigate(action)
        }
        // endregion


        // ViewModel と表示の結びつけ
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.error.filterNotNull().collect { e ->
                        when (e) {
                            is IllegalArgumentException,
                            is IndexOutOfBoundsException -> NavGraphEntryPointDirections.navShowNotifyDialog(
                                title = getString(R.string.page_search_error_title_invalid),
                                message = getString(R.string.page_search_error_message_invalid),
                            )

                            is IOException -> NavGraphEntryPointDirections.navShowNotifyDialog(
                                title = getString(R.string.page_search_error_title_http),
                                message = getString(R.string.page_search_error_message_offline),
                            )

                            else -> {
                                Timber.e(e)
                                NavGraphEntryPointDirections.navShowNotifyDialog(
                                    title = getString(R.string.page_search_error_title_http),
                                    message = getString(R.string.page_search_error_message_http),
                                )
                            }
                        }.also {
                            findNavController().navigate(it)
                            viewModel.showedError()
                        }
                    }
                }

                launch {
                    viewModel.isLoading.collect {
                        binding?.pageSearchLoading?.isVisible = it
                    }
                }

                launch {
                    viewModel.repositories.filterNotNull().collect {
                        binding?.pageSearchList?.adapter?.submitList(it)
                        binding?.pageSearchListEmpty?.isVisible = it.isEmpty()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
