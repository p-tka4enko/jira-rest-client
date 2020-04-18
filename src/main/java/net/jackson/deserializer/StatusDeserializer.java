package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.Status;

import java.io.IOException;

public class StatusDeserializer extends StdDeserializer<Status> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String NAME_PROP = "name";
  private static final String DESCRIPTION_PROP = "description";

  public StatusDeserializer() {
    this(null);
  }

  public StatusDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    Status status = new Status();
    status.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    status.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    status.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    status.setDescription(node.hasNonNull(DESCRIPTION_PROP) ? node.get(DESCRIPTION_PROP).asText() : null);

    return status;
  }
}
