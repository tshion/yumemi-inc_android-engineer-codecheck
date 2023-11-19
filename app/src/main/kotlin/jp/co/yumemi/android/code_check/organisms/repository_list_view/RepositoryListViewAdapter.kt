package jp.co.yumemi.android.code_check.organisms.repository_list_view

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import jp.co.yumemi.android.code_check.RepositoryViewData
import jp.co.yumemi.android.code_check.molecules.SimpleListItemView

/**
 * リポジトリ一覧を表示するためのAdapter
 *
 * @param onTapListener リスト項目タップ時に実行するリスナー
 */
class RepositoryListViewAdapter(
    private val onTapListener: (RepositoryViewData) -> Unit,
) : ListAdapter<RepositoryViewData, SimpleListItemView.ViewHolder>(diffs) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleListItemView(parent.context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }.let { SimpleListItemView.ViewHolder(it) }

    override fun onBindViewHolder(holder: SimpleListItemView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.view.text = data.title
        holder.itemView.setOnClickListener {
            onTapListener.invoke(data)
        }
    }


    companion object {

        private val diffs = object : DiffUtil.ItemCallback<RepositoryViewData>() {

            override fun areItemsTheSame(
                oldItem: RepositoryViewData,
                newItem: RepositoryViewData,
            ) = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: RepositoryViewData,
                newItem: RepositoryViewData,
            ) = oldItem == newItem
        }
    }
}
