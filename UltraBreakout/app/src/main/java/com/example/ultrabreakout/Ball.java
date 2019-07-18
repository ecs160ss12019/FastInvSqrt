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

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel,y_vel, 40,40,
                255, 255, 0, 0);
    }
}
