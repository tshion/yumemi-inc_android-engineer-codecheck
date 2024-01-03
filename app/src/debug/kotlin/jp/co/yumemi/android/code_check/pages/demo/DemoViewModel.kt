package jp.co.yumemi.android.code_check.pages.demo

import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.DemoHyperionPlugin
import jp.co.yumemi.android.code_check.models.DemoSpecEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 操作デモ画面のViewModel
 */
class DemoViewModel : ViewModel() {

    val specs: StateFlow<List<DemoSpecEntity>>
    private val _specs = MutableStateFlow(emptyList<DemoSpecEntity>())


    init {
        specs = _specs
    }


    fun update(spec: DemoSpecEntity? = null) {
        _specs.value = DemoHyperionPlugin.demoSpecs
    }
}
