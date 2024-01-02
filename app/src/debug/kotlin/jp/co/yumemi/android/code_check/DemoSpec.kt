package jp.co.yumemi.android.code_check

import jp.co.yumemi.android.code_check.models.DemoSpecEntity.Companion.createDemo
import jp.co.yumemi.android.code_check.templates.NotifyDialogFragmentDirections
import jp.co.yumemi.android.code_check.templates.RetryDialogFragmentDirections

/**
 * 操作デモの定義
 */
object DemoSpec {

    /** ルートに表示する操作デモ一覧 */
    val root = listOf(
        createDemo("結果通知ダイアログの表示") {
            val direction = NotifyDialogFragmentDirections.navShowNotifyDialog(
                title = "タイトル from Hyperion",
                message = "メッセージ from Hyperion",
            )
            it.get()?.navigate(direction)
        },
        createDemo("リトライダイアログの表示") {
            val direction = RetryDialogFragmentDirections.navShowRetryDialog(
                title = "タイトル from Hyperion",
                message = "メッセージ from Hyperion",
            )
            it.get()?.navigate(direction)
        },
    )
}
