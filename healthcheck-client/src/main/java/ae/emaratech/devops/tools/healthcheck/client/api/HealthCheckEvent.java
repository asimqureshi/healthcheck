package ae.emaratech.devops.tools.healthcheck.client.api;


import java.util.Date;
import java.util.UUID;

/**
 * //TODO : Add Javadoc
 */
public class HealthCheckEvent {

  private String id;
  private String application;
  private String service;
  private HealthStatus status;
  private String error;
  private Date timestamp;
  private Long latency;

  private HealthCheckPing healthCheckPing;


  // for jackson
  public HealthCheckEvent() {
  }

  public HealthCheckEvent(String service, String application,
                          HealthCheckPing healthCheckPing) {
    this.latency = latency;
    this.status = status;
    this.service = service;
    this.application = application;
    this.healthCheckPing = healthCheckPing;

    this.id = UUID.randomUUID().toString();
    this.timestamp = new Date(System.currentTimeMillis());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public HealthCheckPing getHealthCheckPing() {
    return healthCheckPing;
  }

  public void setHealthCheckPing(HealthCheckPing healthCheckPing) {
    this.healthCheckPing = healthCheckPing;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public HealthStatus getStatus() {
    return status;
  }

  public void setStatus(HealthStatus status) {
    this.status = status;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Long getLatency() {
    return latency;
  }

  public void setLatency(Long latency) {
    this.latency = latency;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "HealthCheckEvent{" +
           "id='" + id + '\'' +
           ", healthCheckPing=" + healthCheckPing +
           ", application='" + application + '\'' +
           ", service='" + service + '\'' +
           ", status=" + status +
           ", error='" + error + '\'' +
           ", timestamp=" + timestamp +
           ", latency=" + latency +
           '}';
  }
}
