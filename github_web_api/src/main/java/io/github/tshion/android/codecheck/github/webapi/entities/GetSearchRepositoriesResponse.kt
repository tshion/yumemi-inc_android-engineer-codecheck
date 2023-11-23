package io.github.tshion.android.codecheck.github.webapi.entities

import com.squareup.moshi.JsonClass

/**
 * @param total_count
 * @param incomplete_results
 * @param items
 */
@JsonClass(generateAdapter = true)
public data class GetSearchRepositoriesResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepoSearchResultItem>,
)
