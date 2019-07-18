package com.example.ultrabreakout;

import android.graphics.RectF;

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
    boolean is_live;

    public Ball(RectF _hitbox, float x_vel, float y_vel, float width, float height) {
        super(_hitbox, x_vel, y_vel, width, height);
    }
}
