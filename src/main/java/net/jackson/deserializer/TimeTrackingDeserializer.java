package net.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.dto.TimeTracking;

import java.io.IOException;

public class TimeTrackingDeserializer extends StdDeserializer<TimeTracking> {
  private static final String ORIGINAL_ESTIMATE_PROP = "originalEstimate";
  private static final String REMAINING_ESTIMATE_PROP = "remainingEstimate";
  private static final String TIME_SPENT_PROP = "timeSpent";
  private static final String ORIGINAL_ESTIMATE_SECONDS_PROP = "originalEstimateSeconds";
  private static final String REMAINING_ESTIMATE_SECONDS_PROP = "remainingEstimateSeconds";
  private static final String TIME_SPENT_SECONDS_PROP = "timeSpentSeconds";

  public TimeTrackingDeserializer() {
    this(null);
  }

  public TimeTrackingDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public TimeTracking deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    TimeTracking timeTracking = new TimeTracking();

    JsonNode node = jsonParser.getCodec().readTree(jsonParser);

    String originalEstimate = node.hasNonNull(ORIGINAL_ESTIMATE_PROP) ? node.get(ORIGINAL_ESTIMATE_PROP).asText() : null;
    timeTracking.setOriginalEstimate(originalEstimate);

    String remainingEstimate = node.hasNonNull(REMAINING_ESTIMATE_PROP) ? node.get(REMAINING_ESTIMATE_PROP).asText() : null;
    timeTracking.setRemainingEstimate(remainingEstimate);

    String timeSpent = node.hasNonNull(TIME_SPENT_PROP) ? node.get(TIME_SPENT_PROP).asText() : null;
    timeTracking.setTimeSpent(timeSpent);

    Integer originalEstimateSeconds = node.hasNonNull(ORIGINAL_ESTIMATE_SECONDS_PROP) ? node.get(ORIGINAL_ESTIMATE_SECONDS_PROP).asInt() : null;
    timeTracking.setOriginalEstimateSeconds(originalEstimateSeconds);

    Integer remainingEstimateSeconds = node.hasNonNull(REMAINING_ESTIMATE_SECONDS_PROP) ? node.get(REMAINING_ESTIMATE_SECONDS_PROP).asInt() : null;
    timeTracking.setRemainingEstimateSeconds(remainingEstimateSeconds);

    Integer timeSpentSeconds = node.hasNonNull(TIME_SPENT_SECONDS_PROP) ? node.get(TIME_SPENT_SECONDS_PROP).asInt() : null;
    timeTracking.setTimeSpentSeconds(timeSpentSeconds);

    return timeTracking;
  }
}
