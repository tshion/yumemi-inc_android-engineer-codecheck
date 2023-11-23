package jp.co.yumemi.android.code_check.e2e

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import jp.co.yumemi.android.code_check.EntryPointActivity
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.toName
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 入力検証関連のE2E テスト
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ValidateE2ETest {

    private lateinit var device: UiDevice

    @JvmField
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(EntryPointActivity::class.java)


    @Before
    fun onBeforeTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


    @Test
    fun 空文字() {
        runTest("", R.string.page_search_error_message_invalid)
    }

    @Test
    fun 長すぎる文字列() {
        val keyword = (0..256).joinToString("") { "a" }
        runTest(keyword, R.string.page_search_error_message_http)
    }


    /**
     * テストの実行
     *
     * @param keyword 検索するキーワード
     */
    private fun runTest(
        keyword: String,
        @StringRes dialogTitleId: Int,
    ) {
        // 入力欄へキーワードを入力する
        onView(
            allOf(
                withId(R.id.page_search_box_editor),
                isDisplayed(),
            )
        ).run {
            perform(click())
            perform(replaceText(keyword))
            perform(pressImeActionButton())
        }

        // ダイアログが表示される
        device.wait(
            Until.hasObject(By.res(dialogTitleId.toName())),
            1_000,
        )
        onView(withText(dialogTitleId)).check(matches(isDisplayed()))
    }
}
