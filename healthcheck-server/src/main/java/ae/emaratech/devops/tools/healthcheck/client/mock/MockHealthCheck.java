package ae.emaratech.devops.tools.healthcheck.client.mock;


import java.util.Date;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheck;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckContext;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEvent;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventBuilder;

/**
 * //TODO : Add Javadoc
 */
public class MockHealthCheck implements HealthCheck {

  private String service;

  public MockHealthCheck(String service) {
    this.service = service;
  }

  @Override
  public HealthCheckEvent perform(HealthCheckContext context) {

    HealthCheckEventBuilder builder = new HealthCheckEventBuilder(context);

    return builder
        .service(service)
        .timestamp(new Date()).build();

  }

  private static long random(int minimum, int maximum) {

    return minimum + (long)(Math.random() * maximum);
  }
}
