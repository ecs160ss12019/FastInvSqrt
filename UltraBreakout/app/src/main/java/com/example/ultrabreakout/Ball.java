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

    public Ball(RectF _hitbox, float x_vel, float y_vel,
                int r_color_channel, int g_color_channel,
                int b_color_channel, int a_color_channel) {
        //FIXME: Come up with a standardized brick size in RectF constructor
        super(_hitbox, x_vel, y_vel,
                255, 255, 0, 0);
    }
}
