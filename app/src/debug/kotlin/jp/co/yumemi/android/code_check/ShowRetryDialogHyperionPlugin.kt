package jp.co.yumemi.android.code_check

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import jp.co.yumemi.android.code_check.molecules.HyperionMenuItemView
import jp.co.yumemi.android.code_check.templates.RetryDialogFragmentDirections

/**
 * リトライダイアログを表示するHyperion Plugin
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
@AutoService(Plugin::class)
class ShowRetryDialogHyperionPlugin : Plugin() {

    override fun createPluginModule() = object : PluginModule() {

        override fun createPluginView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup,
        ) = HyperionMenuItemView(parent.context).apply {
            setName(name)
            setOnClickListener {
                try {
                    val activity = extension.activity as? EntryPointActivity
                    val id = activity?.containerId ?: return@setOnClickListener

                    val direction = RetryDialogFragmentDirections.navShowRetryDialog(
                        title = "タイトル from Hyperion",
                        message = "メッセージ from Hyperion",
                    )
                    activity.findNavController(id).navigate(direction)
                } catch (e: Exception) {
                    Toast.makeText(parent.context, "表示できません", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun getName() = R.string.hyperion_show_retry_dialog_title
    }
}
