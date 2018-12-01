package com.android.asiantech.rx_mvvm_base.viewmodel

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.ui.main.setting.SettingViewModel
import com.android.asiantech.rx_mvvm_base.util.RxSchedulersOverrideRule
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-haingo
 */
class SettingViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var localRepository: LocalRepository

    private lateinit var viewModel: SettingViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        viewModel = SettingViewModel(localRepository)
    }

    @Test
    fun `Given nothing - When call constructor SettingViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given nothing - When call clearApiToken() - Then has no exception `() {
        /* Given */


        /* When */
        viewModel.clearApiToken()

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }
}