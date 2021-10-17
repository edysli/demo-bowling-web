package demo.bowlingscore.web;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ScoringRequest {
  @Size(min = 12, max = 21) int[] pins;

  @JsonCreator
  public ScoringRequest(@JsonProperty("pins") int[] pins) {
    this.pins = pins;
  }
}
