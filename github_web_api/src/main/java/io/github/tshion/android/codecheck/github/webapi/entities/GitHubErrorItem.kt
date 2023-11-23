package io.github.tshion.android.codecheck.github.webapi.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class GitHubErrorItem(
    val resource: String,
    val field: String,
    val code: String,
)
