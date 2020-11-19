# ecos-jira-rest-client #
Atlassian's JIRA REST API implementation for Java.

## Maven dependency ##
```xml
<dependency>
    <groupId>ru.citeck.ecos</groupId>
    <artifactId>ecos-jira-rest-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick start example ##
```java
import ru.citeck.ecos.JiraRestClient;
import ru.citeck.ecos.credentials.Credentials;
import ru.citeck.ecos.credentials.impl.BasicCredentials;
import ru.citeck.ecos.domain.Issue;
import ru.citeck.ecos.service.ServiceException;

public class Example {
  public static void main(String[] args) {
    // Create JIRA REST client
    String baseUrl = "https://your-domain.net";
    Credentials credentials = new BasicCredentials("login", "tokenOrPassword");
    JiraRestClient rest = new JiraRestClient(baseUrl, credentials);

    try {
      // Retrieve issue with key "ISSUE-1" from JIRA
      Issue issue = rest.getIssueService().getIssue("ISSUE-1");

      // Print issue id
      System.out.println(issue.getId());

      // You can also do it like this
      System.out.println(issue);
    } catch (ServiceException e) {
      e.printStackTrace();
    }
  }
}
```