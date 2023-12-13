package jp.co.yumemi.android.code_check.models

import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import io.github.tshion.android.codecheck.core.entities.RepositoryQueryEntity
import io.github.tshion.android.codecheck.core.entities.RepositoryResultEntity
import io.github.tshion.android.codecheck.core.repositories.GitHubRepositoryContract
import io.github.tshion.android.codecheck.github.webapi.GitHubWebApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * GitHub 関連データのリポジトリ実装
 */
class GitHubRepository(
    private val gitHubWebApi: GitHubWebApi,
    private val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default,
) : GitHubRepositoryContract {

    /**
     * 検索の実行
     */
    override suspend fun search(
        query: RepositoryQueryEntity,
    ): RepositoryResultEntity {
        val response = gitHubWebApi.endpoint.getSearchRepositories(
            q = query.keyword,
            sort = query.sort,
            order = query.order,
            perPage = query.perPage,
            page = query.page,
        )

        return withContext(dispatcherDefault) {
            val items = response.items.mapNotNull {
                val item = RepositoryEntity.parseOrNull(
                    forkCount = it.forks_count,
                    issueCount = it.open_issues_count,
                    language = it.language,
                    name = it.name,
                    ownerIconUrl = it.owner?.avatar_url,
                    ownerName = it.owner?.login,
                    starCount = it.stargazers_count,
                    watcherCount = it.watchers_count,
                )
                if (item == null) {
                    Timber.e("Fail parse ${it.full_name}")
                }
                return@mapNotNull item
            }

            val count = query.perPage * query.page
            RepositoryResultEntity(
                hasMore = count < response.total_count,
                items = items,
            )
        }
    }
}
