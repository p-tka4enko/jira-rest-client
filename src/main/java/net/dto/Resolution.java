package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Resolution.
 *
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resolution extends Resource {
  private String name;
  private String description;
}
