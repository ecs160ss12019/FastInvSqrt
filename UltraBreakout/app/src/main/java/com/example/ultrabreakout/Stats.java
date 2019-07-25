package com.example.ultrabreakout;

/*
 * Keeps track of in-game statistics such as lives
 * and score.
 *
 * TODO
 * I think this can be created now; does it depend on anything
 * else?
 */

class Stats {
  private int bricksRemaining;
  private int score;
  private float timePlayed;
  int lives;

  public Stats () {
    score = 0;
    timePlayed = 0;
    lives = 3;

  }
}
