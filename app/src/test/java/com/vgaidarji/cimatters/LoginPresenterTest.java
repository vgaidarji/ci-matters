package com.vgaidarji.cimatters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class LoginPresenterTest {

    private LoginView view;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(LoginView.class);
        presenter = new LoginPresenter(view);
    }

    @Test
    public void onLoginClick_shouldOpenNextActivityForAllowedCredentials() throws Exception {
        presenter.onLoginClick("test@test.com", "1111");

        verify(view).openNextActivity();
    }

    @Test
    public void onLoginClick_shouldShowErrorForIncorrectCredentials() throws Exception {
        presenter.onLoginClick("wrong@email.com", "not_a_password");

        verify(view).showError(anyString());
    }
}
