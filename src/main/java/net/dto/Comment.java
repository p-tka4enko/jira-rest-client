package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Comment.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment extends Resource {
  @Getter @Setter private User author;
  @Getter @Setter private User updateAuthor;
  @Getter @Setter private String body;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime created;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime updated;
}
