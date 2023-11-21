package jp.co.yumemi.android.code_check.organisms.repository_list_view

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import jp.co.yumemi.android.code_check.molecules.SimpleListItemView

/**
 * リポジトリ一覧を表示するためのAdapter
 *
 * @param onTapListener リスト項目タップ時に実行するリスナー
 */
class RepositoryListViewAdapter(
    private val onTapListener: (RepositoryEntity) -> Unit,
) : ListAdapter<RepositoryListItemViewData, SimpleListItemView.ViewHolder>(diffs) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleListItemView(parent.context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }.let { SimpleListItemView.ViewHolder(it) }

    override fun onBindViewHolder(holder: SimpleListItemView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.view.text = data.text
        holder.itemView.setOnClickListener {
            onTapListener.invoke(data.original)
        }
    }


    companion object {

        private val diffs = object : DiffUtil.ItemCallback<RepositoryListItemViewData>() {

            override fun areItemsTheSame(
                oldItem: RepositoryListItemViewData,
                newItem: RepositoryListItemViewData,
            ) = oldItem.text == newItem.text

            override fun areContentsTheSame(
                oldItem: RepositoryListItemViewData,
                newItem: RepositoryListItemViewData,
            ) = oldItem == newItem
        }
    }
}
