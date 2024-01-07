package jp.co.yumemi.android.code_check.models

import android.content.Context
import android.content.Intent
import androidx.core.util.Function
import androidx.navigation.NavDirections

/**
 * 操作デモを実行した際の、画面操作の定義
 */
interface DemoViewContract {

    /**
     * 指定された画面の起動
     */
    fun launch(fx: Function<Context, Intent>)

    /**
     * 指定された箇所への遷移
     */
    fun navigate(directions: NavDirections)
}
