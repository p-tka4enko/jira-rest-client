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
import dto.User;
import service.ServiceException;
import service.UserService;

public class UserServiceImpl extends UserService {
  private static final String URL_PATTERN = "%s/rest/api/2/user?accountId=%s";

  private final ObjectMapper mapper;

  public UserServiceImpl(String baseUrl, Credentials credentials) {
    super(baseUrl, credentials);
    mapper = new ObjectMapper();
  }

  @Override
  public User getUser(@NonNull String id) throws ServiceException {
    try {
      String url = String.format(URL_PATTERN, baseUrl, id);
      HttpResponse<String> response = Unirest.get(url).header(HttpHeaders.AUTHORIZATION, credentials.getAuth()).asString();

      int status = response.getStatus();
      switch (status) {
        case HttpStatus.SC_OK: return mapper.readValue(response.getBody(), User.class);
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
}