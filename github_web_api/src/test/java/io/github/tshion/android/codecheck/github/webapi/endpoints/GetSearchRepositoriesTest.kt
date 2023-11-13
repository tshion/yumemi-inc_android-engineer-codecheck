package io.github.tshion.android.codecheck.github.webapi.endpoints

import io.github.tshion.android.codecheck.github.webapi.ApiEndpoint
import io.github.tshion.android.codecheck.github.webapi.GitHubWebApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.FileSystem
import okio.Path.Companion.toPath
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

/**
 * [ApiEndpoint.getSearchRepositories] のユニットテスト
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GetSearchRepositoriesTest {

    private val applicationId = "io.github.tshion.android.codecheck.github.webapi"

    private val cacheDir = FileSystem.SYSTEM_TEMPORARY_DIRECTORY.toFile()

    private val client = OkHttpClient.Builder()
        .build()


    @Ignore("本番環境へ接続する不安定なテストのため")
    @Test
    fun 実際に通信を試してみる() = runTest {
        val webApi = GitHubWebApi(applicationId, cacheDir, client)
        val response = webApi.endpoint.getSearchRepositories("android")
        advanceUntilIdle()
        Assert.assertNotNull(response)
    }

    @Ignore("10秒以上かかるテストのため")
    @Test(expected = SocketTimeoutException::class)
    fun timeout() = runTest {
        val contents = FileSystem.RESOURCES.read("200_openapi_example.json".toPath()) {
            readUtf8()
        }
        val server = MockWebServer().apply {
            enqueue(MockResponse().apply {
                setResponseCode(200)
                setBody(contents)
                setBodyDelay(11, TimeUnit.SECONDS)
            })
            start()
        }
        val webApi = createMockWebApi(server)

        webApi.endpoint.getSearchRepositories("android")
        advanceUntilIdle()
        Assert.fail()
    }


    @Test
    fun Androidと検索した際のレスポンスをパース出来るかどうか() = runTest {
        val contents = FileSystem.RESOURCES.read("200_actual_android.json".toPath()) {
            readUtf8()
        }
        val server = MockWebServer().apply {
            enqueue(MockResponse().apply {
                setResponseCode(200)
                setBody(contents)
            })
            start()
        }
        val webApi = createMockWebApi(server)

        val response200 = webApi.endpoint.getSearchRepositories("android")
        Assert.assertNotNull(response200)
    }


    @Test
    fun code200() = runTest {
        val contents = FileSystem.RESOURCES.read("200_openapi_example.json".toPath()) {
            readUtf8()
        }
        val server = MockWebServer().apply {
            enqueue(MockResponse().apply {
                setResponseCode(200)
                setBody(contents)
            })
            start()
        }
        val webApi = createMockWebApi(server)

        val response200 = webApi.endpoint.getSearchRepositories("android")
        Assert.assertNotNull(response200)
    }

    // region TODO: 304 のテスト
//    @Test
//    fun code304() = runTest {
//        val contents = FileSystem.RESOURCES.read("200_openapi_example.json".toPath()) {
//            readUtf8()
//        }
//        val server = MockWebServer().apply {
//            enqueue(MockResponse().apply {
//                setResponseCode(200)
//                setBody(contents)
//            })
//            enqueue(MockResponse().apply {
//                setResponseCode(304)
//            })
//            start()
//        }
//        val webApi = createMockWebApi(server)
//
//        try {
//            val response200 = webApi.endpoint.getSearchRepositories("android")
//            val response304 = webApi.endpoint.getSearchRepositories("android")
//            Assert.assertNotNull(response200)
//            Assert.assertSame(response200, response304)
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()
//            Assert.assertNotNull(errorBody)
//            Assert.assertTrue(contents == errorBody?.string())
//            throw e
//        } finally {
//            server.shutdown()
//        }
//    }
    // endregion

    @Test(expected = HttpException::class)
    fun code422() = runErrorTest("422_openapi_example.json", 422)

    @Test(expected = HttpException::class)
    fun code503() = runErrorTest("503_openapi_example.json", 503)


    private fun createMockWebApi(server: MockWebServer) = GitHubWebApi(
        applicationId,
        baseUrl = server.url("/").toString(),
        cacheDir,
        client,
    )

    private fun runErrorTest(
        filename: String,
        responseCode: Int,
    ) = runTest {
        val contents = FileSystem.RESOURCES.read(filename.toPath()) {
            readUtf8()
        }
        val server = MockWebServer().apply {
            enqueue(MockResponse().apply {
                setResponseCode(responseCode)
                setBody(contents)
            })
            start()
        }
        val webApi = createMockWebApi(server)

        try {
            webApi.endpoint.getSearchRepositories("android")
        } catch (e: HttpException) {
            Assert.assertTrue(contents == e.response()?.errorBody()?.string())
            throw e
        } finally {
            server.shutdown()
        }
        Assert.fail()
    }
}
