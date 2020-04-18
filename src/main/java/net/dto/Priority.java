package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.PriorityDeserializer;

/**
 * Priority.
 *
 * @version 0.1
 */
@JsonDeserialize(using = PriorityDeserializer.class)
public class Priority extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String description;
}
