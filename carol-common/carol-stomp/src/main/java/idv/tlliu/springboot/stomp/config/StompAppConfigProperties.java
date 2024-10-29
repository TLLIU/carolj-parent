package idv.tlliu.springboot.stomp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="app")
public class StompAppConfigProperties {
  private String websocketEndpoint;
  private String[] simpleBrokerDestinations;
  private String applicationDestinationPrefix;
  
  public String getApplicationDestinationPrefix() {
    return applicationDestinationPrefix;
  }
  public void setApplicationDestinationPrefix(String applicationDestinationPrefix) {
    this.applicationDestinationPrefix = applicationDestinationPrefix;
  }
  public String[] getSimpleBrokerDestinations() {
    return simpleBrokerDestinations;
  }
  public void setSimpleBrokerDestinations(String[] simpleBrokerDestinations) {
    this.simpleBrokerDestinations = simpleBrokerDestinations;
  }
  public String getWebsocketEndpoint() {
    return websocketEndpoint;
  }
  public void setWebsocketEndpoint(String websocketEndpoint) {
    this.websocketEndpoint = websocketEndpoint;
  }

}
