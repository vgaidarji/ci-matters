package com.vgaidarji.cimatters

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.vgaidarji.cimatters.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)
        title = getString(R.string.activity_login)
        presenter = LoginPresenter(this)
        setupLoginButtonClickListener()
    }

    private fun setupLoginButtonClickListener() {
        binding.buttonLogin.setOnClickListener {
            presenter.onLoginClick(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    override fun openNextActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun showError(message: String?) {
        message?.let { Snackbar.make(binding.coordinator, it, Snackbar.LENGTH_LONG).show() }
    }
}
