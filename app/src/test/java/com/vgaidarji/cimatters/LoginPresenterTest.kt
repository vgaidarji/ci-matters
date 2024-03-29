package com.vgaidarji.cimatters

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class LoginPresenterTest {
    private lateinit var view: LoginView
    private var presenter: LoginPresenter? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        view = mock(LoginView::class.java)
        presenter = LoginPresenter(view)
    }

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldOpenNextActivityForAllowedCredentials() {
        presenter!!.onLoginClick("test@test.com", "1111")
        verify(view)?.openNextActivity()
    }

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldShowErrorForIncorrectCredentials() {
        presenter!!.onLoginClick("wrong@email.com", "not_a_password")
        verify(view)?.showError(ArgumentMatchers.anyString())
    }
}
