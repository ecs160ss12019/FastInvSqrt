package com.example.ultrabreakout;

import android.graphics.BitmapFactory;

/*
 * Handles the ball(s).
 * When the game updates, check if the ball has collided
 * (intersected) with anything after bouncing off the paddle.
 *
 * TODO
 *
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
                BitmapFactory.decodeResource(sprites,R.drawable.ball));
    }

    public void collide (Ball ball){
        //FIXME idk what happens here
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

    //Kills the ball. What happens depends on if it's ball_zero.
    public void die (Paddle paddle_zero, int num_balls){
        if (num_balls == 1){
            reset (paddle_zero);
        }
        velocity.setSpeed(0);
        //FIXME: Anything else to set?
    }

    //Resets the ball to on top of the original paddle.
    public void reset (Paddle paddle_zero){
        reposition(paddle_zero.hitbox.centerX(),
                paddle_zero.hitbox.top - paddle_zero.hitbox.height() * 2);
        velocity.setSpeed(0);
    }

}
