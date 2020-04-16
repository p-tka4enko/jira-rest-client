package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import jackson.deserializer.IssueTypeDeserializer;

/**
 * Issue type.
 *
 * @version 0.1
 */
@JsonDeserialize(using = IssueTypeDeserializer.class)
public class IssueType extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private String description;
  @Getter @Setter private Boolean subtask;
}
