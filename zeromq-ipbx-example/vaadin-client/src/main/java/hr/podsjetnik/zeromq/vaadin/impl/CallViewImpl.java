package hr.podsjetnik.zeromq.vaadin.impl;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import hr.podsjetnik.zeromq.vaadin.CallView;
import hr.podsjetnik.zeromq.vaadin.CallViewHandler;
import hr.podsjetnik.zeromq.vaadin.MapService;

public class CallViewImpl extends VerticalLayout implements CallView {

	private static final long serialVersionUID = -7259582919347463868L;

	private ListSelect callerId;
	private TextField number;
	private Button call;
	private Button work;
	private CallViewHandler presenter;

	private MessageUpdater updater;

	@Override
	public void enter(ViewChangeEvent event) {
		updater = new MessageUpdater();
		updater.start();
		System.out.println("Started Call updater");
	}

	@Override
	public void init() {
		HorizontalLayout inputs = new HorizontalLayout();		
		callerId = new ListSelect("Izaberi broj pozivatelja");
		for (int i = 0; i < 3; i++) {
			callerId.addItem(330 + i);
			callerId.setItemCaption(330 + i, (new Integer(330 + i)).toString());
		}
		callerId.setNullSelectionAllowed(false);
		inputs.addComponent(callerId);
		inputs.setComponentAlignment(callerId, Alignment.MIDDLE_CENTER);

		number = new TextField("Telefonski broj:");
		inputs.addComponent(number);
		inputs.setComponentAlignment(number, Alignment.MIDDLE_CENTER);
		addComponent(inputs);
		setComponentAlignment(inputs, Alignment.MIDDLE_CENTER);

		HorizontalLayout buttons = new HorizontalLayout();
		call = new Button("Nazovi");
		call.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -5801090624232277853L;

			@Override
			public void buttonClick(ClickEvent event) {
				presenter.callPbx();
			}
		});		
		buttons.addComponent(call);
		buttons.setComponentAlignment(call, Alignment.MIDDLE_CENTER);

		work = new Button("Novi prozor");
		addComponent(work);
		work.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1910387531862979808L;

			@Override
			public void buttonClick(ClickEvent event) {
				updater.setStop();
				System.out.println("Stopped Call updater");
				UI.getCurrent().getNavigator().navigateTo("work");
			}
		});
		buttons.addComponent(work);
		buttons.setComponentAlignment(work, Alignment.MIDDLE_CENTER);
		
		addComponent(buttons);
		setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
		
		setSpacing(true);
		setMargin(true);
		//setSizeFull();
	}

	@Override
	public void setHandler(CallViewHandler handler) {
		presenter = handler;
	}

	@Override
	public Button getCallButton() {
		return call;
	}

	@Override
	public Button getWorkButton() {
		return work;
	}

	@Override
	public ListSelect getCallerId() {
		return callerId;
	}

	@Override
	public TextField getNumber() {
		return number;
	}

	public void showNotice(String remove) {
		Notification call = new Notification("Poziv uspostavljen u " + remove,
				Notification.Type.TRAY_NOTIFICATION);
		call.setDelayMsec(20000);
//		call.setPosition(Position.BOTTOM_RIGHT);
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
				final MapService map = presenter.getMapService();

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
