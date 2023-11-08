package io.github.tshion.android.codecheck.core.entities

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * [RepositoryEntity] のユニットテスト
 */
internal class RepositoryEntityTest {

    @Test
    fun ハッピーパス() {
        val item = RepositoryEntity.parseOrNull(
            forkCount = 0,
            fullName = "a / b",
            issueCount = 0,
            language = null,
            ownerIconUrl = null,
            starCount = 0,
            watcherCount = 0,
        )
        assertNotNull(item)
        item!!

        RepositoryEntity.parseOrNull(
            item.forkCount,
            item.fullName,
            item.issueCount,
            language = "kotlin",
            ownerIconUrl = "https://github.com/tshion/yumemi-inc_android-engineer-codecheck",
            item.starCount,
            item.watcherCount,
        ).also { assertNotNull(it) }
    }

    @Test
    fun 数字に異常がある場合はnull() {
        val item = RepositoryEntity.parseOrNull(
            forkCount = 0,
            fullName = "a / b",
            issueCount = 0,
            language = null,
            ownerIconUrl = null,
            starCount = 0,
            watcherCount = 0,
        )
        assertNotNull(item)
        item!!

        RepositoryEntity.parseOrNull(
            forkCount = -1,
            item.fullName,
            item.issueCount,
            item.language,
            item.ownerIconUrl?.toString(),
            item.starCount,
            item.watcherCount,
        ).also { assertNull(it) }

        RepositoryEntity.parseOrNull(
            item.forkCount,
            item.fullName,
            issueCount = -1,
            item.language,
            item.ownerIconUrl?.toString(),
            item.starCount,
            item.watcherCount,
        ).also { assertNull(it) }

        RepositoryEntity.parseOrNull(
            item.forkCount,
            item.fullName,
            item.issueCount,
            item.language,
            item.ownerIconUrl?.toString(),
            starCount = -1,
            item.watcherCount,
        ).also { assertNull(it) }

        RepositoryEntity.parseOrNull(
            item.forkCount,
            item.fullName,
            item.issueCount,
            item.language,
            item.ownerIconUrl?.toString(),
            item.starCount,
            watcherCount = -1,
        ).also { assertNull(it) }
    }

    @Test
    fun 解釈できないURLがある場合はnull() {
        RepositoryEntity.parseOrNull(
            forkCount = 0,
            fullName = "a / b",
            issueCount = 0,
            language = null,
            ownerIconUrl = "github.com/tshion/yumemi-inc_android-engineer-codecheck",
            starCount = 0,
            watcherCount = 0,
        ).also { assertNull(it) }
    }
}
