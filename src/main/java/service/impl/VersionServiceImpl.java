package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import credentials.Credentials;
import dto.Version;
import dto.response.Versions;
import service.ServiceException;
import service.VersionService;

import java.util.List;

public class VersionServiceImpl extends VersionService {
  private static final String URL_PATTERN = "%s/rest/api/2/version/%s";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/project/%s/version?startAt=%d&maxResults=%d";

  private final ObjectMapper mapper;

  public VersionServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
    mapper = new ObjectMapper();
  }

  @Override
  public Version getVersion(@NonNull String id) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, id);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return mapper.readValue(response.getBody(), Version.class);
        case HttpStatus.SC_NOT_FOUND: return null;
        default: {
          String message = String.format("Failed to get version (%s), status code: %d", id, status);
          throw new ServiceException(message);
        }
      }
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Version> getVersions(@NonNull String projectIdOrKey, long startAt, int maxResults) throws ServiceException {
    try {
      String url = String.format(LIST_URL_PATTERN, baseUrl, projectIdOrKey, startAt, maxResults);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      if (status == HttpStatus.SC_OK) {
        Versions versions = mapper.readValue(response.getBody(), Versions.class);
        return versions.getValues();
      }

      String message = String.format("Failed to get version list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
