package io.github.tshion.android.codecheck.github.webapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.tshion.android.codecheck.github.webapi.utils.GitHubInterceptor
import io.github.tshion.android.codecheck.github.webapi.utils.OffsetDateTimeAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * GitHub REST API への接続
 */
public class GitHubWebApi internal constructor(
    applicationId: String,
    baseUrl: String,
    cacheDir: File,
    client: OkHttpClient,
) {

    /**
     * @param applicationId ユーザーエージェントに設定するアプリケーションID
     * @param cacheDir 通信キャッシュを保存するディレクトリ
     * @param client アプリ全体で共有しているOkHttpClient
     */
    public constructor(
        applicationId: String,
        cacheDir: File,
        client: OkHttpClient,
    ) : this(applicationId, "https://api.github.com", cacheDir, client)


    /** WebAPI エンドポイント */
    public val endpoint: ApiEndpoint


    init {
        val jsonConverter = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(OffsetDateTimeAdapter())
            .build()
            .let { MoshiConverterFactory.create(it) }

        val okHttpClient = client.newBuilder()
            .addInterceptor(GitHubInterceptor(applicationId))
            .cache(
                Cache(
                    directory = File(cacheDir, "github_http_cache"),
                    maxSize = 4L * 1024L * 1024L,
                )
            )
            // https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#timeouts
            // 10[s] + additional: 0.5[s]
            .callTimeout(10_500L, TimeUnit.MILLISECONDS)
            .build()

        endpoint = Retrofit.Builder()
            .addConverterFactory(jsonConverter)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(ApiEndpoint::class.java)
    }
}
