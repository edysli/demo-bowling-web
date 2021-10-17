package demo.bowlingscore.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ScoringRequest {
  int[] pins;

  @JsonCreator
  public ScoringRequest(@JsonProperty("pins") int[] pins) {
    this.pins = pins;
  }
}
