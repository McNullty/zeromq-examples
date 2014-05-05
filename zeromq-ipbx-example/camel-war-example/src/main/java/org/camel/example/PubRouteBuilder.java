package org.camel.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class PubRouteBuilder extends RouteBuilder {

	/**
	 * Let's configure the Camel routing rules using Java code...
	 */
	public void configure() {

		from("timer://foo?fixedRate=true&period=1000")
				.process(new Processor() {

					List<String> asList = Arrays.asList("coldplay", "keane",
							"jethro tull", "jack bruce", "elton john",
							"kate bush");

					@Override
					public void process(Exchange exchange) throws Exception {						
						Collections.shuffle(asList);
//						System.out.println("Sending message");
						exchange.getIn().setBody(asList.get(0));
					}
				})
				.to("zeromq:tcp://127.0.0.1:5563?socketType=PUBLISH&topics=bands,musicians,singers");
	}

}
