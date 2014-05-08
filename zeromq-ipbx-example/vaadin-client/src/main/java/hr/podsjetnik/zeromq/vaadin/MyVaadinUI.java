package hr.podsjetnik.zeromq.vaadin;

import hr.podsjetnik.zeromq.vaadin.impl.CallPresenter;
import hr.podsjetnik.zeromq.vaadin.impl.CallViewImpl;
import hr.podsjetnik.zeromq.vaadin.impl.LoginPresenter;
import hr.podsjetnik.zeromq.vaadin.impl.LoginViewImpl;
import hr.podsjetnik.zeromq.vaadin.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
@Push
public class MyVaadinUI extends UI {
	@WebServlet(urlPatterns = {"/app/*", "/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "hr.podsjetnik.zeromq.vaadin.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	private ApplicationContext context;

	@Override
	protected void init(VaadinRequest request) {
		final Navigator navigator = new Navigator(this, this);

		MapService map = getMapService(request);

		final CallView call = new CallViewImpl();		
		final CallViewHandler presenter = new CallPresenter(call, map);
		call.setHandler(presenter);
		call.init();
		navigator.addView("call", call);
		
		
		final LoginView loginView = new LoginViewImpl();
	    final LoginPresenter loginPresenter = new LoginPresenter(loginView, new UserServiceImpl());
	    loginView.setHandler(loginPresenter);
	    loginView.init();
	    navigator.addView("", loginView);
	}

	private MapService getMapService(VaadinRequest request) {
		WrappedSession session = request.getWrappedSession();
		HttpSession httpSession = ((WrappedHttpSession) session)
				.getHttpSession();
		ServletContext servletContext = httpSession.getServletContext();
		context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);

		return (MapService) context.getBean("mapService");
	}

}
