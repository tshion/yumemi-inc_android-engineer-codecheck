package jp.co.yumemi.android.code_check

import android.content.Context
import android.content.Intent

/**
 * 操作デモのエントリーポイントを担当するActivity
 */
class DemoEntryPointActivity : EntryPointActivity() {

    /** 参照するNavigation グラフ */
    override val navigationRes = R.navigation.nav_graph_demo_entry_point


    companion object {

        fun newIntent(
            context: Context,
        ) = Intent(context, DemoEntryPointActivity::class.java)
    }
}
