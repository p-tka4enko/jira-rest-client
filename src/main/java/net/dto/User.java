package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Resource {
  private String displayName;
  private String emailAddress;
  private Boolean active;
  private Map<String, String> avatarUrls;

  @Override
  @JsonProperty("accountId")
  public void setId(String id) {
    this.id = id;
  }
}
