package jp.co.yumemi.android.code_check.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.google.android.material.progressindicator.CircularProgressIndicator
import jp.co.yumemi.android.code_check.R

/**
 * ローディングUI
 *
 * ## 使い方
 * 表示に時間がかかるView に被せるように配置してください。
 *
 * ``` xml
 * <FrameLayout>
 *     <!-- 表示に時間がかかるView -->
 *     <View />
 *
 *     <!-- ローディング表示 -->
 *     <(package).LoadingOverlay />
 * </FrameLayout>
 * ```
 */
class LoadingOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    init {
        // Note: このView でタップイベントを奪い、下に配置したView に伝搬しないようにする
        isClickable = true
        isFocusable = true

        setBackgroundResource(R.color.app_transparent_shadow)

        // ローディングの配置
        addView(
            CircularProgressIndicator(context).apply {
                isIndeterminate = true
                setIndicatorColor(resources.getColor(R.color.app_accent, null))
            },
            LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT,
                Gravity.CENTER,
            ),
        )
    }
}
