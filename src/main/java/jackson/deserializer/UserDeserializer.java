package jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dto.User;

import java.io.IOException;
import java.util.HashMap;

public class UserDeserializer extends StdDeserializer<User> {
  private static final String SELF_PROP = "self";
  private static final String ACCOUNT_ID_PROP = "accountId";
  private static final String DISPLAY_NAME_PROP = "displayName";
  private static final String EMAIL_PROP = "emailAddress";
  private static final String ACTIVE_PROP = "active";
  private static final String AVATAR_URLS_PROP = "avatarUrls";

  public UserDeserializer() {
    this(null);
  }

  public UserDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    ObjectCodec codec = jsonParser.getCodec();
    JsonNode node = codec.readTree(jsonParser);

    User user = new User();
    user.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    user.setId(node.hasNonNull(ACCOUNT_ID_PROP) ? node.get(ACCOUNT_ID_PROP).asText() : null);
    user.setDisplayName(node.hasNonNull(DISPLAY_NAME_PROP) ? node.get(DISPLAY_NAME_PROP).asText() : null);
    user.setEmail(node.hasNonNull(EMAIL_PROP) ? node.get(EMAIL_PROP).asText() : null);
    user.setActive(node.hasNonNull(ACTIVE_PROP) ? node.get(ACTIVE_PROP).asBoolean() : null);
    user.setAvatarUrls(node.hasNonNull(AVATAR_URLS_PROP) ? codec.treeToValue(node.get(AVATAR_URLS_PROP), HashMap.class) : null);

    return user;
  }
}
