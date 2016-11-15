package com.vgaidarji.cimatters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.edit_text_email)
    EditText editTextEmail;
    @InjectView(R.id.edit_text_password)
    EditText editTextPassword;
    @InjectView(R.id.button_login)
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getString(R.string.login_activity));
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_login)
    public void inLoginClick() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
