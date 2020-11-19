package net.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.util.Mapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue extends Resource {
  private String key;
  private String summary;
  private String description;
  private User reporter;
  private User assignee;
  private ZonedDateTime created;
  private ZonedDateTime updated;
  private ZonedDateTime dueDate;
  private ZonedDateTime resolutionDate;
  private TimeTracking timeTracking;
  private IssueType issueType;
  private Status status;
  private Priority priority;
  private Resolution resolution;
  private String parentId;
  private List<String> subtaskIds;

  @JsonProperty("fields")
  @SuppressWarnings("unchecked")
  private void deserializeFields(Map<String, Object> fields) {
    summary = (String) fields.get("summary");
    description = (String) fields.get("description");
    reporter = Mapper.get().convertValue(fields.get("reporter"), User.class);
    assignee = Mapper.get().convertValue(fields.get("assignee"), User.class);
    created = getZonedDateTime((String) fields.get("created"));
    updated = getZonedDateTime((String) fields.get("updated"));
    dueDate = getZonedDateTime((String) fields.get("duedate"));
    resolutionDate = getZonedDateTime((String) fields.get("resolutiondate"));
    timeTracking = Mapper.get().convertValue(fields.get("timetracking"), TimeTracking.class);
    issueType = Mapper.get().convertValue(fields.get("issuetype"), IssueType.class);
    status = Mapper.get().convertValue(fields.get("status"), Status.class);
    priority = Mapper.get().convertValue(fields.get("priority"), Priority.class);
    resolution = Mapper.get().convertValue(fields.get("resolution"), Resolution.class);

    if (fields.containsKey("parent")) {
      parentId = (String) ((Map<String, Object>) fields.get("parent")).get("id");
    }

    if (fields.containsKey("subtasks")) {
      subtaskIds = ((List<Map<String, Object>>) fields.get("subtasks"))
          .stream()
          .map(subtaskField -> (String) subtaskField.get("id"))
          .collect(Collectors.toList());
    }
  }

  private ZonedDateTime getZonedDateTime(String date) {
    if (date == null) {
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    return ZonedDateTime.parse(date, formatter);
  }
}
