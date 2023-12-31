package jp.co.yumemi.android.code_check.pages.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.util.Function
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.PageDemoBinding
import jp.co.yumemi.android.code_check.models.DemoViewContract
import jp.co.yumemi.android.code_check.organisms.demo_menu_view.DemoMenuViewAdapter
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/**
 * 操作デモ画面
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
class DemoFragment : Fragment(R.layout.page_demo), DemoViewContract {

    private val args by navArgs<DemoFragmentArgs>()

    private var binding: PageDemoBinding? = null

    private val viewModel by viewModels<DemoViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageDemoBinding.bind(view)

        binding?.pageDemoHeader?.apply {
            setupWith(this@DemoFragment.findNavController())
            if (title.isNullOrBlank()) {
                title = getString(R.string.page_demo_title)
            }
        }

        binding?.pageDemoList?.adapter = DemoMenuViewAdapter { menu ->
            val action = menu.original.tapAction
            if (action != null) {
                action.invoke(WeakReference(this))
            } else {
                DemoFragmentDirections.navGoDemo(
                    label = menu.original.getTitle(requireContext()),
                    specId = menu.original.id,
                ).also { navigate(it) }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.specs.collect {
                    binding?.pageDemoList?.adapter?.submitList(it)
                }
            }
        }


        viewModel.load(args.specId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    /**
     * 指定された画面の起動
     */
    override fun launch(fx: Function<Context, Intent>) {
        context?.let { fx.apply(it) }
            ?.also { startActivity(it) }
    }

    /**
     * 指定された箇所への遷移
     */
    override fun navigate(directions: NavDirections) {
        try {
            findNavController().navigate(directions)
        } catch (e: Exception) {
            Toast.makeText(context, "表示できません", Toast.LENGTH_SHORT).show()
        }
    }
}
