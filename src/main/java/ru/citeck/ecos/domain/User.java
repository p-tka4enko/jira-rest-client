package ru.citeck.ecos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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
