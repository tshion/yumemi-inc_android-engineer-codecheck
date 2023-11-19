package jp.co.yumemi.android.code_check.molecules

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.ImageViewCompat
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.toPx
import com.willowtreeapps.hyperion.plugin.R as HyperionR

/**
 * Hyperion のメニュー項目UI
 *
 * ## 参考文献
 * * [Hyperion Timber のメニュー項目の実装](https://github.com/willowtreeapps/Hyperion-Android/blob/develop/hyperion-timber/src/main/res/layout/tmb_item_plugin.xml)
 */
class HyperionMenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayoutCompat(context, attrs) {

    private val nameTextView: AppCompatTextView


    init {
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
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = 131f.toPx(resources)
        orientation = HORIZONTAL
        setPadding(
            resources.getDimensionPixelSize(HyperionR.dimen.hype_plugin_padding)
        )


        val colorSelector = ContextCompat.getColor(
            context,
            HyperionR.color.hype_plugin_color_selector,
        )

        // アイコン表示
        val imageView = AppCompatImageView(context).apply {
            val size = resources.getDimensionPixelSize(HyperionR.dimen.hype_plugin_icon_size)
            val spaceH = 28f.toPx(resources)

            adjustViewBounds = true
            isDuplicateParentStateEnabled = true
            layoutParams = LayoutParams(size, size).apply {
                setMargins(0, 0, spaceH, 0)
            }
            setImageResource(R.drawable.ic_launcher_foreground)
            ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(colorSelector))
        }

        // 項目名
        nameTextView = AppCompatTextView(context).apply {
            isDuplicateParentStateEnabled = true
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
            setTextColor(colorSelector)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        }

        // 子View の追加
        addView(imageView)
        addView(nameTextView)
    }


    /**
     * 項目名の設定
     */
    fun setName(@StringRes id: Int) {
        nameTextView.setText(id)
    }
}
