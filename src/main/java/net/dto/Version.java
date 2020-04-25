package net.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Version.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Version extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private Boolean archived;
  @Getter @Setter private Boolean released;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate startDate;
  @Getter @Setter @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate releaseDate;
  @Getter @Setter @JsonFormat(pattern = "dd/LLL/yy", locale = "en_US") private LocalDate userStartDate;
  @Getter @Setter @JsonFormat(pattern = "dd/LLL/yy", locale = "en_US") private LocalDate userReleaseDate;
}
