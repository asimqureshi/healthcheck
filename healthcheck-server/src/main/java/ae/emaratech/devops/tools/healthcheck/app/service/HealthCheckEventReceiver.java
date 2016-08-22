package ae.emaratech.devops.tools.healthcheck.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * //TODO : Add Javadoc
 */
@Component
public class HealthCheckEventReceiver {

  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;

  @Value("${hc.websocket.queue}")
  String hcWebSocketQueue;

  public HealthCheckEventReceiver() {
    System.out.println();
  }

  /**
   * When you receive a message, print it out, then shut down the application.
   * Finally, clean up any ActiveMQ server stuff.
   */
  @JmsListener(destination = "${hc.reply.queue}")
  public void receiveMessage(String message) {

    System.out.println("Received <" + message + ">");

    simpMessagingTemplate.convertAndSend(hcWebSocketQueue, message);

  }

}
