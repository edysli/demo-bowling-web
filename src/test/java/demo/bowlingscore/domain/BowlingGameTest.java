package demo.bowlingscore.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class BowlingGameTest {
  private final BowlingGame g = new BowlingGame();

  @Test
  void gutterGame() {
    rollMany(20, 0);
    assertThat(g.score(), is(0));
  }

  @Test
  void allOnes() {
    rollMany(20, 1);
    assertThat(g.score(), is(20));
  }

  @Test
  void oneSpare() {
    rollSpare();
    g.roll(3);
    rollMany(17, 0);
    assertThat(g.score(), is(16));
  }

  @Test
  void oneStrike() {
    rollStrike();
    g.roll(3);
    g.roll(4);
    rollMany(16, 0);
    assertThat(g.score(), is(24));
  }

  @Test
  void perfectGame() {
    rollMany(12, 10);
    assertThat(g.score(), is(300));
  }

  private void rollStrike() {
    g.roll(10);
  }

  private void rollSpare() {
    g.roll(5);
    g.roll(5);
  }

  private void rollMany(final int rolls, final int pins) {
    for (int i = 0; i < rolls; i++) {
      g.roll(pins);
    }
  }
}
