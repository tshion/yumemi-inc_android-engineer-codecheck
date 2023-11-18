package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
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
class EntryPointActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View の整備
        val containerId = ViewCompat.generateViewId()
        FragmentContainerView(this).apply {
            id = containerId
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }.also { setContentView(it) }

        // Jetpack Navigation の設定
        // 参考: https://developer.android.com/guide/navigation/use-graph/programmatic#create_a_navhostfragment
        val fragment = NavHostFragment.create(R.navigation.nav_graph_entry_point)
        supportFragmentManager.commit {
            replace(containerId, fragment)
            setPrimaryNavigationFragment(fragment)
        }
    }
}
