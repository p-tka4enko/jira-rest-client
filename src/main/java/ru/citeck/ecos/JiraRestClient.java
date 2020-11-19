package ru.citeck.ecos;

import lombok.Getter;
import ru.citeck.ecos.credentials.Credentials;
import ru.citeck.ecos.service.*;
import ru.citeck.ecos.service.impl.*;

@Getter
public class JiraRestClient {
  private final UserService userService;
  private final ProjectService projectService;
  private final VersionService versionService;
  private final IssueService issueService;
  private final WorklogService worklogService;

  public JiraRestClient(String baseUrl, Credentials credentials) {
    userService = new UserServiceImpl(baseUrl, credentials);
    projectService = new ProjectServiceImpl(baseUrl, credentials);
    versionService = new VersionServiceImpl(baseUrl, credentials);
    issueService = new IssueServiceImpl(baseUrl, credentials);
    worklogService = new WorklogServiceImpl(baseUrl, credentials);
  }
}
