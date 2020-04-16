package jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dto.Version;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class VersionDeserializer extends StdDeserializer<Version> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String NAME_PROP = "name";
  private static final String ARCHIVED_PROP = "archived";
  private static final String RELEASED_PROP = "released";
  private static final String START_DATE_PROP = "startDate";
  private static final String RELEASE_DATE_PROP = "releaseDate";
  private static final String USER_START_DATE = "userStartDate";
  private static final String USER_RELEASE_DATE = "userReleaseDate";

  public VersionDeserializer() {
    this(null);
  }

  public VersionDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Version deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    Version version = new Version();
    version.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    version.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    version.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    version.setArchived(node.hasNonNull(ARCHIVED_PROP) ? node.get(ARCHIVED_PROP).asBoolean() : null);
    version.setReleased(node.hasNonNull(RELEASED_PROP) ? node.get(RELEASED_PROP).asBoolean() : null);
    String startDate = node.hasNonNull(START_DATE_PROP) ? node.get(START_DATE_PROP).asText() : null;
    version.setStartDate(getDate(startDate, "yyyy-MM-dd"));
    String releaseDate = node.hasNonNull(RELEASE_DATE_PROP) ? node.get(RELEASE_DATE_PROP).asText() : null;
    version.setReleaseDate(getDate(releaseDate, "yyyy-MM-dd"));
    String userStartDate = node.hasNonNull(USER_START_DATE) ? node.get(USER_START_DATE).asText() : null;
    version.setUserStartDate(getDate(userStartDate, "dd/LLL/yy"));
    String userReleaseDate = node.hasNonNull(USER_RELEASE_DATE) ? node.get(USER_RELEASE_DATE).asText() : null;
    version.setUserReleaseDate(getDate(userReleaseDate, "dd/LLL/yy"));

    return version;
  }

  private LocalDate getDate(String date, String pattern) {
    if (date == null) {
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
    return LocalDate.parse(date, formatter);
  }
}
