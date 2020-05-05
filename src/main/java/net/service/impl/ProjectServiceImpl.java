package net.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import net.credentials.Credentials;
import net.dto.Project;
import net.service.ProjectService;
import net.service.ServiceException;
import net.util.Mapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.List;

public class ProjectServiceImpl extends ProjectService {
  private static final String URL_PATTERN = "%s/rest/api/2/project/%s";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/project/search?startAt=%d&maxResults=%d&expand=description,lead";

  public ProjectServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  @Override
  public Project getProject(@NonNull String idOrKey) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, idOrKey);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return Mapper.get().readValue(response.getBody(), Project.class);
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
        JsonNode projects = Mapper.get().readTree(response.getBody()).get("values");
        return Mapper.get().convertValue(projects, new TypeReference<List<Project>>() {});
      }

      String message = String.format("Failed to get project list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
