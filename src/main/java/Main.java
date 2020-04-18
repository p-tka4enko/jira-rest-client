import net.RestClient;
import net.credentials.Credentials;
import net.credentials.impl.BasicCredentials;
import net.dto.Issue;
import net.service.ServiceException;

public class Main {
  public static void main(String[] args) {
    // Create JIRA REST client
    String baseUrl = "https://your-domain.net";
    Credentials credentials = new BasicCredentials("login", "tokenOrPassword");
    RestClient rest = new RestClient(baseUrl, credentials);

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
