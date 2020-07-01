package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Priority.
 *
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Priority extends Resource {
  private String name;
  private String description;
}
