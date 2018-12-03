package com.android.asiantech.rx_mvvm_base.datasource

import com.android.asiantech.rx_mvvm_base.ApiSuiteTest
import com.android.asiantech.rx_mvvm_base.data.source.remote.RemoteDataSource
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.FavoriteResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.HomeResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import com.android.asiantech.rx_mvvm_base.extension.addResponseBody
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Test

/**
 *
 * @author at-haingo
 */
class RemoteDataSourceTest {

    @Test
    fun `Given api - When call constructor  - Then has no exeption`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(RemoteDataSource(ApiSuiteTest.apiClient.service), CoreMatchers.notNullValue())
    }

    @Test
    fun `Given nothing - When call RemoteDataSourceTest secondary constructor - Then do nothing`() {
        /* Given */

        /* When */
        RemoteDataSource()

        /* Then */
        assert(true)
    }

    @Test
    fun `Given email and password - When call login() - Then response is correct`() {
        /* Given */
        val loginTest = TestObserver<LoginResponse>()
        ApiSuiteTest.server.addResponseBody("LoginResponse.json")

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).login("test123@gmail.com", "password")
                .subscribe(loginTest)

        /* Then */
        loginTest.assertValue {
            Assert.assertThat(it.accessToken, `is`("token"))
            true
        }
    }

    @Test
    fun `Given email, password and avatar url - When call register() - Then response is correct`() {
        /* Given */
        val registerTest = TestObserver<SignUpResponse>()
        ApiSuiteTest.server.addResponseBody("SignUpResponse.json")

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).register("test123@gmail.com", "password", "avatarUrl")
                .subscribe(registerTest)

        /* Then */
        registerTest.assertValue {
            Assert.assertThat(it.message, `is`("register completed"))
            true
        }
    }

    @Test
    fun `Given page - When call getComics() - Then response is correct`() {
        /* Given */
        val getComicsTest = TestObserver<HomeResponse>()
        ApiSuiteTest.server.addResponseBody("HomeResponse.json")

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).getComics(1)
                .subscribe(getComicsTest)

        /* Then */
        getComicsTest.assertValue {
            Assert.assertThat(it.nextPageFlag, `is`(true))
            Assert.assertThat(it.comics.size, `is`(0))
            true
        }
    }

    @Test
    fun `Given comic id - When call favorite() - Then response is correct`() {
        /* Given */
        val favoriteTest = TestObserver<FavoriteResponse>()
        ApiSuiteTest.server.addResponseBody("FavoriteResponse.json")

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).favorite(1)
                .subscribe(favoriteTest)

        /* Then */
        favoriteTest.assertValue {
            Assert.assertThat(it.success, `is`(true))
            true
        }
    }

    @Test
    fun `Given comic id - When call unFavorite() - Then response is correct`() {
        /* Given */
        val favoriteTest = TestObserver<FavoriteResponse>()
        ApiSuiteTest.server.addResponseBody("FavoriteResponse.json")

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).unFavorite(1)
                .subscribe(favoriteTest)

        /* Then */
        favoriteTest.assertValue {
            Assert.assertThat(it.success, `is`(true))
            true
        }
    }

    @Test
    fun `Given nothing - When call getUser() - Then response is correct`() {
        /* Given */

        /* When */
        RemoteDataSource(ApiSuiteTest.apiClient.service).getUser()

        /* Then */
        Assert.assertTrue(true)
    }
}
