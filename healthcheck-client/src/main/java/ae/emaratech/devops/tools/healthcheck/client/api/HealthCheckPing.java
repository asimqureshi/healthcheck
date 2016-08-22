package ae.emaratech.devops.tools.healthcheck.client.api;


import java.util.Date;
import java.util.UUID;

/**
 * //TODO : Add Javadoc
 */
public class HealthCheckPing {

  private String id;
  private String application;
  private Date timestamp;
  private String replyToQueue;

  // for jackson
  public HealthCheckPing() {
  }

  public HealthCheckPing(String application, String replyToQueue) {
    this.application = application;
    this.replyToQueue = replyToQueue;

    this.id = UUID.randomUUID().toString();
    this.timestamp = new Date(System.currentTimeMillis());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getReplyToQueue() {
    return replyToQueue;
  }

  public void setReplyToQueue(String replyToQueue) {
    this.replyToQueue = replyToQueue;
  }

  @Override
  public String toString() {
    return "HealthCheckPing{" +
           "id='" + id + '\'' +
           ", application='" + application + '\'' +
           ", timestamp=" + timestamp +
           '}';
  }
}
