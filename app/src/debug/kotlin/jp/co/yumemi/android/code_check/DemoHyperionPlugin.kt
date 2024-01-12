package jp.co.yumemi.android.code_check

import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import jp.co.yumemi.android.code_check.models.DemoSpecEntity.Companion.createDemo
import jp.co.yumemi.android.code_check.models.DemoSpecEntity.Companion.createNavigator
import jp.co.yumemi.android.code_check.models.specs.showDialogDemoSpecs
import jp.co.yumemi.android.code_check.molecules.HyperionMenuItemView
import jp.co.yumemi.android.code_check.pages.DemoEntryPointActivity

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
                val activity = extension.activity
                DemoEntryPointActivity.newIntent(activity)
                    .also { activity.startActivity(it) }
            }
        }

        override fun getName() = R.string.hyperion_show_demo
    }


    companion object {

        /** 操作デモ一覧 */
        val demoSpecs = listOf(
            createNavigator(R.string.hyperion_show_demo_title_show_dialog) {
                showDialogDemoSpecs
            },
            createDemo(R.string.hyperion_show_demo_title_launch_license) {
                val direction = NavGraphDemoEntryPointDirections.navLaunchLicense()
                it.get()?.navigate(direction)
            },
            createDemo(
                R.string.hyperion_show_demo_title_launch_app_settings,
                subtitle = "OS の設定アプリを起動する",
            ) { ref ->
                ref.get()?.launch {
                    Intent(
                        ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${it.packageName}")
                    )
                }
            },
            createDemo(
                R.string.hyperion_show_demo_title_launch_dev_settings,
                subtitle = "OS の設定アプリを起動する",
            ) { ref ->
                ref.get()?.launch {
                    Intent(ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                }
            },
        )
    }
}
