package net.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import net.jackson.deserializer.UserDeserializer;

import java.util.Map;

/**
 * User.
 *
 * @version 0.1
 */
@JsonDeserialize(using = UserDeserializer.class)
public class User extends Resource {
  @Getter @Setter private String displayName;
  @Getter @Setter private String email;
  @Getter @Setter private Boolean active;
  @Getter @Setter private Map<String, String> avatarUrls;
}
