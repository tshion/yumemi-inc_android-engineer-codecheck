package jp.co.yumemi.android.code_check.organisms.repository_list_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * リポジトリ一覧UI (罫線付き)
 *
 * ## 使い方
 * ``` kotlin
 * val listView: RepositoryListView = TODO("View の生成 or 取得")
 * listView.adapter = RepositoryListViewAdapter {
 *     // TODO リスト項目タップ時に実行したいこと
 * }
 * ```
 */
class RepositoryListView @JvmOverloads constructor(
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


    override fun getAdapter(): RepositoryListViewAdapter? {
        return super.getAdapter() as? RepositoryListViewAdapter
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter !is RepositoryListViewAdapter) {
            throw IllegalArgumentException("RepositoryListViewAdapter を設定してください")
        }
        super.setAdapter(adapter)
    }
}
