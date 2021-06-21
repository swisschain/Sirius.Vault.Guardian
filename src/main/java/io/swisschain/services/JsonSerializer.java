package io.swisschain.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.io.Reader;

public class JsonSerializer {
  private final ObjectMapper mapper;

  public JsonSerializer() {
    var mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    this.mapper = mapper;
  }

  public String serialize(Object o) throws Exception {
    try {
      return mapper.writeValueAsString(o);
    } catch (JsonProcessingException exception) {
      throw new Exception("JSON serialization error.", exception);
    }
  }

  public <T> T deserialize(String content, Class<T> type) throws Exception {
    try {
      return mapper.readValue(content, type);
    } catch (JsonProcessingException exception) {
      throw new Exception("JSON deserialization error.", exception);
    }
  }

  public <T> T deserialize(Reader reader, Class<T> type) throws Exception {
    try {
      return mapper.readValue(reader, type);
    } catch (JsonProcessingException exception) {
      throw new Exception("JSON deserialization error.", exception);
    }
  }

  public <T> T deserialize(InputStream stream, Class<T> type) throws Exception {
    try {
      return mapper.readValue(stream, type);
    } catch (JsonProcessingException exception) {
      throw new Exception("JSON deserialization error.", exception);
    }
  }
}
