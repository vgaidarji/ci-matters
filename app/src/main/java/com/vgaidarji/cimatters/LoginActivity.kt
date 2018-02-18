package com.vgaidarji.cimatters

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class LoginActivity : AppCompatActivity(), LoginView {

    @BindView(R.id.coordinator)
    lateinit var coordinatorLayout: CoordinatorLayout
    @BindView(R.id.edit_text_email)
    lateinit var editTextEmail: EditText
    @BindView(R.id.edit_text_password)
    lateinit var editTextPassword: EditText
    @BindView(R.id.button_login)
    lateinit var buttonLogin: Button

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = getString(R.string.activity_login)
        ButterKnife.bind(this)
        presenter = LoginPresenter(this)
    }

    @OnClick(R.id.button_login)
    fun onLoginClick() {
        presenter.onLoginClick(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
        )
    }

    override fun openNextActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun showError(message: String) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
