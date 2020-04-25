package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Project.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends Resource {
  @Getter @Setter private String key;
  @Getter @Setter private String name;
  @Getter @Setter private String description;
  @Getter @Setter private User lead;
}
