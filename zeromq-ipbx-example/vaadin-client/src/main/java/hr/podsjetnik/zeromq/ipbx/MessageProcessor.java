package hr.podsjetnik.zeromq.ipbx;

import hr.podsjetnik.zeromq.common.data.CallEvent;
import hr.podsjetnik.zeromq.common.service.CallInputService;
import hr.podsjetnik.zeromq.vaadin.MapService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class MessageProcessor implements Processor {
	private MapService map;

	public MessageProcessor(MapService map) {
		this.map = map; 
	}

	@Override
	public void process(Exchange in) throws Exception {
		String message = new String((byte[]) in.getIn().getBody());
		
		if(map!=null){
			System.out.println("Session mapa je inicijalizirana");
		}
		
		System.out.println(message);
		
		CallEvent event = CallInputService.convertJsonEventToJava(message);
		map.setMessage(event.getExternal_ref(), event.getTimestamp());
		System.out.println("Dodao u mapu: " + event.getExternal_ref() + ": " + event.getTimestamp());
		System.out.println(map);
	}

}
