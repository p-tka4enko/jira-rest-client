package net.service;

import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.Project;

import java.util.List;

/**
 * Abstract project net.service.
 *
 * @version 0.1
 */
public abstract class ProjectService extends Service {
  public ProjectService(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  /**
   * Returns project.
   *
   * @param idOrKey project id or key
   * @return project
   * @throws ServiceException in case of any problems
   */
  public abstract Project getProject(@NonNull String idOrKey) throws ServiceException;

  /**
   * Returns list of projects.
   *
   * @param startAt index of the first item to return in a page of results (page offset)
   * @param maxResults maximum number of items to return per page
   * @return list of projects
   * @throws ServiceException in case of any problems
   */
  public abstract List<Project> getProjects(long startAt, int maxResults) throws ServiceException;
}
