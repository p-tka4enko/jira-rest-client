package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Version.
 *
 * @version 0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Version extends Resource {
  private String name;
  private Boolean archived;
  private Boolean released;
  @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate startDate;
  @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate releaseDate;
  @JsonFormat(pattern = "dd/LLL/yy", locale = "en_US") private LocalDate userStartDate;
  @JsonFormat(pattern = "dd/LLL/yy", locale = "en_US") private LocalDate userReleaseDate;
}
