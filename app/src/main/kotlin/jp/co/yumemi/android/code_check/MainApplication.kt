package jp.co.yumemi.android.code_check

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
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
            when (priority) {
                Log.ERROR -> t?.also { Firebase.crashlytics.recordException(it) }
            }
        }
    }


    override fun onCreate() {
        super.onCreate()

        // Timber の設定
        Timber.plant(timberTree)
    }
}
