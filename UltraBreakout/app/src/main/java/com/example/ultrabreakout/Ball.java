package com.example.ultrabreakout;

/**
 * Handles the ball(s).
 * When the game updates, check if the ball has collided
 * (intersected) with anything after bouncing off the paddle.
 *
 * TODO
 * See "Ball.java" from Ch. 11
 * THE BALL HANDLES CHECKING WHEN AND WHERE IT HIT(S).
 */
//

class Ball extends Actor {

	private float xDirection;
    private float yDirection;


    boolean colliding = false;
    boolean isLive;
}
