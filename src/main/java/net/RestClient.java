package net;

import lombok.Getter;
import net.credentials.Credentials;
import net.service.*;
import net.service.impl.*;

@Getter
public class RestClient {
  private final UserService userService;
  private final ProjectService projectService;
  private final VersionService versionService;
  private final IssueService issueService;
  private final WorklogService worklogService;

  public RestClient(String baseUrl, Credentials credentials) {
    userService = new UserServiceImpl(baseUrl, credentials);
    projectService = new ProjectServiceImpl(baseUrl, credentials);
    versionService = new VersionServiceImpl(baseUrl, credentials);
    issueService = new IssueServiceImpl(baseUrl, credentials);
    worklogService = new WorklogServiceImpl(baseUrl, credentials);
  }
}
