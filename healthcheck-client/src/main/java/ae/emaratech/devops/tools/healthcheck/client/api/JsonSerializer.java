package ae.emaratech.devops.tools.healthcheck.client.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * //TODO : Add Javadoc
 */
public class JsonSerializer<T> implements Serializer<T> {


  @Override
  public String serialize(T obj) {

    ObjectMapper mapper = new ObjectMapper();

    String retStr = "";

    try {
      retStr = mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("Issue in making json for " + obj);
    }

    return retStr;
  }

  @Override
  public T deserialize(String json, Class<T> clazz) {

    ObjectMapper mapper = new ObjectMapper();
    T obj = null;
    try {
      obj = mapper.readValue(json, clazz);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Issue in parsing json for " + json);

    }

    return obj;
  }
}
