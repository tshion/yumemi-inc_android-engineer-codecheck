package jp.co.yumemi.android.code_check.pages.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.tshion.android.codecheck.core.SearchUseCase
import jp.co.yumemi.android.code_check.organisms.repository_list_view.RepositoryListItemViewData

/**
 * ページング出来るリポジトリ一覧情報
 */
class RepositoriesPagingSource private constructor(
    private val keyword: String,
    private val searchUseCase: SearchUseCase,
) : PagingSource<Int, RepositoryListItemViewData>() {

    override fun getRefreshKey(
        state: PagingState<Int, RepositoryListItemViewData>,
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

        // TODO: View 都合による変換ロジックの移動
        val data = result.items.map { RepositoryListItemViewData(it) }

        LoadResult.Page(
            data = data,
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
            pageSize = 50, // FIXME: RepositoryQueryEntity の値を利用する
        ).let {
            Pager(it) { RepositoriesPagingSource(keyword, searchUseCase) }
        }
    }
}
