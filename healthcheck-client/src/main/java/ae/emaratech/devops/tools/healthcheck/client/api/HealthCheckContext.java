package ae.emaratech.devops.tools.healthcheck.client.api;


/**
 * //TODO : Add Javadoc
 */
public class HealthCheckContext {

  private String application;
  private String replyToQueue;
  private String coorelationId;

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getReplyToQueue() {
    return replyToQueue;
  }

  public void setReplyToQueue(String replyToQueue) {
    this.replyToQueue = replyToQueue;
  }

  public String getCoorelationId() {
    return coorelationId;
  }

  public void setCoorelationId(String coorelationId) {
    this.coorelationId = coorelationId;
  }

  @Override
  public String toString() {
    return "HealthCheckContext{" +
           "application='" + application + '\'' +
           ", replyToQueue='" + replyToQueue + '\'' +
           ", coorelationId='" + coorelationId + '\'' +
           '}';
  }
}
