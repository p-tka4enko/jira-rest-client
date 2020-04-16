package service;

import lombok.NonNull;
import credentials.Credentials;
import dto.User;

/**
 * Abstract user service.
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
