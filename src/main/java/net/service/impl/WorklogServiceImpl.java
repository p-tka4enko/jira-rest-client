package net.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.Worklog;
import net.service.ServiceException;
import net.service.WorklogService;
import net.util.Mapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.List;

public class WorklogServiceImpl extends WorklogService {
  private static final String URL_PATTERN = "%s/rest/api/2/issue/%s/worklog/%s";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/issue/%s/worklog?startAt=%d&maxResults=%d";

  public WorklogServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  @Override
  public Worklog getWorklog(@NonNull String issueIdOrKey, @NonNull String id) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, issueIdOrKey, id);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return Mapper.get().readValue(response.getBody(), Worklog.class);
        case HttpStatus.SC_NOT_FOUND: return null;
        default: {
          String message = String.format("Failed to get worklog (%s) of issue (%s), status code: %d", id, issueIdOrKey, status);
          throw new ServiceException(message);
        }
      }
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Worklog> getWorklogs(@NonNull String issueIdOrKey, long startAt, int maxResults) throws ServiceException {
    try {
      String url = String.format(LIST_URL_PATTERN, baseUrl, issueIdOrKey, startAt, maxResults);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      if (status == HttpStatus.SC_OK) {
        JsonNode worklogs = Mapper.get().readTree(response.getBody()).get("worklogs");
        return Mapper.get().convertValue(worklogs, new TypeReference<List<Worklog>>() {});
      }

      String message = String.format("Failed to get worklog list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
