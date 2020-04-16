package jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dto.Resolution;

import java.io.IOException;

public class ResolutionDeserializer extends StdDeserializer<Resolution> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String NAME_PROP = "name";
  private static final String DESCRIPTION_PROP = "description";

  public ResolutionDeserializer() {
    this(null);
  }

  public ResolutionDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Resolution deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    Resolution resolution = new Resolution();
    resolution.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    resolution.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    resolution.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    resolution.setDescription(node.hasNonNull(DESCRIPTION_PROP) ? node.get(DESCRIPTION_PROP).asText() : null);

    return resolution;
  }
}
