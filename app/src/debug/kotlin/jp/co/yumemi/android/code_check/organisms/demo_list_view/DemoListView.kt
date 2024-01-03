package jp.co.yumemi.android.code_check.organisms.demo_list_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 操作デモ一覧UI (罫線付き)
 *
 * ## 使い方
 * ``` kotlin
 * val listView: DemoListView = TODO("View の生成 or 取得")
 * listView.adapter = DemoListViewAdapter {
 *     // TODO リスト項目タップ時に実行したいこと
 * }
 * ```
 */
class DemoListView @JvmOverloads constructor(
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


    override fun getAdapter(): DemoListViewAdapter? {
        return super.getAdapter() as? DemoListViewAdapter
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter !is DemoListViewAdapter) {
            throw IllegalArgumentException("DemoListViewAdapter を設定してください")
        }
        super.setAdapter(adapter)
    }
}
