package com.example.ultrabreakout;

import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.Color;
import android.content.Context;
import android.graphics.Bitmap;

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

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel, y_vel, BALL_WIDTH, BALL_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball));
    }
    public void reset(float xpos){
        this.hitbox.left = xpos;
        this.hitbox.top = 900;
        this.hitbox.right = xpos + width;
        this.hitbox.bottom = height;

        this.velocity.x = 0;
        this.velocity.y = 0;
    }
}
