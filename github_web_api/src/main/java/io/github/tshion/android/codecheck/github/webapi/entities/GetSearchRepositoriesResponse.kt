package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * @param totalCount
 * @param incompleteResults
 * @param items
 */
public data class GetSearchRepositoriesResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<RepoSearchResultItem>,
)
