package com.example.bowlingscore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class BowlingScoreTest {

  @Test
  void gutterGame() {
    Game g = new Game();
    for (int i = 0; i < 20; i++) {
      g.roll(0);
    }
    assertThat(g.score(), is(0));
  }

}

class Game {

  public void roll(int pins) {
  }

  public int score() {
    return 0;
  }
}