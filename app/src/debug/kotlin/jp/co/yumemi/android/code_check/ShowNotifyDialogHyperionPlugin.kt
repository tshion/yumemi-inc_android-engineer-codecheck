package jp.co.yumemi.android.code_check

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import jp.co.yumemi.android.code_check.molecules.HyperionMenuItemView
import jp.co.yumemi.android.code_check.templates.NotifyDialogFragmentDirections

/**
 * 結果通知ダイアログを表示するHyperion Plugin
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
@AutoService(Plugin::class)
class ShowNotifyDialogHyperionPlugin : Plugin() {

    override fun createPluginModule() = object : PluginModule() {

        override fun createPluginView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup,
        ) = HyperionMenuItemView(parent.context).apply {
            setName(name)
            setOnClickListener {
                try {
                    val direction = NotifyDialogFragmentDirections.navShowNotifyDialog(
                        title = "タイトル from Hyperion",
                        message = "メッセージ from Hyperion",
                    )
                    extension.activity
                        .findNavController(R.id.nav_host_fragment)
                        .navigate(direction)
                } catch (e: Exception) {
                    Toast.makeText(parent.context, "表示できません", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun getName() = R.string.hyperion_show_notify_dialog_title
    }
}
