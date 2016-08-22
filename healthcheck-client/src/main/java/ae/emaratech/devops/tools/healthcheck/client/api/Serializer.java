package ae.emaratech.devops.tools.healthcheck.client.api;

/**
 * //TODO : Add Javadoc
 */
public interface Serializer<T> {

  String serialize(T obj);
  T deserialize(String json, Class<T> clazz);

}
