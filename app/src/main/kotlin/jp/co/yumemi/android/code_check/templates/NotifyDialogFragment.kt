package jp.co.yumemi.android.code_check.templates

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import jp.co.yumemi.android.code_check.R

/**
 * 結果を通知するダイアログ
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 *
 * ## 注意事項
 * * ダイアログ外に黒透過の背景が無いなどがあれば、まずはOS 標準のダイアログと見比べてください
 *     * OS 標準のダイアログを表示するには、アプリアンインストールを試すのが手軽です
 *     * エミュレーターのバグで、上手く表示できない事例があるようなので、色々な端末で見比べてください
 */
class NotifyDialogFragment : DialogFragment() {

    private val args by navArgs<NotifyDialogFragmentArgs>()


    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ) = MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_App_Dialog)
        .setTitle(args.title)
        .setMessage(args.message)
        .setPositiveButton(R.string.template_notify_dialog_button_positive, null)
        .create()
}
