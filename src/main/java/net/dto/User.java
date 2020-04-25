package net.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * User.
 *
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Resource {
  @Getter @Setter private String displayName;
  @Getter @Setter private String emailAddress;
  @Getter @Setter private Boolean active;
  @Getter @Setter private Map<String, String> avatarUrls;

  @Override
  @JsonProperty("accountId")
  public void setId(String id) {
    this.id = id;
  }
}
