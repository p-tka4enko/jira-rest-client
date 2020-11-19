package net.service;

import lombok.NonNull;
import net.credentials.Credentials;
import net.domain.User;

import java.util.List;

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

  /**
   * Returns list of users.
   *
   * @param startAt index of the first item to return in a page of results (page offset)
   * @param maxResults maximum number of items to return per page
   * @return list of users
   * @throws ServiceException in case of any problems
   */
  public abstract List<User> getUsers(long startAt, int maxResults) throws ServiceException;
}
