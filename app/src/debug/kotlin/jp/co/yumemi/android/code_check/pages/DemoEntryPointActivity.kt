package jp.co.yumemi.android.code_check.pages

import android.content.Context
import android.content.Intent
import jp.co.yumemi.android.code_check.EntryPointActivity
import jp.co.yumemi.android.code_check.R

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
