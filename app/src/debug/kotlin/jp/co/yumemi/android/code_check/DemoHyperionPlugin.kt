package jp.co.yumemi.android.code_check

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule
import jp.co.yumemi.android.code_check.models.DemoSpecEntity
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
            DemoSpecEntity.createDemo("結果通知ダイアログの表示") {
                val direction = NavGraphDemoEntryPointDirections.navShowNotifyDialog(
                    title = "タイトル from Hyperion",
                    message = "メッセージ from Hyperion",
                )
                it.get()?.navigate(direction)
            },
            DemoSpecEntity.createDemo("リトライダイアログの表示") {
                val direction = NavGraphDemoEntryPointDirections.navShowRetryDialog(
                    title = "タイトル from Hyperion",
                    message = "メッセージ from Hyperion",
                )
                it.get()?.navigate(direction)
            },
        )
    }
}
