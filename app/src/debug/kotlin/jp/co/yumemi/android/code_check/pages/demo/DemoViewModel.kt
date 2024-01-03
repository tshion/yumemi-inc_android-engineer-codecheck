package jp.co.yumemi.android.code_check.pages.demo

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


    fun update(spec: DemoMenuItemViewData? = null) {
        _specs.value = DemoHyperionPlugin.demoSpecs.map {
            DemoMenuItemViewData(true, it)
        }
    }
}
