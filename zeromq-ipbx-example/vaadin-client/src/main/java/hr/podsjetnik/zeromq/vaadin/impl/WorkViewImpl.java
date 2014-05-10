package hr.podsjetnik.zeromq.vaadin.impl;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

import hr.podsjetnik.zeromq.vaadin.MapService;
import hr.podsjetnik.zeromq.vaadin.WorkView;

public class WorkViewImpl extends VerticalLayout implements WorkView {

	private static final long serialVersionUID = 6567059036857570824L;
	private RichTextArea rta;
	private MapService map;
	private Button back;

	private MessageUpdater updater;

	public WorkViewImpl(final MapService map) {
		this.map = map;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		updater = new MessageUpdater();
		updater.start();
	}

	@Override
	public void init() {
		rta = new RichTextArea((String) null);
		rta.setSizeFull();
		addComponent(rta);

		back = new Button("Natrag");
		back.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 6758743957959187704L;

			@Override
			public void buttonClick(ClickEvent event) {
				updater.setStop();
				UI.getCurrent().getNavigator().navigateTo("call");
			}
		});
		addComponent(back);
	}

	public void showNotice(final String remove) {
		Notification call = new Notification("Poziv uspostavljen u " + remove,
				Notification.Type.TRAY_NOTIFICATION);
		call.setDelayMsec(20000);
		call.show(Page.getCurrent());
	}

	class MessageUpdater extends Thread {
		private boolean stop = false;

		public void setStop() {
			stop = true;
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				final String user = (String) VaadinSession.getCurrent()
						.getAttribute("username");

				synchronized (map) {
					if (map.contains(user)) {
						if (!stop) {
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

	}
}
