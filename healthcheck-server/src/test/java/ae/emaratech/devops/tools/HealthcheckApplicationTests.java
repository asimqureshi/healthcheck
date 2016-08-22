package ae.emaratech.devops.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventPublisher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthcheckApplicationTests {

  @Test
  public void contextLoads() {

  }


  @Bean
  HealthCheckEventPublisher eventPub(@Autowired JmsTemplate jmsTemplate){

    //return new SpringAwareJmsEventPublisher(jmsTemplate);

    return null;
  }

  @EnableJms
  @Configuration
  public class JmsConfiguration {

    @Autowired
    private BeanFactory springContextBeanFactory;

    @Bean
    public DefaultJmsListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
      DefaultJmsListenerContainerFactory factory =
          new DefaultJmsListenerContainerFactory();
      factory.setConnectionFactory(connectionFactory);
      factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
      factory.setConcurrency("3-10");
      return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
      return new JmsTemplate(connectionFactory);
    }



  }

}
