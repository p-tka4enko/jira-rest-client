package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Worklog.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worklog extends Resource {
  @Getter @Setter User author;
  @Getter @Setter User updateAuthor;
  @Getter @Setter Integer timeSpentSeconds;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") ZonedDateTime created;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") ZonedDateTime updated;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") ZonedDateTime started;
}
