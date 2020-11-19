package net.service;

import lombok.NonNull;
import net.credentials.Credentials;
import net.domain.Worklog;

import java.util.List;

public abstract class WorklogService extends Service {
  public WorklogService(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  /**
   * Returns worklog.
   *
   * @param issueIdOrKey issue id or key
   * @param id worklog id
   * @return worklog
   * @throws ServiceException in case of any problems
   */
  public abstract Worklog getWorklog(@NonNull String issueIdOrKey, @NonNull String id) throws ServiceException;

  /**
   * Returns list of worklogs.
   *
   * @param issueIdOrKey issue id or key
   * @param startAt index of the first item to return in a page of results (page offset)
   * @param maxResults maximum number of items to return per page
   * @return list of worklogs
   * @throws ServiceException in case of any problems
   */
  public abstract List<Worklog> getWorklogs(@NonNull String issueIdOrKey, long startAt, int maxResults) throws ServiceException;
}
