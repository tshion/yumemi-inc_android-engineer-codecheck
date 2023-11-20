package jp.co.yumemi.android.code_check.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.tshion.android.codecheck.core.SearchUseCase
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.RepositoryViewData
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

    /** 読み込み中かどうか */
    val isLoading: StateFlow<Boolean>
    private val _isLoading = MutableStateFlow(false)

    /** 検索結果 */
    val searchResult: StateFlow<Result<List<RepositoryViewData>>?>
    private val _searchResult = MutableStateFlow<Result<List<RepositoryViewData>>?>(null)


    init {
        isLoading = _isLoading
        searchResult = _searchResult
    }


    /**
     * 検索
     *
     * @param keyword 検索キーワード
     */
    fun search(keyword: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _searchResult.value = null

            _searchResult.value = withContext(dispatcherDefault) {
                runCatching {
                    val result = searchUseCase.searchRepositories(keyword, 1)
                    result.items.map {
                        val url = it.ownerIconUrl?.toString()
                        RepositoryViewData(
                            it.forkCount,
                            imageText = if (url != null) it.ownerName else null,
                            imageUrl = url,
                            it.issueCount,
                            it.language,
                            it.starCount,
                            title = "${it.ownerName}/${it.name}",
                            it.watcherCount,
                        )
                    }
                }
            }
            _isLoading.value = false
        }
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
