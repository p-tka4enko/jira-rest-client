package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.ResolutionDeserializer;

/**
 * Resolution.
 *
 * @version 0.1
 */
@JsonDeserialize(using = ResolutionDeserializer.class)
public class Resolution extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String description;
}
