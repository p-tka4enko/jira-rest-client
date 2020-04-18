package net.credentials;

/**
 * Credentials.
 *
 * @version 0.1
 */
public interface Credentials {
  /**
   * Returns authorization HTTP header.
   *
   * @return authorization HTTP header
   */
  String getAuth();
}
