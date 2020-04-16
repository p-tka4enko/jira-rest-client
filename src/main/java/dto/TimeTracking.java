package dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import jackson.deserializer.TimeTrackingDeserializer;

/**
 * Issue time tracking data.
 *
 * @version 0.1
 */
@JsonDeserialize(using = TimeTrackingDeserializer.class)
public class TimeTracking {
  @Getter @Setter private String originalEstimate;
  @Getter @Setter private String remainingEstimate;
  @Getter @Setter private String timeSpent;
  @Getter @Setter private Integer originalEstimateSeconds;
  @Getter @Setter private Integer remainingEstimateSeconds;
  @Getter @Setter private Integer timeSpentSeconds;

  @Override
  public String toString() {
    return String.format("(Original estimate: %s, Remaining estimate: %s, Time spent: %s)", originalEstimate, remainingEstimate, timeSpent);
  }
}
