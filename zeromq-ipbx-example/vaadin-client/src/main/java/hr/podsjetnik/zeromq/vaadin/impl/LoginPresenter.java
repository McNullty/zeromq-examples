package hr.podsjetnik.zeromq.vaadin.impl;

import hr.podsjetnik.zeromq.vaadin.LoginView;
import hr.podsjetnik.zeromq.vaadin.LoginViewHandler;
import hr.podsjetnik.zeromq.vaadin.UserService;

import com.vaadin.server.ServiceException;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginPresenter implements LoginViewHandler {

  private final LoginView view;
  private final UserService service;

  public LoginPresenter(final LoginView view, final UserService service) {
    this.view = view;
    this.service = service;
  }

  @Override
  public void login() {
    final TextField txtUsername = view.getTxtUsername();
    final PasswordField txtPassword = view.getTxtPassword();

    final String username = txtUsername.getValue();
    final String password = txtPassword.getValue();

    if ("".equals(username) || "".equals(username)) {
      view.afterFailedLogin();
    } else {
      try {
        service.login(username, password);

        view.afterSuccessfulLogin(username);
      } catch (final ServiceException e) {
        view.afterFailedLogin();
      }
    }
  }
}
