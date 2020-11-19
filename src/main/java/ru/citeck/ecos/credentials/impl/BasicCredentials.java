package ru.citeck.ecos.credentials.impl;

import lombok.NonNull;
import ru.citeck.ecos.credentials.Credentials;

import java.util.Base64;

public class BasicCredentials implements Credentials {
  private static final String TYPE = "Basic";

  private final String username;
  private final String password;

  public BasicCredentials(@NonNull String username, @NonNull String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String getAuth() {
    String encodedData = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    return String.format("%s %s", TYPE, encodedData);
  }
}
