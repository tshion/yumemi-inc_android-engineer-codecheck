package io.github.tshion.android.codecheck.github.webapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.tshion.android.codecheck.github.webapi.utils.GitHubInterceptor
import io.github.tshion.android.codecheck.github.webapi.utils.LocalDateTimeAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

/**
 * GitHub REST API への接続
 */
public class GitHubWebApi internal constructor(
    baseUrl: String,
    client: OkHttpClient,
) {
    public constructor(
        client: OkHttpClient,
    ) : this("https://api.github.com", client)


    /** WebAPI エンドポイント */
    public val endpoint: ApiEndpoint


    init {
        val jsonConverter = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(LocalDateTimeAdapter())
            .build()
            .let { MoshiConverterFactory.create(it) }

        val okHttpClient = client.newBuilder()
            .addInterceptor(GitHubInterceptor())
            .build()

        endpoint = Retrofit.Builder()
            .addConverterFactory(jsonConverter)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(ApiEndpoint::class.java)
    }
}
