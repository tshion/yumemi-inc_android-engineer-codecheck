package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * GitHub REST API のエラーレスポンス
 *
 * ※[参考文献](https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#client-errors)
 */
public data class GitHubErrorResponse(
    val message: String,
    val errors: List<GitHubErrorItem>? = null,
)
