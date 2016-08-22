package ae.emaratech.devops.tools.healthcheck.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckPing;
import ae.emaratech.devops.tools.healthcheck.client.api.Serializer;

@Service
public class HealthCheckService {

  @Autowired
  JmsTemplate jmsTemplate;

  @Value("${hc.request.queue.suffix}")
  String hcRequestQ;

  @Value("${hc.reply.queue}")
  String hcReplyQ;

  @Autowired
  Serializer<HealthCheckPing> serializer;

  @Async
  public Future<String> submitHealthCheckRequest(String[] apps){

    for (String app : apps) {

      final HealthCheckPing ping = new HealthCheckPing(app, hcReplyQ);

      MessageCreator messageCreator = new MessageCreator() {
        @Override
        public Message createMessage(Session session) throws JMSException {
          return session.createTextMessage(serializer.serialize(ping));
        }
      };

      System.out.println("Sending some messages.");

      jmsTemplate.send(app.toUpperCase() + "." + hcRequestQ, messageCreator);

    }

    return new AsyncResult<String>("Request sent!");
  }

  private void sleep() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }

}
