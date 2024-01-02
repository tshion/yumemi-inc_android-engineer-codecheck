package jp.co.yumemi.android.code_check.pages.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.PageDemoBinding
import jp.co.yumemi.android.code_check.models.DemoViewContract

/**
 * 操作デモ画面
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
class DemoFragment : Fragment(R.layout.page_demo), DemoViewContract {

    private var binding: PageDemoBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PageDemoBinding.bind(view)

        binding?.pageDemoHeader?.setupWith(findNavController())

        binding?.pageDemoList?.apply {
            val layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            this.layoutManager = layoutManager
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    /**
     * 指定された箇所への遷移
     */
    override fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}
