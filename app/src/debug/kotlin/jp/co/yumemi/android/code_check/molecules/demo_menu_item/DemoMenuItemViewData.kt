package jp.co.yumemi.android.code_check.molecules.demo_menu_item

import jp.co.yumemi.android.code_check.models.DemoSpecEntity

/**
 * 操作デモのメニュー項目UI の表示データ
 *
 * @param isEnabled 利用可能かどうか
 * @param original オリジナルデータ
 */
data class DemoMenuItemViewData(
    val isEnabled: Boolean,
    val original: DemoSpecEntity,
)
