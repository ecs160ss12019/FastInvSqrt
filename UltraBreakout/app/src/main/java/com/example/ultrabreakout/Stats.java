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
  int bricksRemaining;
  int bricksDestroyed;
  int score;
  float timestart;
  int lives;
  float timeelpased;


  public Stats () {
    score = 0;
    lives = 3;
    timeelpased = 0;
    bricksRemaining = 0;
    bricksDestroyed = 0;
    timestart = System.currentTimeMillis();
  }

  public Stats (int bricksRemaining) {
    score = 0;
    lives = 3;
    timeelpased = 0;
    this.bricksRemaining = bricksRemaining;
    timestart = System.currentTimeMillis();
  }

  public void updatetime() {
    this.timeelpased = (this.timeelpased + (System.currentTimeMillis() - this.timestart));
  }

  public void incrementLives(){
    lives++;
  }

  public void decrementLives(){
    lives--;
  }

  public void incrementScore(){
    score += 10;
  }
  public void decrementScore(){
    score -= 50;
  }
  public void incrementRemainingBricks() {bricksRemaining+=1;}
  public void incrementDestroyedBricks() {bricksDestroyed+=1;}

  public void decrementRemainingBricks() {bricksRemaining-=1;}
}
