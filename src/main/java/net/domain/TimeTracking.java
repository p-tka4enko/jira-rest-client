package net.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeTracking {
  private String originalEstimate;
  private String remainingEstimate;
  private String timeSpent;
  private Integer originalEstimateSeconds;
  private Integer remainingEstimateSeconds;
  private Integer timeSpentSeconds;

  @Override
  public String toString() {
    return String.format("(Original estimate: %s, Remaining estimate: %s, Time spent: %s)", originalEstimate, remainingEstimate, timeSpent);
  }
}
