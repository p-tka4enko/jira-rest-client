package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.Project;
import net.dto.User;

import java.io.IOException;

public class ProjectDeserializer extends StdDeserializer<Project> {
  private static final String SELF_PROP = "self";
  private static final String ID_PROP = "id";
  private static final String KEY_PROP = "key";
  private static final String NAME_PROP = "name";
  private static final String DESCRIPTION_PROP = "description";
  private static final String LEAD_PROP = "lead";

  public ProjectDeserializer() {
    this(null);
  }

  public ProjectDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    ObjectCodec codec = jsonParser.getCodec();
    JsonNode node = codec.readTree(jsonParser);

    Project project = new Project();
    project.setSelf(node.hasNonNull(SELF_PROP) ? node.get(SELF_PROP).asText() : null);
    project.setId(node.hasNonNull(ID_PROP) ? node.get(ID_PROP).asText() : null);
    project.setKey(node.hasNonNull(KEY_PROP) ? node.get(KEY_PROP).asText() : null);
    project.setName(node.hasNonNull(NAME_PROP) ? node.get(NAME_PROP).asText() : null);
    project.setDescription(node.hasNonNull(DESCRIPTION_PROP) ? node.get(DESCRIPTION_PROP).asText() : null);
    project.setLead(node.hasNonNull(LEAD_PROP) ? codec.treeToValue(node.get(LEAD_PROP), User.class) : null);

    return project;
  }
}
