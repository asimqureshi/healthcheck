package ae.emaratech.devops.tools.healthcheck.client.api;


import java.util.List;

/**
 * //TODO : Add Javadoc
 */
public interface HealthCheckEventPublisher {


  public void publish(HealthCheckContext context, HealthCheckEvent event);
  public void publish(HealthCheckContext context, List<HealthCheckEvent> events);

}
