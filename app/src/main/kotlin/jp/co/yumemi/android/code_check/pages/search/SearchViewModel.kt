package jp.co.yumemi.android.code_check.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.tshion.android.codecheck.core.SearchUseCase
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.organisms.repository_list_view.RepositoryListItemViewData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 検索画面のViewModel
 */
class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default,
) : ViewModel() {

    /** エラー */
    val error: StateFlow<Exception?>
    private val _error = MutableStateFlow<Exception?>(null)

    /** 読み込み中かどうか */
    val isLoading: StateFlow<Boolean>
    private val _isLoading = MutableStateFlow(false)

    /** リポジトリ情報 */
    val repositories: StateFlow<List<RepositoryListItemViewData>>
    private val _repositories = MutableStateFlow(emptyList<RepositoryListItemViewData>())

    /** 検索結果 */
    val searchResult: StateFlow<Result<List<RepositoryListItemViewData>>?>
    private val _searchResult = MutableStateFlow<Result<List<RepositoryListItemViewData>>?>(null)


    init {
        error = _error
        isLoading = _isLoading
        repositories = _repositories

        searchResult = _searchResult
    }


    /**
     * 検索
     *
     * @param keyword 検索キーワード
     */
    fun search(keyword: String) {
        viewModelScope.launch {
            _error.value = null
            _isLoading.value = true
            _searchResult.value = null
            try {
                val mapped = withContext(dispatcherDefault) {
                    val result = searchUseCase.searchRepositories(keyword, 1)
                    result.items.map { RepositoryListItemViewData(it) }
                }
                _repositories.value = mapped
                _searchResult.value = Result.success(mapped)
            } catch (e: Exception) {
                _error.value = e
                _searchResult.value = Result.failure(e)
            }
            _isLoading.value = false
        }
    }

    /**
     * エラー表示の完了
     */
    fun showedError() {
        _error.value = null
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
