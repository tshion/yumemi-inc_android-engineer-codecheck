package jp.co.yumemi.android.code_check

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 端末状態にまつわるUI テスト
 *
 * ※日本語表示の端末を想定
 * ※機内モードは無効の想定
 * ※自動回転は無効の想定
 */
@Ignore("テストする端末の前提があるため")
@LargeTest
@RunWith(AndroidJUnit4::class)
class DeviceStateUiTest {

    private lateinit var device: UiDevice

    @JvmField
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(TopActivity::class.java)


    @Before
    fun onBeforeTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


    @Test
    fun 画面回転後に検索してもクラッシュしない() {
        // FIXME: 自動回転の有効化方法
        val descRotate = By.desc("自動回転")
        device.run {
            openQuickSettings()
            wait(Until.hasObject(descRotate), 500)
            findObject(descRotate)?.click()
            pressBack()
            pressBack()
            setOrientationRight()
        }

        // 入力欄へキーワードを入力する
        runSearch()

        // FIXME: 自動回転の無効化方法
        device.run {
            openQuickSettings()
            wait(Until.hasObject(descRotate), 500)
            findObject(descRotate)?.click()
            pressHome()
            setOrientationLeft()
        }
    }

    @Test
    fun 機内モードの状態で検索してもクラッシュしない() {
        // FIXME: 機内モードの有効化方法
        val descAirplane = By.desc("機内モード")
        device.run {
            openQuickSettings()
            wait(Until.hasObject(descAirplane), 500)
            findObject(descAirplane)?.click()
            pressBack()
            pressBack()
        }

        // 入力欄へキーワードを入力する
        runSearch()

        // FIXME: Toast 表示が出るかどうか
//        mActivityScenarioRule.scenario.onActivity {
//            it.runOnUiThread {
//            onView(withText(R.string.error_offline))
//                .inRoot(withDecorView(not(`is`(it.window.decorView))))
//                .check(matches(isDisplayed()))
//            }
//        }

        // FIXME: 機内モードの無効化方法
        device.run {
            openQuickSettings()
            wait(Until.hasObject(descAirplane), 500)
            findObject(descAirplane)?.click()
            pressHome()
        }
    }


    /**
     * 検索の実行
     */
    private fun runSearch() {
        onView(
            Matchers.allOf(
                withId(R.id.searchInputText),
                isDisplayed(),
            )
        ).run {
            perform(click())
            perform(replaceText("android"))
            perform(pressImeActionButton())
        }
    }
}
