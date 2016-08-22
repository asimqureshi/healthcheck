package ae.emaratech.devops.tools.healthcheck.client.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * //TODO : Add Javadoc
 */
@Component
public class SpringAwareJmsEventPublisher implements HealthCheckEventPublisher {

  private JmsTemplate jmsTemplate;
  private Serializer<List<HealthCheckEvent>> serializer;

  @Autowired
  public SpringAwareJmsEventPublisher(JmsTemplate jmsTemplate, Serializer<List<HealthCheckEvent>> serializer){

    this.jmsTemplate = jmsTemplate;
    this.serializer = serializer;
  }


  @Override
  public void publish(HealthCheckContext context, HealthCheckEvent event) {

    MessageCreator messageCreator = getMessageCreator(context, event.toString());

    jmsTemplate.convertAndSend(context.getReplyToQueue(), messageCreator);
  }

  @Override
  public void publish(final HealthCheckContext context, final List<HealthCheckEvent> events) {

    MessageCreator messageCreator = getMessageCreator(context, serializer.serialize(events));

    jmsTemplate.send(context.getReplyToQueue(), messageCreator);
  }

  private MessageCreator getMessageCreator(final HealthCheckContext context,
                                           final String stringMessage) {
    return new MessageCreator() {
        @Override
        public Message createMessage(Session session) throws JMSException {

          Message message = session.createTextMessage(stringMessage);
          message.setJMSCorrelationID(context.getCoorelationId());
          return message;
        }
      };
  }

}
