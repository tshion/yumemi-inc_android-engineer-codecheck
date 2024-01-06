package jp.co.yumemi.android.code_check.models.specs

import jp.co.yumemi.android.code_check.NavGraphDemoEntryPointDirections
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.models.DemoSpecEntity.Companion.createDemo

val showDialogDemoSpecs = listOf(
    createDemo(R.string.hyperion_show_demo_title_show_notify_dialog) {
        val direction = NavGraphDemoEntryPointDirections.navShowNotifyDialog(
            title = "タイトル from Hyperion",
            message = "メッセージ from Hyperion",
        )
        it.get()?.navigate(direction)
    },
    createDemo(R.string.hyperion_show_demo_title_show_retry_dialog) {
        val direction = NavGraphDemoEntryPointDirections.navShowRetryDialog(
            title = "タイトル from Hyperion",
            message = "メッセージ from Hyperion",
        )
        it.get()?.navigate(direction)
    },
)
