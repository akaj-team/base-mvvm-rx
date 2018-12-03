package com.android.asiantech.rx_mvvm_base.viewmodel

import com.android.asiantech.rx_mvvm_base.BaseTest
import com.android.asiantech.rx_mvvm_base.data.source.Repository
import com.android.asiantech.rx_mvvm_base.data.source.remote.response.SignUpResponse
import com.android.asiantech.rx_mvvm_base.ui.user.register.RegisterViewModel
import com.android.asiantech.rx_mvvm_base.util.RxSchedulersOverrideRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-haingo
 */
class RegisterViewModelTest : BaseTest() {
    @get:Rule
    val rule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        viewModel = RegisterViewModel(repository)
    }

    @Test
    fun `Given nothing - When call constructor RegisterViewModel - Then has no exception`() {
        /* Given */

        /* When */

        /* Then */
        Assert.assertThat(viewModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `Given email, password and avatar - When call register() - Then SignUp message and progress dialog status is correct`() {
        /* Given */
        val progressDiaLogStatusTest = TestObserver<Boolean>()
        val registerTest = TestObserver<SignUpResponse>()
        `when`(repository.register(anyString(), anyString(), anyString())).thenReturn(Single.just(SignUpResponse("Register completed")))

        /* When */
        viewModel.progressDialogStatus().subscribe(progressDiaLogStatusTest)
        viewModel.register(anyString(), anyString(), anyString()).subscribe(registerTest)

        /* Then */
        progressDiaLogStatusTest.assertValueCount(2)
        progressDiaLogStatusTest.assertValueAt(0) {
            Assert.assertThat(it, `is`(true))
            true
        }

        progressDiaLogStatusTest.assertValueAt(1) {
            Assert.assertThat(it, `is`(false))
            true
        }

        registerTest.assertValue {
            Assert.assertThat(it.message, `is`("Register completed"))
            true
        }
    }

    @Test
    fun `Given all condition is incorrect - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = ""
        val password = "123"
        val confirmPassword = "1234"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct - When validateRegisterInformation() - Then validateRegisterInformationStatus is true`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "password123"
        val confirmPassword = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(true))
            true
        }
    }

    @Test
    fun `Given all condition is correct but email is empty - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = ""
        val password = "password123"
        val confirmPassword = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct but email is incorrect - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = "test123"
        val password = "password123"
        val confirmPassword = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct but password is incorrect - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "pass"
        val confirmPassword = "password123"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct but confirm password is incorrect - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "password123"
        val confirmPassword = "pass"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }

    @Test
    fun `Given all condition is correct but password and confirm password is different - When validateRegisterInformation() - Then validateRegisterInformationStatus is false`() {
        /* Given */
        val validateRegisterInformationStatusTest = TestObserver<Boolean>()
        val email = "test123@gmail.com"
        val password = "password123"
        val confirmPassword = "password1234"

        /* When */
        viewModel.infoValidateStatus().subscribe(validateRegisterInformationStatusTest)
        viewModel.validateRegisterInformation(email, password, confirmPassword)

        /* Then */
        validateRegisterInformationStatusTest.assertValue {
            Assert.assertThat(it, `is`(false))
            true
        }
    }
}
