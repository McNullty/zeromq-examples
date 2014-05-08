package hr.podsjetnik.zeromq.vaadin.impl;

import com.vaadin.server.VaadinSession;

import hr.podsjetnik.zeromq.common.data.CallInput;
import hr.podsjetnik.zeromq.common.data.CallOutput;
import hr.podsjetnik.zeromq.ipbx.IpbxRestService;
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
		String number = view.getNumber().getValue();
		
		CallInput ci = new CallInput();
		ci.setDestination(number);
		ci.setSource(caller.toString());
		ci.setService_id(1);
		
		String user = (String) VaadinSession.getCurrent().getAttribute("username");
		ci.setExternal_ref(user);
		
		IpbxRestService irs = new IpbxRestService();
		
		CallOutput co  = irs.callPbx(ci);
		System.out.println("output status: " + co.getStatus());
	}

}
