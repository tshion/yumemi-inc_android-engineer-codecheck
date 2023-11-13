package io.github.tshion.android.codecheck.github.webapi.entities

public data class GitHubErrorItem(
    val resource: String,
    val field: String,
    val code: String,
)
