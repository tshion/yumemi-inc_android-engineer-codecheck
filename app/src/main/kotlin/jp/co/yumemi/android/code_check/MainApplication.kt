package jp.co.yumemi.android.code_check

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import io.github.tshion.android.codecheck.core.SearchUseCase
import io.github.tshion.android.codecheck.github.webapi.GitHubWebApi
import jp.co.yumemi.android.code_check.models.GitHubRepository
import okhttp3.OkHttpClient
import timber.log.Timber

abstract class MainApplication : Application(), ImageLoaderFactory {

    /** アプリ全体で共有するOkHttpClient */
    protected abstract val okHttpClient: OkHttpClient

    /** 検索フロー */
    lateinit var searchUseCase: SearchUseCase

    /** Timber のログ出力ツリー */
    protected abstract val timberTree: Timber.Tree


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)

        // region ロジック関連インスタンスの作成
        val gitHubWebApi = GitHubWebApi(
            applicationId = packageName,
            cacheDir = filesDir,
            client = okHttpClient,
        )
        val gitHubRepository = GitHubRepository(gitHubWebApi)
        // endregion

        searchUseCase = SearchUseCase(gitHubRepository)
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
