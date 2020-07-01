package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Issue type.
 *
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueType extends Resource {
  private String name;
  private String description;
  private Boolean subtask;
}
