package jp.co.yumemi.android.code_check.molecules.demo_menu_item

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.MoleculeDemoMenuItemBinding

/**
 * 操作デモのメニュー項目UI
 *
 * ## 使い方
 * `RecyclerView` のリスト項目として使ってください。
 * `RecyclerView.Adapter` での実装例は下記となります。
 *
 * ``` kotlin
 * class ???Adapter : RecyclerView.Adapter<DemoMenuItemView.ViewHolder>() {
 *     // (省略)
 *
 *     override fun onCreateViewHolder(
 *         parent: ViewGroup,
 *         viewType: Int,
 *     ) = DemoMenuItemView(parent.context).apply {
 *         layoutParams = LayoutParams(
 *             MATCH_PARENT,
 *             WRAP_CONTENT,
 *         )
 *     }.let { DemoMenuItemView.ViewHolder(it) }
 *
 *     override fun onBindViewHolder(
 *         holder: DemoMenuItemView.ViewHolder,
 *         position: Int,
 *     ) {
 *         // (省略)
 *     }
 *
 *     // (省略)
 * }
 * ```
 */
class DemoMenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding = MoleculeDemoMenuItemBinding.inflate(
        LayoutInflater.from(context),
        this,
    )


    init {
        isClickable = true
        setBackgroundResource(R.color.app_base)

        // ripple 効果の設定
        // 参考文献: https://stackoverflow.com/a/48884995
        foreground = AppCompatResources.getDrawable(
            context,
            TypedValue().apply {
                context.theme.resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    this,
                    true,
                )
            }.resourceId,
        )
    }


    /**
     * 表示セットアップ
     */
    fun setup(data: DemoMenuItemViewData) {
        binding.moleculeDemoMenuItemOverlay.isVisible = !data.isEnabled
        binding.moleculeDemoMenuItemSubtitle.apply {
            val subtitle = data.original.subtitle
            isVisible = !subtitle.isNullOrBlank()
            text = subtitle
        }
        binding.moleculeDemoMenuItemTitle.text = data.original.getTitle(context)
    }


    class ViewHolder(
        val view: DemoMenuItemView,
    ) : RecyclerView.ViewHolder(view)
}
