package com.vgaidarji.cimatters

internal class LoginPresenter(private val view: LoginView) {

    fun onLoginClick(email: String, password: String) {
        if (email == EMAIL && password == PASSWORD) {
            view.openNextActivity()
        } else {
            view.showError(WRONG_CREDENTIALS)
        }
    }

    companion object {

        private const val EMAIL = "test@test.com"
        private const val PASSWORD = "1111"
        private const val WRONG_CREDENTIALS = "Wrong credentials."
    }
}
