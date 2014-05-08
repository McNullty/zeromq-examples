package hr.podsjetnik.zeromq.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public interface LoginView extends View {

  void setHandler(LoginViewHandler handler);

  void init();

  TextField getTxtUsername();

  PasswordField getTxtPassword();

  Button getBtnLogin();

  void afterSuccessfulLogin(String user);

  void afterFailedLogin();
}
