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
      if (isStrike(frameIndex)) {
        score += 10 + strikeBonus(frameIndex);
        frameIndex++;
      } else if (isSpare(frameIndex)) {
        score += 10 + spareBonus(frameIndex);
        frameIndex += 2;
      } else {
        score += sumOfBallsInFrame(frameIndex);
        frameIndex += 2;
      }
    }
    return score;
  }

  private int sumOfBallsInFrame(int frameIndex) {
    return rolls[frameIndex] + rolls[frameIndex + 1];
  }

  private int strikeBonus(int frameIndex) {
    return rolls[frameIndex + 1] + rolls[frameIndex + 2];
  }

  private int spareBonus(int frameIndex) {
    return rolls[frameIndex + 2];
  }

  private boolean isStrike(int frameIndex) {
    return rolls[frameIndex] == 10;
  }

  private boolean isSpare(int frameIndex) {
    return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
  }

}
