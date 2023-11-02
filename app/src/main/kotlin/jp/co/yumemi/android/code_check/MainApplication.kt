package jp.co.yumemi.android.code_check

import android.app.Application
import timber.log.Timber

open class MainApplication : Application() {

    /** Timber のログ出力ツリー */
    protected open val timberTree = object : Timber.Tree() {
        override fun log(
            priority: Int,
            tag: String?,
            message: String,
            t: Throwable?,
        ) {
            // TODO: 必要に応じてログ出力先を設定する
        }
    }


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)
    }
}
