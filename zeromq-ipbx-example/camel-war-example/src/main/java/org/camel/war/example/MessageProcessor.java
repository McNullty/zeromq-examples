package org.camel.war.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class MessageProcessor implements Processor {

	@Override
	public void process(Exchange in) throws Exception {
		String message = new String((byte[]) in.getIn().getBody());				
		System.out.println(message);
	}

}
