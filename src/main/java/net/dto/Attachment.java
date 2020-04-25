package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Attachment.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment extends Resource {
  @Getter @Setter private String filename;
  @Getter @Setter private User author;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") private ZonedDateTime created;
  @Getter @Setter private Integer size;
  @Getter @Setter private String mimeType;
  @Getter @Setter private String content;
  @Getter @Setter private String thumbnail;
}
