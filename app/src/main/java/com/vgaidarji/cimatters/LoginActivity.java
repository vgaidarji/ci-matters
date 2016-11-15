package com.vgaidarji.cimatters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @InjectView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @InjectView(R.id.edit_text_email)
    EditText editTextEmail;
    @InjectView(R.id.edit_text_password)
    EditText editTextPassword;
    @InjectView(R.id.button_login)
    Button buttonLogin;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getString(R.string.activity_login));
        ButterKnife.inject(this);
        presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        presenter.onLoginClick(
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString()
        );
    }

    @Override
    public void openNextActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }
}
