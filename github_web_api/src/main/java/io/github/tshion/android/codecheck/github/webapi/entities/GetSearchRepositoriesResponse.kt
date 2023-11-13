package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * @param total_count
 * @param incomplete_results
 * @param items
 */
public data class GetSearchRepositoriesResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepoSearchResultItem>,
)
