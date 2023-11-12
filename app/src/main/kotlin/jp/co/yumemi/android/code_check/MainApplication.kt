package jp.co.yumemi.android.code_check

import android.app.Application
import okhttp3.OkHttpClient
import timber.log.Timber

abstract class MainApplication : Application() {

    /** アプリ全体で共有するOkHttpClient */
    protected abstract val okHttpClient: OkHttpClient

    /** Timber のログ出力ツリー */
    protected abstract val timberTree: Timber.Tree


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)
    }
}
