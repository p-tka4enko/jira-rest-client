package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.IssueDeserializer;

import java.util.Map;

/**
 * Issue.
 *
 * @version 0.1
 */
@JsonDeserialize(using = IssueDeserializer.class)
public class Issue extends Resource {
  @Getter @Setter private String key;
  @Getter @Setter private Map<String, Object> fields;
}