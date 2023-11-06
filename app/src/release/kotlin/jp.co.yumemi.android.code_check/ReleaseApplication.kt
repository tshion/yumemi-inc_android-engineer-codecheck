package jp.co.yumemi.android.code_check

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber

/**
 * Release ビルド時に適用するApplication クラス
 */
class ReleaseApplication : MainApplication() {

    /** Timber のログ出力ツリー */
    override val timberTree = object : Timber.Tree() {
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
}
