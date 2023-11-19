package jp.co.yumemi.android.code_check.templates

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import jp.co.yumemi.android.code_check.R

/**
 * リトライ可能な際に表示するダイアログ
 *
 * * 「リトライ」ボタンをタップ: `true` が通知される
 * * 「キャンセル」ボタン or 領域外をタップ: `false` が通知される
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 * * Kotlin Flow を利用している
 *
 * ## 使い方
 * Jetpack Navigation の[NavBackStackEntry] 経由で選択結果を受け渡しているので、
 * 受け取り側の準備 → ダイアログの呼び出しという手順が必要です。
 *
 * 1. 受け取り側の準備(例: Fragment)
 *     ``` kotlin
 *     viewLifecycleOwner.lifecycleScope.launch {
 *         repeatOnLifecycle(Lifecycle.State.CREATED) {
 *             findNavController().currentBackStackEntry
 *                 ?.savedStateHandle
 *                 ?.getStateFlow(RetryDialogFragment.KEY_RESULT, false)
 *                 ?.collect {
 *                     // TODO: 処理の記述
 *                 }
 *         }
 *     }
 *     ```
 * 1. このダイアログを呼び出す
 *     ``` kotlin
 *     RetryDialogFragmentDirections.navShowRetryDialog(
 *         title = "TODO: Set Title",
 *         message = "TODO: Set Message",
 *     ).also { findNavController().navigate(it) }
 *     ```
 *
 * ## 参考文献
 * * [Returning a result to the previous Destination](https://developer.android.com/guide/navigation/use-graph/programmatic?hl=en#returning_a_result)
 * * [StateFlow and SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
 */
class RetryDialogFragment : DialogFragment() {

    private val args by navArgs<RetryDialogFragmentArgs>()


    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ) = MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_App_Dialog)
        .setTitle(args.title)
        .setMessage(args.message)
        .setNegativeButton(R.string.template_retry_dialog_button_negative) { dialog, _ ->
            dialog.cancel()
        }
        .setPositiveButton(R.string.template_retry_dialog_button_positive) { _, _ ->
            setResult(true)
        }
        .create()


    override fun onCancel(dialog: DialogInterface) {
        setResult(false)
    }


    private fun setResult(shouldRetry: Boolean) {
        findNavController().previousBackStackEntry
            ?.savedStateHandle
            ?.set(KEY_RESULT, shouldRetry)
    }


    companion object {

        const val KEY_RESULT = "KEY_RESULT"
    }
}
