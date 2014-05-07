package hr.podsjetnik.zeromq.vaadin;

import hr.podsjetnik.zeromq.ipbx.MessageProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"hr.podsjetnik.zeromq.ipbx"})
public class AppConfig extends SingleRouteCamelConfiguration {

	@Bean(name = "mapService")
	public MapService getMapService() {
		return new MapService();
	}

	@Override
	public RouteBuilder route() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("zeromq:tcp://127.0.0.1:5563?socketType=SUBSCRIBE")
						.process(new MessageProcessor());

			}
		};
	}
}
