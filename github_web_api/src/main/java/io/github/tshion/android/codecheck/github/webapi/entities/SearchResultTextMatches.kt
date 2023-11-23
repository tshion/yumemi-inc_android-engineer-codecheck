package io.github.tshion.android.codecheck.github.webapi.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class SearchResultTextMatches(
    val objectUrl: String? = null,
    val objectType: String? = null,
    val property: String? = null,
    val fragment: String? = null,
    val matches: List<Matches>? = null
)
