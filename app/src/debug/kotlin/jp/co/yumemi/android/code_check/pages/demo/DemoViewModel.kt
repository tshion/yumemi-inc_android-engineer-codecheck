package jp.co.yumemi.android.code_check.pages.demo

import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.DemoSpec
import jp.co.yumemi.android.code_check.models.DemoSpecEntity

/**
 * 操作デモ画面のViewModel
 */
class DemoViewModel : ViewModel() {

//    val items: StateFlow<null>


    fun update(spec: DemoSpecEntity? = null) {
        val items = DemoSpec.root
        android.R.layout.simple_list_item_2
    }
}
