package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment

/**
 * エントリーポイントを担当するActivity
 *
 * リソース"navigation/nav_graph_entry_point" で定義した画面を表示する
 *
 * ## 使用条件
 * * Jetpack Navigation コンポーネントを利用している
 */
open class EntryPointActivity : AppCompatActivity() {

    var containerId: Int? = null
        private set

    /** 参照するNavigation グラフ */
    @NavigationRes
    protected open val navigationRes = R.navigation.nav_graph_entry_point


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ID の復元
        val viewId = savedInstanceState?.getInt(STATE_ID)
            ?: ViewCompat.generateViewId()
        containerId = viewId

        // View の整備
        FragmentContainerView(this).apply {
            id = viewId
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }.also { setContentView(it) }

        if (supportFragmentManager.findFragmentById(viewId) == null) {
            // Jetpack Navigation の設定
            // 参考: https://developer.android.com/guide/navigation/use-graph/programmatic#create_a_navhostfragment
            val fragment = NavHostFragment.create(navigationRes)

            supportFragmentManager.commit {
                replace(viewId, fragment)
                setPrimaryNavigationFragment(fragment)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        containerId?.also { outState.putInt(STATE_ID, it) }
        super.onSaveInstanceState(outState)
    }


    companion object {

        private const val STATE_ID = "STATE_ID"
    }
}
