package net.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import net.credentials.Credentials;
import net.dto.Project;
import net.dto.response.Projects;
import net.service.ProjectService;
import net.service.ServiceException;

import java.util.List;

public class ProjectServiceImpl extends ProjectService {
  private static final String URL_PATTERN = "%s/rest/api/2/project/%s";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/project/search?startAt=%d&maxResults=%d&expand=description,lead";

  private final ObjectMapper mapper;

  public ProjectServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
    mapper = new ObjectMapper();
  }

  @Override
  public Project getProject(@NonNull String idOrKey) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, idOrKey);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return mapper.readValue(response.getBody(), Project.class);
        case HttpStatus.SC_NOT_FOUND: return null;
      }

      String message = String.format("Failed to get project (%s), status code: %d", idOrKey, status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Project> getProjects(long startAt, int maxResults) throws ServiceException {
    try {
      String url = String.format(LIST_URL_PATTERN, baseUrl, startAt, maxResults);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      if (status == HttpStatus.SC_OK) {
        Projects projects = mapper.readValue(response.getBody(), Projects.class);
        return projects.getValues();
      }

      String message = String.format("Failed to get project list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
