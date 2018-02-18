package com.vgaidarji.cimatters

internal interface LoginView {
    fun openNextActivity()

    fun showError(message: String)
}
