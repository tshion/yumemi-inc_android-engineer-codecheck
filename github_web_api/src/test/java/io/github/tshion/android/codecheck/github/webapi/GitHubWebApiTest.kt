package io.github.tshion.android.codecheck.github.webapi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.FileSystem
import okio.Path.Companion.toPath
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException

/**
 * [GitHubWebApiTest] のユニットテスト
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GitHubWebApiTest {

    private val applicationId = "io.github.tshion.android.codecheck.github.webapi"

    private val cacheDir = FileSystem.SYSTEM_TEMPORARY_DIRECTORY.toFile()

    private val client = OkHttpClient.Builder()
        .build()


    @Test
    fun parse() = runTest {
        val contents = FileSystem.RESOURCES.read("503_openapi_example.json".toPath()) {
            readUtf8()
        }
        val server = MockWebServer().apply {
            enqueue(MockResponse().apply {
                setResponseCode(503)
                setBody(contents)
            })
            start()
        }
        val webApi = GitHubWebApi(
            applicationId,
            baseUrl = server.url("/").toString(),
            cacheDir,
            client,
        )

        try {
            webApi.endpoint.getSearchRepositories("android")
        } catch (e: HttpException) {
            val errorResponse = webApi.parse(e)
            Assert.assertNotNull(errorResponse)
            errorResponse!!
            Assert.assertTrue(errorResponse.message.isNotEmpty())
        } finally {
            server.shutdown()
        }
    }
}