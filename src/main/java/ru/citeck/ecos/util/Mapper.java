package ru.citeck.ecos.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Mapper {
  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

  public static ObjectMapper get() {
    return mapper;
  }
}
