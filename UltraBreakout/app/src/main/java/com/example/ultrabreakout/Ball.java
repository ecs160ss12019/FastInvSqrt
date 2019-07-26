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
    public static final int BALL_HEIGHT = 64;
    public static final int BALL_WIDTH = 64;

    // The maximum velocities for the ball in the x and y components.
    public static final int X_VELOCITY = 450;
    public static final int Y_VELOCITY = 450;

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel, y_vel, BALL_WIDTH, BALL_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.breakout_tiles_58));
    }

    public void collide (Paddle paddle){
        float x_diff = hitbox.centerX() - paddle.hitbox.centerX();
        float x_velocity = (x_diff / (paddle.width / 2)) * Ball.X_VELOCITY;
        velocity.setVelocity(x_velocity, velocity.y);

        velocity.reverseY();
    }

    public void collide (Brick brick){
        float vertical_dist = Math.min (
                Math.abs(brick.hitbox.bottom - hitbox.top),
                Math.abs(brick.hitbox.top - hitbox.bottom)
        );
        float horizontal_dist = Math.min (
                Math.abs(brick.hitbox.left - hitbox.right),
                Math.abs(brick.hitbox.right - hitbox.left)
        );
        if (vertical_dist >= horizontal_dist){
            velocity.reverseX();
        }
        else{
            velocity.reverseY();
        }
    }

    public void update (float fps, float screenWidth){
        if ((hitbox.right > screenWidth && velocity.x > 0)
                || (hitbox.left < 0 && velocity.x < 0)){
            velocity.reverseX();
        }
        if ((hitbox.top < 0 && velocity.y < 0)){
            velocity.reverseY();
        }
        updatePos(fps);
    }

    //Returns true if the ball has fallen down off the screen
    public boolean hasFallen (int screenHeight){
        if (hitbox.bottom > screenHeight && velocity.y > 0){
            return true;
        }
        return false;
    }

    public void reset (Paddle paddle){
        reposition(paddle.hitbox.centerX(),
                paddle.hitbox.top - paddle.hitbox.height() * 2);
        velocity.setSpeed(0);
    }

}
