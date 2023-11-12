package jp.co.yumemi.android.code_check

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import okhttp3.OkHttpClient
import timber.log.Timber

abstract class MainApplication : Application(), ImageLoaderFactory {

    /** アプリ全体で共有するOkHttpClient */
    protected abstract val okHttpClient: OkHttpClient

    /** Timber のログ出力ツリー */
    protected abstract val timberTree: Timber.Tree


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)
    }


    /**
     * Return a new [ImageLoader].
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .okHttpClient(okHttpClient)
            .build()
    }
}
