package net.service;

import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.Issue;

import java.util.List;

/**
 * Abstract issue service.
 *
 * @version 0.1
 */
public abstract class IssueService extends Service {
  public IssueService(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  /**
   * Returns issue.
   *
   * @param idOrKey issue id or key
   * @return issue
   * @throws ServiceException in case of any problems
   */
  public abstract Issue getIssue(@NonNull String idOrKey) throws ServiceException;

  /**
   * Returns list of issues.
   *
   * @param startAt index of the first item to return in a page of results (page offset)
   * @param maxResults maximum number of items to return per page
   * @return list of issues
   * @throws ServiceException in case of any problems
   */
  public abstract List<Issue> getIssues(long startAt, int maxResults) throws ServiceException;
}
