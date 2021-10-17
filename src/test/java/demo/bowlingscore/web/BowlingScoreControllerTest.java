package demo.bowlingscore.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BowlingScoreControllerTest {
  private final BowlingScoreController c = new BowlingScoreController();

  @ParameterizedTest
  @MethodSource
  void games(ScoringRequest request, ScoringResponse response) {
    assertThat(c.compute(request), is(response));
  }

  static Stream<Arguments> games() {
    return Stream.of(
      Arguments.of(new ScoringRequest(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }),
        new ScoringResponse(0)),
      Arguments.of(new ScoringRequest(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }),
        new ScoringResponse(20)),
      Arguments.of(new ScoringRequest(new int[] { 5, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }),
        new ScoringResponse(16)),
      Arguments.of(new ScoringRequest(new int[] { 10, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }),
        new ScoringResponse(24)),
      Arguments.of(new ScoringRequest(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}),
        new ScoringResponse(300)));
  }
}
