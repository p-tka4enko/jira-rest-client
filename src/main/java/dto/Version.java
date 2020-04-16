package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import jackson.deserializer.VersionDeserializer;

import java.time.LocalDate;

/**
 * Version.
 *
 * @version 0.1
 */
@JsonDeserialize(using = VersionDeserializer.class)
public class Version extends Resource {
  @Getter @Setter private String name;
  @Getter @Setter private Boolean archived;
  @Getter @Setter private Boolean released;
  @Getter @Setter private LocalDate startDate;
  @Getter @Setter private LocalDate releaseDate;
  @Getter @Setter private LocalDate userStartDate;
  @Getter @Setter private LocalDate userReleaseDate;
}
