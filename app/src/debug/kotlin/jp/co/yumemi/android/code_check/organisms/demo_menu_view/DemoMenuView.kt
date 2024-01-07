package jp.co.yumemi.android.code_check.organisms.demo_menu_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 操作デモのメニューUI (罫線付き)
 *
 * ## 使い方
 * ``` kotlin
 * val listView: DemoMenuView = TODO("View の生成 or 取得")
 * listView.adapter = DemoMenuViewAdapter {
 *     // TODO リスト項目タップ時に実行したいこと
 * }
 * ```
 */
class DemoMenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : RecyclerView(context, attrs) {

    init {
        val layoutManager = LinearLayoutManager(context).apply {
            orientation = VERTICAL
        }
        addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        this.layoutManager = layoutManager
    }


    override fun getAdapter(): DemoMenuViewAdapter? {
        return super.getAdapter() as? DemoMenuViewAdapter
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter !is DemoMenuViewAdapter) {
            throw IllegalArgumentException("DemoMenuViewAdapter を設定してください")
        }
        super.setAdapter(adapter)
    }
}
