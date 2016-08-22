package ae.emaratech.devops.tools.healthcheck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheck;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckEventPublisher;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckExecutor;
import ae.emaratech.devops.tools.healthcheck.client.api.HealthCheckPing;
import ae.emaratech.devops.tools.healthcheck.client.mock.MockHealthCheck;

/**
 * //TODO : Add Javadoc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = {HealthcheckApplicationTests.class, HealthcheckApplicationTests.JmsConfiguration.class})
//@ComponentScan(basePackages = "ae.emaratech.devops.tools.healthcheck.api")
public class HealthCheckExecutorTest {

  @Autowired
  HealthCheckEventPublisher eventPublisher;

  @Test
  public void completeEndToEndTest(){

    HealthCheck[] healthChecks = new HealthCheck[]{new MockHealthCheck("vision_app")
        , new MockHealthCheck("vision_est")};

    HealthCheckExecutor executor = new HealthCheckExecutor(healthChecks, eventPublisher);

    HealthCheckPing ping = new HealthCheckPing("VC", "");

   // executor.performAndSubmit(ping);

  }


}
