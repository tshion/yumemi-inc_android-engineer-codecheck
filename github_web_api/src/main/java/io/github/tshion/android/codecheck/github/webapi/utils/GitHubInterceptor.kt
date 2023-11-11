package io.github.tshion.android.codecheck.github.webapi.utils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * GitHub REST API へアクセスする際の共通設定の付与
 */
internal class GitHubInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }
}
