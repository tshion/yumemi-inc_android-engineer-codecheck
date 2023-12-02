package jp.co.yumemi.android.code_check.models

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.tshion.android.codecheck.core.SearchUseCase
import io.github.tshion.android.codecheck.core.entities.RepositoryEntity
import io.github.tshion.android.codecheck.core.entities.RepositoryQueryEntity

/**
 * データ[RepositoryEntity] のページング実装
 */
class RepositoriesPagingSource private constructor(
    private val keyword: String,
    private val searchUseCase: SearchUseCase,
) : PagingSource<Int, RepositoryEntity>() {

    override fun getRefreshKey(
        state: PagingState<Int, RepositoryEntity>,
    ) = state.anchorPosition?.let {
        val anchorPage = state.closestPageToPosition(it)
        return@let anchorPage?.prevKey?.plus(1)
            ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(
        params: LoadParams<Int>,
    ) = try {
        val currentPage = params.key ?: 1

        val result = searchUseCase.searchRepositories(keyword, currentPage)
        LoadResult.Page(
            data = result.items,
            prevKey = null,
            nextKey = (currentPage + 1).takeIf { result.hasMore },
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }


    companion object {

        /**
         * ページャーの作成
         *
         * @param keyword 検索キーワード
         * @param searchUseCase 検索フロー
         */
        fun newPager(
            keyword: String,
            searchUseCase: SearchUseCase,
        ) = PagingConfig(
            pageSize = RepositoryQueryEntity.PER_PAGE,
        ).let {
            Pager(it) { RepositoriesPagingSource(keyword, searchUseCase) }
        }
    }
}
