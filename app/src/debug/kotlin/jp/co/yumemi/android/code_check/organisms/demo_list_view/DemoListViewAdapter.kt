package jp.co.yumemi.android.code_check.organisms.demo_list_view

import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import jp.co.yumemi.android.code_check.models.DemoSpecEntity
import jp.co.yumemi.android.code_check.molecules.SimpleListItemView

/**
 * 操作デモ一覧を表示するためのAdapter
 *
 * @param onTapListener リスト項目タップ時に実行するリスナー
 */
class DemoListViewAdapter(
    private val onTapListener: Consumer<DemoSpecEntity>,
) : ListAdapter<DemoSpecEntity, SimpleListItemView.ViewHolder>(diffs) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleListItemView(parent.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }.let { SimpleListItemView.ViewHolder(it) }

    override fun onBindViewHolder(holder: SimpleListItemView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.view.text = data.title
        holder.itemView.setOnClickListener {
            onTapListener.accept(data)
        }
    }


    companion object {

        private val diffs = object : DiffUtil.ItemCallback<DemoSpecEntity>() {

            override fun areItemsTheSame(
                oldItem: DemoSpecEntity,
                newItem: DemoSpecEntity,
            ) = oldItem.dataId == newItem.dataId

            override fun areContentsTheSame(
                oldItem: DemoSpecEntity,
                newItem: DemoSpecEntity,
            ) = oldItem.dataId == newItem.dataId
                    && oldItem.title == newItem.title
        }
    }
}
