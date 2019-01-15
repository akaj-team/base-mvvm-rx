package com.android.asiantech.rx_mvvm_base.viewmodel

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.ui.user.UserViewModel
import com.android.asiantech.rx_mvvm_base.util.RxSchedulersOverrideRule
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-haingo
 */
class UserViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var localRepository: LocalRepository

    private lateinit var viewModel: UserViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(localRepository)
    }

    @Test
    fun `Given nothing - When call constructor UserViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given apiToken is not blank - When call checkLogin() - Then loginStatus is true`() {
        /* Given */
        val loginStatusTest = TestObserver<Boolean>()
        `when`(localRepository.getApiToken()).thenReturn("abc123")

        /* When */
        viewModel.loginStatus().subscribe(loginStatusTest)
        viewModel.checkLogin()

        /* Then */
        loginStatusTest.assertValue {
            Assert.assertThat(it, `is`(true))
            true
        }
    }

    @Test
    fun `Given apiToken is blank - When call checkLogin() - Then loginStatus is false`() {
        /* Given */
        val loginStatusTest = TestObserver<Boolean>()
        `when`(localRepository.getApiToken()).thenReturn("")

        /* When */
        viewModel.loginStatus().subscribe(loginStatusTest)
        viewModel.checkLogin()

        /* Then */
        loginStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }
}
