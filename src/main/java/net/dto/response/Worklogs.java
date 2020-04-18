package net.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dto.Worklog;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worklogs {
  @JsonProperty("total")
  private Integer total;

  @JsonProperty("worklogs")
  private List<Worklog> worklogs;
}
