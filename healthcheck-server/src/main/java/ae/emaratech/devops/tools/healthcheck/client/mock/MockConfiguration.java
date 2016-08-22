package ae.emaratech.devops.tools.healthcheck.client.mock;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheck;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventPublisher;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckExecutor;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckPing;
import ae.emaratech.devops.tools.healthcheck.client.api.JsonSerializer;
import ae.emaratech.devops.tools.healthcheck.client.api.Serializer;

/**
 * //TODO : Add Javadoc
 */
@Configuration
public class MockConfiguration {

  @Bean
  public Serializer<HealthCheckPing> healthCheckPingSeralizer(){
    return new JsonSerializer<HealthCheckPing>();
  }

  @Bean
  public HealthCheckExecutor healthCheckExecutor(HealthCheckEventPublisher eventPublisher){


    HealthCheck[] healthChecks = new HealthCheck[]{new MockHealthCheck("vision_app")
        , new MockHealthCheck("vision_est")};

    HealthCheckExecutor executor = new HealthCheckExecutor(healthChecks, eventPublisher);

    return executor;

  }

  @Value("VC.HC.PING.QUEUE")
  String hcRequestQueue;


  @Bean
  public DefaultMessageListenerContainer pingQueueListenerContainer(
      ConnectionFactory connectionFactory, MessageListener messageListener)
//  ,   PlatformTransactionManager transactionManager)
  {

    DefaultMessageListenerContainer pingQueueListenerContainer = new DefaultMessageListenerContainer();
    pingQueueListenerContainer.setConnectionFactory(connectionFactory);

    ActiveMQQueue replyQueue = new ActiveMQQueue();
    replyQueue.setPhysicalName(hcRequestQueue);
    pingQueueListenerContainer.setDestination(replyQueue);

    pingQueueListenerContainer.setMessageListener(messageListener);
//    pingQueueListenerContainer.setTransactionManager(transactionManager);

    return pingQueueListenerContainer;
  }


}
