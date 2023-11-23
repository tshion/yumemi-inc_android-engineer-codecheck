package io.github.tshion.android.codecheck.github.webapi.utils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * GitHub REST API へアクセスする際の共通設定の付与
 *
 * @param applicationId ユーザーエージェントに設定するアプリケーションID
 */
internal class GitHubInterceptor(
    private val applicationId: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Accept", "application/vnd.github+json")
            // https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#user-agent-%E3%81%AE%E5%BF%85%E8%A6%81%E6%80%A7
            .header("User-Agent", applicationId)
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}
