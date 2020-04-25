package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Issue type.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueType extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String description;
  @Getter @Setter private Boolean subtask;
}
