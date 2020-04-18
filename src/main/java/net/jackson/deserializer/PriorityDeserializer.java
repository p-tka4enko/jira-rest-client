package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.Priority;

import java.io.IOException;

public class PriorityDeserializer extends StdDeserializer<Priority> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String NAME_PROP = "name";
  private static final String DESCRIPTION_PROP = "description";

  public PriorityDeserializer() {
    this(null);
  }

  public PriorityDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Priority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    Priority priority = new Priority();
    priority.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    priority.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    priority.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    priority.setDescription(node.hasNonNull(DESCRIPTION_PROP) ? node.get(DESCRIPTION_PROP).asText() : null);

    return priority;
  }
}
