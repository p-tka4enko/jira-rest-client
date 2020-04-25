package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Issue link type.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueLinkType extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String inward;
  @Getter @Setter private String outward;
}
