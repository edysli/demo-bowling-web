package com.example.bowlingscore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class BowlingScoreTest {

  private final Game g = new Game();

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

  private void rollMany(final int rolls, final int pins) {
    for (int i = 0; i < rolls; i++) {
      g.roll(pins);
    }
  }
}

class Game {
  private int score = 0;

  public void roll(int pins) {
    score += pins;
  }

  public int score() {
    return score;
  }
}
