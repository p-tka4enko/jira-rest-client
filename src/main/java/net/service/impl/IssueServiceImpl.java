package net.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.Issue;
import net.dto.response.Issues;
import net.service.IssueService;
import net.service.ServiceException;
import net.util.Mapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.List;

public class IssueServiceImpl extends IssueService {
  private static final String URL_PATTERN = "%s/rest/api/2/issue/%s?fields=summary,description,reporter,assignee,created,updated,duedate,resolutiondate,timetracking,issuetype,status,priority,resolution,parent,subtasks";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/search?startAt=%d&maxResults=%d&fields=summary,description,reporter,assignee,created,updated,duedate,resolutiondate,timetracking,issuetype,status,priority,resolution,parent,subtasks";

  public IssueServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  @Override
  public Issue getIssue(@NonNull String idOrKey) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, idOrKey);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return Mapper.get().readValue(response.getBody(), Issue.class);
        case HttpStatus.SC_NOT_FOUND: return null;
      }

      String message = String.format("Failed to get issue (%s), status code: %d", idOrKey, status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Issue> getIssues(long startAt, int maxResults) throws ServiceException {
    try {
      String url = String.format(LIST_URL_PATTERN, baseUrl, startAt, maxResults);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      if (status == HttpStatus.SC_OK) {
        Issues issues = Mapper.get().readValue(response.getBody(), Issues.class);
        return issues.getIssues();
      }

      String message = String.format("Failed to get issue list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
