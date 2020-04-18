package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.StatusDeserializer;

/**
 * Status.
 *
 * @version 0.1
 */
@JsonDeserialize(using = StatusDeserializer.class)
public class Status extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String description;
}
