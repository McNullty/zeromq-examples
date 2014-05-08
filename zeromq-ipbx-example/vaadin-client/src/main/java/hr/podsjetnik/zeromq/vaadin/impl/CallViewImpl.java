package hr.podsjetnik.zeromq.vaadin.impl;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import hr.podsjetnik.zeromq.vaadin.CallView;
import hr.podsjetnik.zeromq.vaadin.CallViewHandler;

public class CallViewImpl extends VerticalLayout implements CallView {

	private static final long serialVersionUID = -7259582919347463868L;
	
	private ListSelect callerId;
	private TextField number;
	private Button call;
	private Button work;
	private CallViewHandler presenter;

	@Override
	public void enter(ViewChangeEvent event) {}

	@Override
	public void init() {
		callerId = new ListSelect("Izaberi broj pozivatelja");
		for(int i=0; i<3; i++){
			callerId.addItem(330+i);
			callerId.setItemCaption(330+i, (new Integer(330+i)).toString());
		}
		callerId.setNullSelectionAllowed(false);
		addComponent(callerId);
		
		number = new TextField("Telefonski broj:");
		addComponent(number);
		
		call = new Button("Nazovi");
		call.addClickListener(new ClickListener() {
	
			private static final long serialVersionUID = -5801090624232277853L;

			@Override
			public void buttonClick(ClickEvent event) {
				presenter.callPbx();
			}
		});
		addComponent(call);
		
		work = new Button("Novi prozor");
		addComponent(work);
				
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

}
