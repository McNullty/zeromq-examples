package hr.podsjetnik.zeromq.vaadin.impl;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;

import hr.podsjetnik.zeromq.vaadin.MapService;
import hr.podsjetnik.zeromq.vaadin.WorkView;

public class WorkViewImpl extends HorizontalLayout implements WorkView {

	private static final long serialVersionUID = 6567059036857570824L;
	private RichTextArea rta;
	private MapService map;
	
	public WorkViewImpl(final MapService map) {
		this.map = map;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@Override
	public void init() {
		rta = new RichTextArea((String)null);
		rta.setSizeFull();
		addComponent(rta);
		
		new MessageUpdater().start();
	}
	
	public void showNotice(final String remove) {
		Notification call = new Notification("Poziv uspostavljen u " + remove, Notification.Type.TRAY_NOTIFICATION);
		call.setDelayMsec(20000);
		call.setPosition(Position.BOTTOM_RIGHT);
		call.show(Page.getCurrent());		
	}

	
	class MessageUpdater extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				final String user = (String) VaadinSession.getCurrent().getAttribute(
						"username");
				// TODO: synchronize
				if (map.contains(user)) {
					getUI().access(new Runnable() {
						
						@Override
						public void run() {
							showNotice(map.remove(user));
						}
					});
				}
			}
		}

		
	}
}
