package jp.co.yumemi.android.code_check.pages.demo

import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.DemoHyperionPlugin
import jp.co.yumemi.android.code_check.molecules.demo_menu_item.DemoMenuItemViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 操作デモ画面のViewModel
 */
class DemoViewModel : ViewModel() {

    val specs: StateFlow<List<DemoMenuItemViewData>>
    private val _specs = MutableStateFlow(emptyList<DemoMenuItemViewData>())


    init {
        specs = _specs
    }


    /**
     * 操作デモのデータ読み込み
     */
    fun load(specId: Int) {
        val specs = if (specId != ResourcesCompat.ID_NULL) {
            DemoHyperionPlugin.demoSpecs.firstOrNull {
                it.search(specId) != null
            }?.children ?: emptyList()
        } else {
            DemoHyperionPlugin.demoSpecs
        }
        _specs.value = specs.map {
            DemoMenuItemViewData(true, it)
        }
    }
}
