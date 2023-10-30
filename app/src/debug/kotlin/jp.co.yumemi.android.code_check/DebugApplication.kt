package jp.co.yumemi.android.code_check

import android.os.StrictMode

/**
 * Debug ビルド時に適用するApplication クラス
 */
class DebugApplication : MainApplication() {

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
            .penaltyDeath()
            .build()
            .also { StrictMode.setVmPolicy(it) }
        // endregion

        super.onCreate()
    }
}
