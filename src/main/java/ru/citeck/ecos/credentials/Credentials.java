package ru.citeck.ecos.credentials;

public interface Credentials {
  /**
   * Returns authorization HTTP header.
   *
   * @return authorization HTTP header
   */
  String getAuth();
}
