package jp.co.yumemi.android.code_check.molecules

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import jp.co.yumemi.android.code_check.R

/**
 * 画面タイトルを表示するツールバー
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 *
 * ## 使い方
 * 画面UI にこのツールバーを配置し、コードから `setupWith(NavController)` を呼び出してください。
 *
 * ``` xml
 * <???>
 *     <!-- 省略 -->
 *     <package.SimpleToolbar
 *         android:id="@+id/???"
 *         android:layout_width="match_parent"
 *         android:layout_height="?actionBarSize" />
 *     <!-- 省略 -->
 * </???>
 * ```
 */
class SimpleToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : Toolbar(context, attrs) {

    init {
        setBackgroundResource(R.color.app_main)
        setTitleTextColor(ContextCompat.getColor(context, R.color.app_main_on))
    }


    /**
     * Jetpack Navigation コンポーネントを使って表示をセットアップ
     *
     * * [参考文献](https://developer.android.com/guide/navigation/navigation-ui#create_a_toolbar)
     */
    fun setupWith(navController: NavController) {
        setupWithNavController(
            navController,
            AppBarConfiguration(navController.graph),
        )
    }
}
