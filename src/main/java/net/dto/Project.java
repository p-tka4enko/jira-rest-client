package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.ProjectDeserializer;

/**
 * Project.
 *
 * @version 0.1
 */
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project extends Resource {
  @Getter @Setter private String key;
  @Getter @Setter private String name;
  @Getter @Setter private String description;
  @Getter @Setter private User lead;
}
