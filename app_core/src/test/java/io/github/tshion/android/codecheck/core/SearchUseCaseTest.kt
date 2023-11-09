package io.github.tshion.android.codecheck.core

import io.github.tshion.android.codecheck.core.entities.RepositoryResultEntity
import io.github.tshion.android.codecheck.core.repositories.GitHubRepositoryContract
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

/**
 * [SearchUseCase] のユニットテスト
 */
@OptIn(ExperimentalCoroutinesApi::class)
class SearchUseCaseTest {

    @Test()
    fun リポジトリ検索() = runHappyPathTest("android")

    @Test()
    fun 空白を指定してリポジトリ検索() = runHappyPathTest("   ")


    @Test(expected = IllegalArgumentException::class)
    fun nullを指定してリポジトリ検索() = runKeywordErrorTest(null)

    @Test(expected = IllegalArgumentException::class)
    fun 空文字を指定してリポジトリ検索() = runKeywordErrorTest("")

    @Test(expected = IllegalArgumentException::class)
    fun 長すぎるキーワードを指定してリポジトリ検索() {
        val word = (0..300).joinToString(separator = "") { "a" }
        runKeywordErrorTest(word)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun ゼロを指定してリポジトリ検索() = runTest {
        val useCase = SearchUseCase(
            gitHubRepository = mockk(),
        )
        useCase.searchRepositories("abc", 0)
        Assert.fail()
    }


    private fun runHappyPathTest(value: String) = runTest {
        val fakeResult = RepositoryResultEntity(false, emptyList())
        val repository = mockk<GitHubRepositoryContract>()
        coEvery {
            repository.search(any())
        } returns fakeResult

        val useCase = SearchUseCase(repository)

        val result = useCase.searchRepositories(value, 1)
        coVerify(exactly = 1) { repository.search(any()) }
        Assert.assertSame(fakeResult, result)
    }

    private fun runKeywordErrorTest(value: String?) = runTest {
        val useCase = SearchUseCase(
            gitHubRepository = mockk(),
        )
        useCase.searchRepositories(value, 1)
        Assert.fail()
    }
}
