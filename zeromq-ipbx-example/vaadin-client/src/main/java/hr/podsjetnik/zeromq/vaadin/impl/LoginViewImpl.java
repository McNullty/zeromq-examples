package hr.podsjetnik.zeromq.vaadin.impl;

import hr.podsjetnik.zeromq.vaadin.LoginView;
import hr.podsjetnik.zeromq.vaadin.LoginViewHandler;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginViewImpl extends HorizontalLayout implements LoginView {

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
		VerticalLayout layout = new VerticalLayout();
		txtUsername = new TextField("Username:");
		layout.addComponent(txtUsername);
		layout.setComponentAlignment(txtUsername, Alignment.MIDDLE_CENTER);
		txtPassword = new PasswordField("Password:");
		layout.addComponent(txtPassword);
		layout.setComponentAlignment(txtPassword, Alignment.MIDDLE_CENTER);

		btnLogin = new Button("Login");
		btnLogin.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 582630689354182376L;

			@Override
			public void buttonClick(final Button.ClickEvent clickEvent) {
				handler.login();
			}
		});
		btnLogin.setClickShortcut(KeyCode.ENTER);
		layout.addComponent(btnLogin);		
		layout.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);

		addComponent(layout);
		setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		setSizeFull();
		setSpacing(true);
		setMargin(true);
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
