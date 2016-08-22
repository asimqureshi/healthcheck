package ae.emaratech.devops.tools.healthcheck.client.api;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * //TODO : Add Javadoc
 */
public class HealthCheckExecutor {


  public HealthCheckExecutor(HealthCheck[] healthChecks,
                             HealthCheckEventPublisher eventPublisher) {
    this.healthChecks = healthChecks;
    this.eventPublisher = eventPublisher;
  }

  private HealthCheckEventPublisher eventPublisher;
  private HealthCheck[] healthChecks;

  public void performAndSubmit(HealthCheckPing healthCheckPing, Long timeout){

    final HealthCheckContext context = new HealthCheckContext();
    context.setApplication(healthCheckPing.getApplication());
    context.setReplyToQueue(healthCheckPing.getReplyToQueue());
    context.setCoorelationId(healthCheckPing.getId());

    List<HealthCheckEvent> events = new ArrayList<HealthCheckEvent>(healthChecks.length);



    for (final HealthCheck healthCheck : healthChecks) {

      HealthCheckEvent event = null;

      Future<HealthCheckEvent> healthCheckEventFuture = execute(context, healthCheck);

      try {

        event = healthCheckEventFuture.get(timeout, TimeUnit.SECONDS);

      } catch (Exception e) {
        e.printStackTrace();
        event.setStatus(HealthStatus.TIMED_OUT);
        event.setError(e.getMessage());
      }

      events.add(event);

    }

    eventPublisher.publish(context, events);

  }

  private Future<HealthCheckEvent> execute(final HealthCheckContext context,
                                           final HealthCheck healthCheck) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    return executor.submit(new Callable<HealthCheckEvent>() {
          @Override
          public HealthCheckEvent call() {

            HealthCheckEvent localEvent = null;

            try {

              long now = System.currentTimeMillis();

              localEvent = healthCheck.perform(context);
              localEvent.setStatus(HealthStatus.OK);
              localEvent.setLatency(System.currentTimeMillis() - now);

            } catch (Exception e) {

              localEvent.setStatus(HealthStatus.NOT_OK);
              localEvent.setError(e.getMessage());
              e.printStackTrace();

            }

            return localEvent;
          }
        });
  }


}
