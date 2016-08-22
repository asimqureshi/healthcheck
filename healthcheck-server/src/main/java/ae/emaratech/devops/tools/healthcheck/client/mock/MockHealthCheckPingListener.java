package ae.emaratech.devops.tools.healthcheck.client.mock;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import ae.emaratech.devops.tools.healthcheck.client.api.*;

/**
 * //TODO : Add Javadoc
 */
@Component
public class MockHealthCheckPingListener implements MessageListener {


  @Autowired
  HealthCheckEventPublisher eventPublisher;

  @Autowired
  Serializer<HealthCheckPing> serializer;

  @Autowired
  HealthCheckExecutor executor;

//  @JmsListener(destination = "${hc.request.queue}")
  @Override
  public void onMessage(Message messageIn) {

    String message = "";

    try {
      message = ((TextMessage) messageIn).getText();
      System.out.println("Received <" + message + ">");

      HealthCheckPing ping = serializer.deserialize(message, HealthCheckPing.class);

      executor.performAndSubmit(ping, 1L);

    } catch (JMSException e) {
      e.printStackTrace();
    }

  }
}
