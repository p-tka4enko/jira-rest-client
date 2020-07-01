package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Worklog.
 *
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worklog extends Resource {
  private User author;
  private User updateAuthor;
  private Integer timeSpentSeconds;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime created;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime updated;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime started;
}
