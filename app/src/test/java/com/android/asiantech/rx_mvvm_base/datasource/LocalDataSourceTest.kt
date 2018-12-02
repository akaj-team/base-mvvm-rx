package com.android.asiantech.rx_mvvm_base.datasource

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.source.local.ComicLocalDataSource
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 *
 * @author at-haingo
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LocalDataSourceTest : BaseTest() {
    private lateinit var ComicLocalDataSource: ComicLocalDataSource

    @Before
    fun beforeTest() {
        ComicLocalDataSource = ComicLocalDataSource(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun `Given apiToken - When call getApiToken() - Then apiToken is correct`() {
        /* Given */
        ComicLocalDataSource.saveApiToken("token")

        /* When */

        /* Then */
        Assert.assertThat(ComicLocalDataSource.getApiToken(), `is`("token"))
    }


    @Test
    fun `Given apiToke - When call clearApiToken() - Then apiToken is empty `() {
        /* Given */
        ComicLocalDataSource.saveApiToken("token")

        /* When */
        ComicLocalDataSource.clearApiToken()

        /* Then */
        Assert.assertThat(ComicLocalDataSource.getApiToken(), `is`(""))
    }
}