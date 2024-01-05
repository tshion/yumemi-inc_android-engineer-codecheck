package jp.co.yumemi.android.code_check.organisms.demo_menu_view

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.core.util.Consumer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.molecules.demo_menu_item.DemoMenuItemView
import jp.co.yumemi.android.code_check.molecules.demo_menu_item.DemoMenuItemViewData

/**
 * 操作デモのメニューを表示するためのAdapter
 *
 * @param onTapListener リスト項目タップ時に実行するリスナー
 */
class DemoMenuViewAdapter(
    private val onTapListener: Consumer<DemoMenuItemViewData>,
) : ListAdapter<DemoMenuItemViewData, DemoMenuItemView.ViewHolder>(diffs) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DemoMenuItemView.ViewHolder {
        val context = parent.context
        return DemoMenuItemView(context).apply {
            layoutParams = LayoutParams(
                MATCH_PARENT,
                context.resources.getDimensionPixelSize(R.dimen.molecule_demo_menu_item_height),
            )
        }.let { DemoMenuItemView.ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: DemoMenuItemView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.view.setup(data)
        if (data.isEnabled) {
            holder.itemView.setOnClickListener { onTapListener.accept(data) }
        } else {
            holder.view.setOnClickListener(null)
        }
    }


    companion object {

        private val diffs = object : DiffUtil.ItemCallback<DemoMenuItemViewData>() {

            override fun areItemsTheSame(
                oldItem: DemoMenuItemViewData,
                newItem: DemoMenuItemViewData,
            ) = oldItem.original.id == newItem.original.id

            override fun areContentsTheSame(
                oldItem: DemoMenuItemViewData,
                newItem: DemoMenuItemViewData,
            ) = oldItem == newItem
        }
    }
}
