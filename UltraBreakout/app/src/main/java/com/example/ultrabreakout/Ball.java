package com.example.ultrabreakout;

import android.graphics.BitmapFactory;
import android.os.Handler;

import java.util.ArrayList;

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
    public static final int BALL_HEIGHT = 55;
    public static final int BALL_WIDTH = 55;

    // The maximum velocities for the ball in the x and y components.
    public static final int X_VELOCITY = 450;
    public static final int Y_VELOCITY = 450;
    public static final int BALL_POWERUP_TIME = 4000;
    public Handler ballTimer;
    private Runnable ballCallback;
    // Timer and handler to implement paddle width powerup object.
    public boolean golden = false;

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel, y_vel, BALL_WIDTH, BALL_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball3));
        ballTimer = new Handler();
        ballCallback = new Runnable() {
            @Override
            public void run() {
                notGoldenBall();
            }
        };
    }

    public void setGoldenBall(){
        ballTimer.removeCallbacks(ballCallback);
        ballTimer.postDelayed(ballCallback, BALL_POWERUP_TIME);
        this.golden = true;
        this.setSprite(BitmapFactory.decodeResource(sprites,R.drawable.goldenball));
    }
    public void notGoldenBall(){
        setSprite(BitmapFactory.decodeResource(sprites,R.drawable.ball3));
        this.golden = false;
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
            notGoldenBall();
            paddle_zero.paddleWidthDecrease();
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
