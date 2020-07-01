package net;

import lombok.Getter;
import net.credentials.Credentials;
import net.service.*;
import net.service.impl.*;

/**
 * JIRA REST client.
 *
 * @version 0.1
 */
@Getter
public class RestClient {
  private UserService userService;
  private ProjectService projectService;
  private VersionService versionService;
  private IssueService issueService;
  private WorklogService worklogService;

  public RestClient(String baseUrl, Credentials credentials) {
    this.userService = new UserServiceImpl(baseUrl, credentials);
    this.projectService = new ProjectServiceImpl(baseUrl, credentials);
    this.versionService = new VersionServiceImpl(baseUrl, credentials);
    this.issueService = new IssueServiceImpl(baseUrl, credentials);
    this.worklogService = new WorklogServiceImpl(baseUrl, credentials);
  }
}
