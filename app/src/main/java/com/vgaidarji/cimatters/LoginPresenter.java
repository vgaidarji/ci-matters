package com.vgaidarji.cimatters;

class LoginPresenter {

    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "1111";
    private static final String WRONG_CREDENTIALS = "Wrong credentials.";
    private final LoginView view;

    LoginPresenter(LoginView view) {
        this.view = view;
    }

    void onLoginClick(String email, String password) {
        if (email.equals(EMAIL) && password.equals(PASSWORD)) {
            view.openNextActivity();
        } else {
            view.showError(WRONG_CREDENTIALS);
        }
    }
}
