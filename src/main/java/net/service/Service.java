package net.service;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import net.credentials.Credentials;

/**
 * Base abstract net.service class.
 *
 * @version 0.1
 */
public abstract class Service {
  protected String baseUrl;
  protected Credentials credentials;

  public Service(@NonNull String baseUrl, @NonNull Credentials credentials) {
    this.baseUrl = StringUtils.removeEnd(baseUrl, "/");
    this.credentials = credentials;
  }
}