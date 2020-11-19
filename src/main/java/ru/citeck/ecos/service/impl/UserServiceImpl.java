package ru.citeck.ecos.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;
import ru.citeck.ecos.credentials.Credentials;
import ru.citeck.ecos.domain.User;
import ru.citeck.ecos.service.ServiceException;
import ru.citeck.ecos.service.UserService;
import ru.citeck.ecos.util.Mapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import java.util.List;

public class UserServiceImpl extends UserService {
  private static final String URL_PATTERN = "%s/rest/api/2/user?accountId=%s";
  private static final String LIST_URL_PATTERN = "%s/rest/api/2/users?startAt=%d&maxResults=%d";

  public UserServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
  }

  @Override
  public User getUser(@NonNull String id) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, id);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return Mapper.get().readValue(response.getBody(), User.class);
        case HttpStatus.SC_NOT_FOUND: return null;
        default: {
          String message = String.format("Failed to get user (%s), status code: %d", id, status);
          throw new ServiceException(message);
        }
      }
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<User> getUsers(long startAt, int maxResults) throws ServiceException {
    try {
      String url = String.format(LIST_URL_PATTERN, baseUrl, startAt, maxResults);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      if (status == HttpStatus.SC_OK) {
        return Mapper.get().readValue(response.getBody(), new TypeReference<List<User>>() {});
      }

      String message = String.format("Failed to get user list, status code: %d", status);
      throw new ServiceException(message);
    } catch (UnirestException | JsonProcessingException e) {
      throw new ServiceException(e.getMessage());
    }
  }
}
