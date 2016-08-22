package ae.emaratech.devops.tools.healthcheck.client.api;


import java.util.Date;

/**
 * //TODO : Add Javadoc
 */
public class HealthCheckEventBuilder {


  private String id;
  private HealthCheckPing healthCheckPing;
  private String application;
  private String service;
  private HealthStatus status;
  private Date timestamp;
  private Long latency;
  private HealthCheckContext context;

  public HealthCheckEventBuilder(HealthCheckContext context) {
    this.context = context;
  }

  public HealthCheckEventBuilder healthCheckPing(HealthCheckPing healthCheckPing) {
    this.healthCheckPing = healthCheckPing;
    return this;
  }


  public HealthCheckEventBuilder service(String service) {
    this.service = service;
    return this;
  }


  public HealthCheckEventBuilder timestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }



  public HealthCheckEvent build(){

    return new HealthCheckEvent(service,context.getApplication(),healthCheckPing);

  }
}
