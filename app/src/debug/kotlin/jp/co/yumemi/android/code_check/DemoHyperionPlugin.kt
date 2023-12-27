package jp.co.yumemi.android.code_check

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import jp.co.yumemi.android.code_check.molecules.HyperionMenuItemView

/**
 * 操作デモを表示するHyperion Plugin
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
@AutoService(Plugin::class)
class DemoHyperionPlugin : Plugin() {

    override fun createPluginModule() = object : PluginModule() {

        override fun createPluginView(
            layoutInflater: LayoutInflater,
            parent: ViewGroup,
        ) = HyperionMenuItemView(parent.context).apply {
            setName(name)
            setOnClickListener {
                val activity = extension.activity as? EntryPointActivity
                val id = activity?.mContainerId ?: return@setOnClickListener

                // TODO
            }
        }

        override fun getName() = R.string.hyperion_show_demo
    }
}
