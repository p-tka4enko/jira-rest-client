package service;

import lombok.NonNull;
import credentials.Credentials;
import dto.Version;

import java.util.List;

/**
 * Abstract version service.
 *
 * @version 0.1
 */
public abstract class VersionService extends Service {
  public VersionService(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  /**
   * Returns version.
   *
   * @param id version id
   * @return version
   * @throws ServiceException in case of any problems
   */
  public abstract Version getVersion(@NonNull String id) throws ServiceException;

  /**
   * Returns list of versions.
   *
   * @param projectIdOrKey project id or key
   * @param startAt index of the first item to return in a page of results (page offset)
   * @param maxResults maximum number of items to return per page
   * @return list of version
   * @throws ServiceException in case of any problems
   */
  public abstract List<Version> getVersions(@NonNull String projectIdOrKey, long startAt, int maxResults) throws ServiceException;
}
