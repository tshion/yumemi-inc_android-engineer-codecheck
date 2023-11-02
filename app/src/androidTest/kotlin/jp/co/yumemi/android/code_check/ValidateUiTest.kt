package jp.co.yumemi.android.code_check

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.ktor.client.features.ClientRequestException
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 入力検証関連のUI テスト
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ValidateUiTest {

    @JvmField
    @Rule
    var activityScenarioRule = ActivityScenarioRule(TopActivity::class.java)


    @Test
    fun 空文字() {
        runSearch("")

        onView(withId(R.id.recyclerView))
            .check(matches(recyclerViewSizeMatcher(0)))
    }

    @Test(expected = ClientRequestException::class)
    fun 長すぎる文字列() {
        val keyword = (0..256).joinToString("") { "a" }
        runSearch(keyword)
    }


    /**
     * RecyclerView の項目数のMatcher
     *
     * 参考文献: https://stackoverflow.com/a/63583748
     */
    private fun recyclerViewSizeMatcher(matcherSize: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with list size: $matcherSize")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return matcherSize == recyclerView.adapter!!.itemCount
            }
        }
    }

    /**
     * 検索の実行
     *
     * @param keyword 検索するキーワード
     */
    private fun runSearch(keyword: String) {
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
    }
}
