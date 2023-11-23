package io.github.tshion.android.codecheck.core

import io.github.tshion.android.codecheck.core.entities.RepositoryQueryEntity
import io.github.tshion.android.codecheck.core.entities.RepositoryResultEntity
import io.github.tshion.android.codecheck.core.repositories.GitHubRepositoryContract

/**
 * 検索フロー
 */
public class SearchUseCase(
    private val gitHubRepository: GitHubRepositoryContract,
) {

    /**
     * リポジトリの検索
     *
     * @param keyword 検索キーワード
     * @param page ページ番号の指定
     * @throws IllegalArgumentException 空文字 or null or 300文字を超える場合
     * @throws IndexOutOfBoundsException 意図しないページ数を指定された場合
     */
    public suspend fun searchRepositories(
        keyword: String?,
        page: Int,
    ): RepositoryResultEntity {
        if (keyword.isNullOrEmpty() || 300 < keyword.length) {
            throw IllegalArgumentException()
        }
        if (page < 1) {
            throw IndexOutOfBoundsException()
        }
        val query = RepositoryQueryEntity(keyword, page)

        return gitHubRepository.search(query)
    }
}
