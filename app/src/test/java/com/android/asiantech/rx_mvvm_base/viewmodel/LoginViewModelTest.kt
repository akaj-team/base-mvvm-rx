package com.android.asiantech.rx_mvvm_base.viewmodel

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.source.LocalRepository
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.LoginResponse
import com.android.asiantech.rx_mvvm_base.ui.user.login.LoginViewModel
import com.android.asiantech.rx_mvvm_base.util.RxSchedulersOverrideRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-haingo
 */
class LoginViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var localRepository: LocalRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(repository, localRepository)
    }

    @Test
    fun `Given nothing - When call constructor LoginViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given email and password - When call login() - Then LoginResponse and processDialog status is correct`() {
        /* Given */
        val loginTest = TestObserver<LoginResponse>()
        val processDialogStatusTest = TestObserver<Boolean>()
        `when`(repository.login(anyString(), anyString())).thenReturn(Single.just(LoginResponse("token")))

        /* When */
        viewModel.progressDialogStatus().subscribe(processDialogStatusTest)
        viewModel.login(anyString(), anyString()).subscribe(loginTest)

        /* Then */
        loginTest.assertValue {
            Assert.assertThat(it.accessToken, `is`("token"))
            true
        }

        processDialogStatusTest.assertValueCount(2)
        processDialogStatusTest.assertValueAt(0) {
            Assert.assertThat(it, `is`(true))
            true
        }

        processDialogStatusTest.assertValueAt(1) {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is incorrect - When call validateLoginInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val infoValidateTest = TestObserver<Boolean>()
        val email = ""
        val password = "123"

        /* When */
        viewModel.infoValidateStatus().subscribe(infoValidateTest)
        viewModel.validateLoginInformation(email, password)

        /* Then */
        infoValidateTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct - When call validateLoginInformation() - Then validateRegisterInformationStatus is true`() {
        /* Given */
        val infoValidateTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(infoValidateTest)
        viewModel.validateLoginInformation(email, password)

        /* Then */
        infoValidateTest.assertValue {
            Assert.assertThat(it, `is`(true))
            true
        }
    }

    @Test
    fun `Given all condition is correct, only email is empty - When call validateLoginInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val infoValidateTest = TestObserver<Boolean>()
        val email = ""
        val password = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(infoValidateTest)
        viewModel.validateLoginInformation(email, password)

        /* Then */
        infoValidateTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct, only password length less than 8 - When call validateLoginInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val infoValidateTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "pass1"

        /* When */
        viewModel.infoValidateStatus().subscribe(infoValidateTest)
        viewModel.validateLoginInformation(email, password)

        /* Then */
        infoValidateTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct, only email is incorrect - When call validateLoginInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val infoValidateTest = TestObserver<Boolean>()
        val email = "test123"
        val password = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(infoValidateTest)
        viewModel.validateLoginInformation(email, password)

        /* Then */
        infoValidateTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given token - When call saveApiToken() - Then has no exception`() {
        /* Given */
        val token = "abc123"

        /* When */
        viewModel.saveApiToken(token)

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }
}
