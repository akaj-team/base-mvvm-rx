package com.android.asiantech.rx_mvvm_base

import com.android.asiantech.rx_mvvm_base.data.source.remote.network.ApiClient
import com.android.asiantech.rx_mvvm_base.datasource.ComicRemoteDataSourceTest
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**

 * @author at-haingo.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
        ComicRemoteDataSourceTest::class
)
class ApiSuiteTest {

    companion object {
        internal val server = MockWebServer()
        internal lateinit var baseUrl: HttpUrl

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            ApiClient.clearInstance()
            try {
                server.start()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
            baseUrl = server.url("/")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            server.shutdown()
            ApiClient.clearInstance()
        }

        internal val apiClient: ApiClient
            get() = ApiClient.getInstance(baseUrl.toString(), true)
    }
}
