package jp.co.yumemi.android.code_check

import android.os.StrictMode
import androidx.fragment.app.strictmode.FragmentStrictMode
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

/**
 * Debug ビルド時に適用するApplication クラス
 */
class DebugApplication : MainApplication() {

    /** アプリ全体で共有するOkHttpClient */
    override val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor {
            Timber.tag("OkHttp").d(it)
        })
        .build()

    /** Timber のログ出力ツリー */
    override val timberTree = Timber.DebugTree()


    override fun onCreate() {
        // region: StrictMode の設定
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyFlashScreen()
            .build()
            .also { StrictMode.setThreadPolicy(it) }

        StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            // .penaltyDeath() // FIXME: 1.0.3 時点では通信実行時に引っ掛かるので、一時的に封印
            .build()
            .also { StrictMode.setVmPolicy(it) }
        // endregion

        super.onCreate()

        // region: FragmentStrictMode の設定
        FragmentStrictMode.Policy.Builder()
            .detectFragmentReuse()
            .detectFragmentTagUsage()
            .detectRetainInstanceUsage()
            .detectSetUserVisibleHint()
            .detectTargetFragmentUsage()
            .detectWrongFragmentContainer()
            .penaltyDeath()
            .build()
            .also { FragmentStrictMode.defaultPolicy = it }
        // endregion
    }
}
