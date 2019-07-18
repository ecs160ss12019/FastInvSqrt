package com.example.ultrabreakout;

import android.graphics.RectF;
import android.graphics.Color;

/*
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
    public static final int BALL_HEIGHT = 40;
    public static final int BALL_WIDTH = 40;

    boolean is_live;

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel, y_vel, BALL_WIDTH, BALL_HEIGHT,
                Color.CYAN);
    }
}
