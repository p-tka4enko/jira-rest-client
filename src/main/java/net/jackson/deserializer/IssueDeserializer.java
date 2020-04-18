package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.*;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class IssueDeserializer extends StdDeserializer<Issue> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String KEY_PROP = "key";
  private static final String FIELDS_PROP = "fields";
  private static final String SUMMARY_PROP = "summary";
  private static final String DESCRIPTION_PROP = "description";
  private static final String REPORTER_PROP = "reporter";
  private static final String ASSIGNEE_PROP = "assignee";
  private static final String CREATED_PROP = "created";
  private static final String UPDATED_PROP = "updated";
  private static final String DUE_DATE_PROP = "duedate";
  private static final String RESOLUTION_DATE_PROP = "resolutiondate";
  private static final String TIME_TRACKING_PROP = "timetracking";
  private static final String ISSUE_TYPE_PROP = "issuetype";
  private static final String STATUS_PROP = "status";
  private static final String PRIORITY_PROP = "priority";
  private static final String RESOLUTION_PROP = "resolution";
  private static final String PARENT_PROP = "parent";
  private static final String SUBTASKS_PROP = "subtasks";

  public IssueDeserializer() {
    this(null);
  }

  public IssueDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Issue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    Issue issue = new Issue();
    Map<String, Object> fields = new HashMap<>();
    issue.setFields(fields);

    ObjectCodec codec = jsonParser.getCodec();
    JsonNode rootNode = codec.readTree(jsonParser);

    String self = rootNode.hasNonNull(SELF_PROP) ? rootNode.get(SELF_PROP).asText() : null;
    issue.setSelf(self);

    String id = rootNode.hasNonNull(ID_PROP) ? rootNode.get(ID_PROP).asText() : null;
    issue.setId(id);

    String key = rootNode.hasNonNull(KEY_PROP) ? rootNode.get(KEY_PROP).asText() : null;
    issue.setKey(key);

    JsonNode fieldsNode = rootNode.get(FIELDS_PROP);
    if (fieldsNode != null) {
      String summary = fieldsNode.hasNonNull(SUMMARY_PROP) ? fieldsNode.get(SUMMARY_PROP).asText() : null;
      fields.put(SUMMARY_PROP, summary);

      String description = fieldsNode.hasNonNull(DESCRIPTION_PROP) ? fieldsNode.get(DESCRIPTION_PROP).asText() : null;
      fields.put(DESCRIPTION_PROP, description);

      User reporter = fieldsNode.hasNonNull(REPORTER_PROP) ? codec.treeToValue(fieldsNode.get(REPORTER_PROP), User.class) : null;
      fields.put(REPORTER_PROP, reporter);

      User assignee = fieldsNode.hasNonNull(ASSIGNEE_PROP) ? codec.treeToValue(fieldsNode.get(ASSIGNEE_PROP), User.class) : null;
      fields.put(ASSIGNEE_PROP, assignee);

      String createdStr = fieldsNode.hasNonNull(CREATED_PROP) ? fieldsNode.get(CREATED_PROP).asText() : null;
      ZonedDateTime created = getDateTime(createdStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      fields.put(CREATED_PROP, created);

      String updatedStr = fieldsNode.hasNonNull(UPDATED_PROP) ? fieldsNode.get(UPDATED_PROP).asText() : null;
      ZonedDateTime updated = getDateTime(updatedStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      fields.put(UPDATED_PROP, updated);

      String dueDateStr = fieldsNode.hasNonNull(DUE_DATE_PROP) ? fieldsNode.get(DUE_DATE_PROP).asText() : null;
      ZonedDateTime dueDate = getDateTime(dueDateStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      fields.put(DUE_DATE_PROP, dueDate);

      String resolutionDateStr = fieldsNode.hasNonNull(RESOLUTION_DATE_PROP) ? fieldsNode.get(RESOLUTION_DATE_PROP).asText() : null;
      ZonedDateTime resolutionDate = getDateTime(resolutionDateStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      fields.put(RESOLUTION_DATE_PROP, resolutionDate);

      TimeTracking timeTracking = fieldsNode.hasNonNull(TIME_TRACKING_PROP) ? codec.treeToValue(fieldsNode.get(TIME_TRACKING_PROP), TimeTracking.class) : null;
      fields.put(TIME_TRACKING_PROP, timeTracking);

      IssueType issueType = fieldsNode.hasNonNull(ISSUE_TYPE_PROP) ? codec.treeToValue(fieldsNode.get(ISSUE_TYPE_PROP), IssueType.class) : null;
      fields.put(ISSUE_TYPE_PROP, issueType);

      Status status = fieldsNode.hasNonNull(STATUS_PROP) ? codec.treeToValue(fieldsNode.get(STATUS_PROP), Status.class) : null;
      fields.put(STATUS_PROP, status);

      Priority priority = fieldsNode.hasNonNull(PRIORITY_PROP) ? codec.treeToValue(fieldsNode.get(PRIORITY_PROP), Priority.class) : null;
      fields.put(PRIORITY_PROP, priority);

      Resolution resolution = fieldsNode.hasNonNull(RESOLUTION_PROP) ? codec.treeToValue(fieldsNode.get(RESOLUTION_PROP), Resolution.class) : null;
      fields.put(RESOLUTION_PROP, resolution);

      JsonNode parentNode = fieldsNode.get(PARENT_PROP);
      if (parentNode != null) {
        String parentId = parentNode.hasNonNull(ID_PROP) ? parentNode.get(ID_PROP).asText() : null;
        fields.put(PARENT_PROP, parentId);
      }

      JsonNode subtasksNode = fieldsNode.get(SUBTASKS_PROP);
      if (subtasksNode != null && subtasksNode.isArray()) {
        List<String> subtasks = new ArrayList<>();
        for (JsonNode subtaskNode: subtasksNode) {
          String subtaskId = subtaskNode.hasNonNull(ID_PROP) ? subtaskNode.get(ID_PROP).asText() : null;
          subtasks.add(subtaskId);
        }
        fields.put(SUBTASKS_PROP, subtasks);
      }
    }

    return issue;
  }

  private ZonedDateTime getDateTime(String date, String pattern) {
    if (date == null) {
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
    return ZonedDateTime.parse(date, formatter);
  }
}
