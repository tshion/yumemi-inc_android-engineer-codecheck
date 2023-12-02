package jp.co.yumemi.android.code_check.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.cachedIn
import androidx.paging.map
import io.github.tshion.android.codecheck.core.SearchUseCase
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.models.RepositoriesPagingSource
import jp.co.yumemi.android.code_check.organisms.repository_list_view.RepositoryListItemViewData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

/**
 * 検索画面のViewModel
 */
class SearchViewModel(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    /** 検索条件 */
    private val _query = MutableStateFlow<String?>(null)

    /** 検索結果 */
    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResult = _query
        .filterNotNull()
        .flatMapLatest {
            RepositoriesPagingSource.newPager(it, searchUseCase).flow
        }
        .map { data ->
            data.map { RepositoryListItemViewData(it) }
        }
        .cachedIn(viewModelScope)


    /**
     * 検索
     *
     * @param keyword 検索キーワード
     */
    fun search(keyword: String) {
        _query.value = keyword
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MainApplication
                SearchViewModel(application.searchUseCase)
            }
        }
    }
}
