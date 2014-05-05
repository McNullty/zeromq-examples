package org.camel.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class SubRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("zeromq:tcp://127.0.0.1:5563?socketType=SUBSCRIBE").process(new Processor() {
			
			@Override
			public void process(Exchange in) throws Exception {		
				String message = new String((byte[]) in.getIn().getBody());				
				System.out.println(message);
			}
		});
    }

}
