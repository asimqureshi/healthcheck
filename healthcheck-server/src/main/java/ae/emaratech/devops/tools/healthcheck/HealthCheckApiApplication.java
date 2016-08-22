package ae.emaratech.devops.tools.healthcheck;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheck;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEvent;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventPublisher;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckExecutor;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckPing;
import ae.emaratech.devops.tools.healthcheck.client.api.JsonSerializer;
import ae.emaratech.devops.tools.healthcheck.client.api.Serializer;
import ae.emaratech.devops.tools.healthcheck.client.mock.MockHealthCheck;

/**
 * //TODO : Add Javadoc
 */
@SpringBootApplication
@EnableJms
@EnableWebSocketMessageBroker
@EnableAsync
public class HealthCheckApiApplication extends AbstractWebSocketMessageBrokerConfigurer {

  public static void main(String[] args) {
    ConfigurableApplicationContext
        ctx = SpringApplication.run(HealthCheckApiApplication.class, args);

  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic", "/queue");
    config.setApplicationDestinationPrefixes("/app");

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/hello").withSockJS();
  }

  @Bean
  public Serializer<List<HealthCheckEvent>> healthCheckEventListSeralizer(){
    return new JsonSerializer<List<HealthCheckEvent>>();
  }

  @Bean
  public Serializer<HealthCheckEvent> healthCheckEventSeralizer(){
    return new JsonSerializer<HealthCheckEvent>();
  }



}
