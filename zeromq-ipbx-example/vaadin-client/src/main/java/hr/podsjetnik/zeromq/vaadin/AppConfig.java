package hr.podsjetnik.zeromq.vaadin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean(name = "mapService")
  public MapService getMapService() {
    return new MapService();
  }
}
