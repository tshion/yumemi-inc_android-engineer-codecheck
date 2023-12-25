package jp.co.yumemi.android.code_check.pages.detail

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import jp.co.yumemi.android.code_check.EntryPointActivity
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * [DetailViewData] のユニットテスト
 *
 * ※インストルメンテーションテストの理由: `getResources` が必要なため
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class DetailViewDataUnitTest {

    @JvmField
    @Rule
    var mActivityScenarioRule = ActivityScenarioRule(EntryPointActivity::class.java)


    @Test
    fun アイコン有りなら画像関連データも有る() {
        val entity = RepositoryEntity.parseOrNull(
            forkCount = 0,
            issueCount = 0,
            language = null,
            name = "",
            ownerIconUrl = "https://github.com/tshion/yumemi-inc_android-engineer-codecheck",
            ownerName = "tshion",
            starCount = 0,
            watcherCount = 0,
        )
        assertNotNull(entity)
        entity!!

        mActivityScenarioRule.scenario.onActivity {
            val data = DetailViewData.parse(entity, it.resources)
            assertThat(data.imageText, `is`(entity.ownerName))
            assertNotNull(data.imageUrl)
        }
    }

    @Test
    fun アイコン無しなら画像関連データは無し() {
        val entity = RepositoryEntity.parseOrNull(
            forkCount = 0,
            issueCount = 0,
            language = null,
            name = "",
            ownerIconUrl = null,
            ownerName = "tshion",
            starCount = 0,
            watcherCount = 0,
        )
        assertNotNull(entity)
        entity!!

        mActivityScenarioRule.scenario.onActivity {
            val data = DetailViewData.parse(entity, it.resources)
            assertNull(data.imageText)
            assertNull(data.imageUrl)
        }
    }
}
