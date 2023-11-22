package jp.co.yumemi.android.code_check.e2e

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import jp.co.yumemi.android.code_check.EntryPointActivity
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.molecules.SimpleListItemView
import jp.co.yumemi.android.code_check.toName
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ハッピーパスを試すE2E テスト
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class HappyPathE2ETest {

    private lateinit var device: UiDevice

    @JvmField
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(EntryPointActivity::class.java)


    @Before
    fun onBeforeTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


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
                withId(R.id.page_search_box_editor),
                isDisplayed(),
            )
        ).run {
            perform(click())
            perform(replaceText(keyword))
            perform(pressImeActionButton())
        }

        // ローディングが表示される
        val loadingId = R.id.page_search_loading
        device.wait(
            Until.hasObject(By.res(loadingId.toName())),
            1_000,
        )
        onView(withId(loadingId)).check(matches(isDisplayed()))

        // 検索結果が表示される
        device.wait(
            Until.gone(By.res(loadingId.toName())),
            1_000,
        )

        val listId = R.id.page_search_list
        device.wait(
            Until.hasObject(By.res(listId.toName())),
            1_000,
        )
        onView(withId(listId)).check(matches(hasMinimumChildCount(1)))
        device.waitForIdle()

        // 検索結果の１項目をタップする
        onView(withId(listId)).perform(
            actionOnItemAtPosition<SimpleListItemView.ViewHolder>(
                position,
                click()
            )
        )

        // 詳細画面に遷移したことの確認
        val imageId = R.id.page_detail_image
        device.wait(
            Until.gone(By.res(imageId.toName())),
            1_000,
        )
        onView(withId(imageId)).check(matches(isDisplayed()))
    }
}
