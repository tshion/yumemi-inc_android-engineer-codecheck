package jp.co.yumemi.android.code_check

import android.app.Application
import timber.log.Timber

abstract class MainApplication : Application() {

    /** Timber のログ出力ツリー */
    protected abstract val timberTree: Timber.Tree


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)
    }
}
