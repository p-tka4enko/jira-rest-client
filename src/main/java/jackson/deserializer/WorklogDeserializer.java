package jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dto.User;
import dto.Worklog;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WorklogDeserializer extends StdDeserializer<Worklog> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String AUTHOR_PROP = "author";
  private static final String UPDATE_AUTHOR_PROP = "updateAuthor";
  private static final String CREATED_PROP = "created";
  private static final String UPDATED_PROP = "updated";
  private static final String STARTED_PROP = "started";
  private static final String TIME_SPENT_SECONDS_PROP = "timeSpentSeconds";

  public WorklogDeserializer() {
    this(null);
  }

  public WorklogDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Worklog deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    Worklog worklog = new Worklog();

    ObjectCodec codec = jsonParser.getCodec();
    JsonNode rootNode = codec.readTree(jsonParser);

    String self = rootNode.hasNonNull(SELF_PROP) ? rootNode.get(SELF_PROP).asText() : null;
    worklog.setSelf(self);

    String id = rootNode.hasNonNull(ID_PROP) ? rootNode.get(ID_PROP).asText() : null;
    worklog.setId(id);

    User author = rootNode.hasNonNull(AUTHOR_PROP) ? codec.treeToValue(rootNode.get(AUTHOR_PROP), User.class) : null;
    worklog.setAuthor(author);

    User updateAuthor = rootNode.hasNonNull(UPDATE_AUTHOR_PROP) ? codec.treeToValue(rootNode.get(UPDATE_AUTHOR_PROP), User.class) : null;
    worklog.setUpdateAuthor(updateAuthor);

    String createdStr = rootNode.hasNonNull(CREATED_PROP) ? rootNode.get(CREATED_PROP).asText() : null;
    ZonedDateTime created = getDateTime(createdStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    worklog.setCreated(created);

    String updatedStr = rootNode.hasNonNull(UPDATED_PROP) ? rootNode.get(UPDATED_PROP).asText() : null;
    ZonedDateTime updated = getDateTime(updatedStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    worklog.setUpdated(updated);

    String startedStr = rootNode.hasNonNull(STARTED_PROP) ? rootNode.get(STARTED_PROP).asText() : null;
    ZonedDateTime started = getDateTime(startedStr, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    worklog.setStarted(started);

    Integer timeSpentSeconds = rootNode.hasNonNull(TIME_SPENT_SECONDS_PROP) ? rootNode.get(TIME_SPENT_SECONDS_PROP).asInt() : null;
    worklog.setTimeSpentSeconds(timeSpentSeconds);

    return worklog;
  }

  private ZonedDateTime getDateTime(String date, String pattern) {
    if (date == null) {
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
    return ZonedDateTime.parse(date, formatter);
  }
}
