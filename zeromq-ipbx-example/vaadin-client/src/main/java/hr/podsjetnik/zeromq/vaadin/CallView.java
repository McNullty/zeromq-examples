package hr.podsjetnik.zeromq.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

public interface CallView extends View{
	void init();
	
	void setHandler(CallViewHandler handler);
	
	Button getCallButton();
	
	Button getWorkButton();
	
	ListSelect getCallerId();
	
	TextField getNumber();
}
