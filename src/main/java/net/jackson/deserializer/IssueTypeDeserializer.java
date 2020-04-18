package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.IssueType;

import java.io.IOException;

public class IssueTypeDeserializer extends StdDeserializer<IssueType> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String NAME_PROP = "name";
  private static final String DESCRIPTION_PROP = "description";
  private static final String SUBTASK_PROP = "subtask";

  public IssueTypeDeserializer() {
    this(null);
  }

  public IssueTypeDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public IssueType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    IssueType issueType = new IssueType();
    issueType.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    issueType.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    issueType.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    issueType.setDescription(node.hasNonNull(DESCRIPTION_PROP) ? node.get(DESCRIPTION_PROP).asText() : null);
    issueType.setSubtask(node.hasNonNull(SUBTASK_PROP) ? node.get(SUBTASK_PROP).asBoolean() : null);

    return issueType;
  }
}
