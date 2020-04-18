package net.service;

import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.User;

/**
 * Abstract user net.service.
 *
 * @version 0.1
 */
public abstract class UserService extends Service {
  public UserService(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  /**
   * Returns user.
   *
   * @param id user id
   * @return user
   * @throws ServiceException in case of any problems
   */
  public abstract User getUser(@NonNull String id) throws ServiceException;
}
