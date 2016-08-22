package ae.emaratech.devops.tools.healthcheck.client;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheck;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventPublisher;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckExecutor;
import ae.emaratech.devops.tools.healthcheck.client.mock.MockHealthCheck;


/**
 * //TODO : Add Javadoc
 */
@Configuration
public class HealthCheckTestConfiguration {


  @Bean
  public HealthCheckExecutor healthCheckExecutor(HealthCheckEventPublisher eventPublisher){


    HealthCheck[] healthChecks = new HealthCheck[]{new MockHealthCheck("vision_app")
        , new MockHealthCheck("vision_est")};

    HealthCheckExecutor executor = new HealthCheckExecutor(healthChecks, eventPublisher);

    return executor;

  }

}
