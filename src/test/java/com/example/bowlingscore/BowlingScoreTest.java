package com.example.bowlingscore;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Disabled;
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

  @Test
  void oneSpare() {
    rollSpare();
    g.roll(3);
    rollMany(17, 0);
    assertThat(g.score(), is(16));
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

class Game {
  private int[] rolls = new int[21];
  private int currentRoll = 0;

  public void roll(int pins) {
    rolls[currentRoll++] = pins;
  }

  public int score() {
    int score = 0;
    int frameIndex = 0;
    for (int frame = 0; frame < 10; frame++) {
      if (isSpare(frameIndex)) {
        score += 10 + rolls[frameIndex + 2];
        frameIndex += 2;
      } else {
        score += rolls[frameIndex] + rolls[frameIndex + 1];
        frameIndex += 2;
      }
    }
    return score;
  }

  private boolean isSpare(int frameIndex) {
    return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
  }
}
