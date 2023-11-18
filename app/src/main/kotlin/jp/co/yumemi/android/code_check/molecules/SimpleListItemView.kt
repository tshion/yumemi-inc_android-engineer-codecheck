package jp.co.yumemi.android.code_check.molecules

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.toPx

/**
 * 1つの文言を表示するリスト項目UI
 *
 * ## 使い方
 * `RecyclerView` のリスト項目として使ってください。
 * `RecyclerView.Adapter` での実装例は下記となります。
 *
 * ``` kotlin
 * class ???Adapter : RecyclerView.Adapter<SimpleListItemView.ViewHolder>() {
 *     // (省略)
 *
 *     override fun onCreateViewHolder(
 *         parent: ViewGroup,
 *         viewType: Int,
 *     ) = SimpleListItemView(parent.context).apply {
 *         layoutParams = LayoutParams(
 *             MATCH_PARENT,
 *             WRAP_CONTENT,
 *         )
 *     }.let { SimpleListItemView.ViewHolder(it) }
 *
 *     override fun onBindViewHolder(
 *         holder: SimpleListItemView.ViewHolder,
 *         position: Int,
 *     ) {
 *         holder.view.text = "set text"
 *     }
 *
 *     // (省略)
 * }
 * ```
 */
class SimpleListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : AppCompatTextView(context, attrs) {

    init {
        val paddingH = 16f.toPx(resources)
        val paddingV = 8f.toPx(resources)
        setPadding(paddingH, paddingV, paddingH, paddingV)
        isClickable = true


        setBackgroundResource(R.color.app_base)

        // ripple 効果の設定
        // 参考文献: https://stackoverflow.com/a/48884995
        foreground = getDrawable(
            context,
            TypedValue().apply {
                context.theme.resolveAttribute(
                    android.R.attr.selectableItemBackground,
                    this,
                    true,
                )
            }.resourceId,
        )


        setTextColor(ContextCompat.getColor(context, R.color.app_base_on))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
    }


    class ViewHolder(
        val view: SimpleListItemView,
    ) : RecyclerView.ViewHolder(view)
}
