package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import jackson.deserializer.WorklogDeserializer;

import java.time.ZonedDateTime;

/**
 * Worklog.
 *
 * @version 0.1
 */
@JsonDeserialize(using = WorklogDeserializer.class)
public class Worklog extends Resource {
  @Getter @Setter User author;
  @Getter @Setter User updateAuthor;
  @Getter @Setter ZonedDateTime created;
  @Getter @Setter ZonedDateTime updated;
  @Getter @Setter ZonedDateTime started;
  @Getter @Setter Integer timeSpentSeconds;
}
