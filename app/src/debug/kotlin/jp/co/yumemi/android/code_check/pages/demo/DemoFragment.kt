package jp.co.yumemi.android.code_check.pages.demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
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

    private var binding: PageDemoBinding? = null

    private val viewModel by viewModels<DemoViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageDemoBinding.bind(view)

        binding?.pageDemoHeader?.setupWith(findNavController())

        binding?.pageDemoList?.adapter = DemoMenuViewAdapter {
            it.original.tapAction?.invoke(WeakReference(this))
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.specs.collect {
                    binding?.pageDemoList?.adapter?.submitList(it)
                }
            }
        }


        viewModel.update()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
