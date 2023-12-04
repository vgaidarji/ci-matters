package com.vgaidarji.cimatters

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import butterknife.ButterKnife
import butterknife.InjectView
import butterknife.OnClick

class LoginActivity : AppCompatActivity(), LoginView {
    @InjectView(R.id.coordinator)
    var coordinatorLayout: CoordinatorLayout? = null

    @InjectView(R.id.edit_text_email)
    var editTextEmail: EditText? = null

    @InjectView(R.id.edit_text_password)
    var editTextPassword: EditText? = null

    @InjectView(R.id.button_login)
    var buttonLogin: Button? = null
    private var presenter: LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = getString(R.string.activity_login)
        ButterKnife.inject(this)
        presenter = LoginPresenter(this)
    }

    @OnClick(R.id.button_login)
    fun onLoginClick() {
        presenter!!.onLoginClick(
            editTextEmail!!.text.toString(),
            editTextPassword!!.text.toString()
        )
    }

    override fun openNextActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun showError(message: String?) {
        message?.let { Snackbar.make(coordinatorLayout!!, it, Snackbar.LENGTH_LONG).show() }
    }
}
