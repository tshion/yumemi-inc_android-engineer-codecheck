package jp.co.yumemi.android.code_check

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ハッピーパスのUI テスト
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class HappyPathUiTest {

    @JvmField
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(TopActivity::class.java)


    @Test
    fun androidと検索して5番目の詳細を表示する() {
        runTest("android", 5)
    }

    @Test
    fun yumemiと検索して1番目の詳細を表示する() {
        runTest("yumemi", 1)
    }


    /**
     * テストの実行
     *
     * @param keyword 検索するキーワード
     * @param position 検索結果一覧でタップする項目番号
     */
    private fun runTest(
        keyword: String,
        position: Int,
    ) {
        // 入力欄へキーワードを入力する
        onView(
            allOf(
                withId(R.id.searchInputText),
                isDisplayed(),
            )
        ).run {
            perform(click())
            perform(replaceText(keyword))
            perform(pressImeActionButton())
        }

        // 検索結果の１項目をタップする
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ViewHolder>(position, click()))

        // 詳細画面に遷移したことの確認
        onView(withId(R.id.ownerIconView))
            .check(matches(isDisplayed()))
    }
}
