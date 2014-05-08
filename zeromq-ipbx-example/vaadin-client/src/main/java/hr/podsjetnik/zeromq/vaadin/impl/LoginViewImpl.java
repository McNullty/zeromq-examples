package hr.podsjetnik.zeromq.vaadin.impl;

import hr.podsjetnik.zeromq.vaadin.LoginView;
import hr.podsjetnik.zeromq.vaadin.LoginViewHandler;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginViewImpl extends VerticalLayout implements LoginView {

	private static final long serialVersionUID = 5725359388678494023L;

	private LoginViewHandler handler;

	private TextField txtUsername;
	private PasswordField txtPassword;

	private Button btnLogin;

	@Override
	public void setHandler(final LoginViewHandler handler) {
		this.handler = handler;
	}

	@Override
	public void init() {
		txtUsername = new TextField("Username:");
		addComponent(txtUsername);
		txtPassword = new PasswordField("Password:");
		addComponent(txtPassword);

		btnLogin = new Button("Login");
		addComponent(btnLogin);
		btnLogin.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 582630689354182376L;

			@Override
			public void buttonClick(final Button.ClickEvent clickEvent) {
				handler.login();
			}
		});
	}

	@Override
	public TextField getTxtUsername() {
		return txtUsername;
	}

	@Override
	public PasswordField getTxtPassword() {
		return txtPassword;
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent viewChangeEvent) {
	}

	@Override
	public Button getBtnLogin() {
		return btnLogin;
	}

	@Override
	public void afterSuccessfulLogin(String user) {
		VaadinSession.getCurrent().setAttribute("username", user);
		UI.getCurrent().getNavigator().navigateTo("call");
	}

	@Override
	public void afterFailedLogin() {
		UI.getCurrent().getNavigator().navigateTo("");
	}
}
