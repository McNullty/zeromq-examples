package hr.podsjetnik.zeromq.vaadin.impl;

import hr.podsjetnik.zeromq.vaadin.CallView;
import hr.podsjetnik.zeromq.vaadin.CallViewHandler;
import hr.podsjetnik.zeromq.vaadin.MapService;

public class CallPresenter implements CallViewHandler {
	
	private CallView view;

	public CallPresenter(CallView view, MapService map) {
		this.view = view;
	}

	@Override
	public void callPbx() {
		Integer caller = (Integer)view.getCallerId().getValue();
		System.out.println("Caller: " + caller);
	}

}
