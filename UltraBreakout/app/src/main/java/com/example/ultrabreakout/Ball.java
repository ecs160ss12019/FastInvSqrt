package com.example.ultrabreakout;

import android.graphics.BitmapFactory;
import android.os.Handler;

import java.util.ArrayList;

import static com.example.ultrabreakout.UltraBreakout.statsBarOffset;

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
    public static final int BALL_HEIGHT = 40;
    public static final int BALL_WIDTH = 40;

    // The maximum velocities for the ball in the x and y components.
    public static final int X_VELOCITY = 450;
    public static final int Y_VELOCITY = 450;
    public static final int BALL_POWERUP_TIME = 4000;
    public Handler ballTimer;
    private Runnable ballCallback;
    // Timer and handler to implement paddle width powerup object.
    BallState ballState;
    public enum BallState {
        INCREASE,
        DECREASE,
        NORMAL,
        GOLDEN,
    }

    public Ball(float x_pos, float y_pos, float x_vel, float y_vel) {
        super(x_pos, y_pos, x_vel, y_vel, BALL_WIDTH, BALL_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball3));
        ballTimer = new Handler();
        ballCallback = new Runnable() {
            @Override
            public void run() {
                normalBall();
            }
        };
    }

    public  Ball(Ball ball){
        super(
                ball.hitbox.left,
                ball.hitbox.top,
                ball.velocity.x,
                ball.velocity.y,
                BALL_WIDTH,
                BALL_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball3)
        );
        ballTimer = new Handler();
        ballCallback = new Runnable() {
            @Override
            public void run() {
                normalBall();
            }
        };

    }


    public void setGoldenBall(){
        ballTimer.removeCallbacks(ballCallback);
        ballTimer.postDelayed(ballCallback, BALL_POWERUP_TIME);
        this.ballState = BallState.GOLDEN;
        this.setSprite(R.drawable.goldenball);
    }

    public void increaseBallSpeed(){
        ballTimer.removeCallbacks(ballCallback);
        ballTimer.postDelayed(ballCallback, BALL_POWERUP_TIME);

        if (this.ballState == BallState.NORMAL){
            this.ballState = BallState.INCREASE;
            this.velocity.setSpeed((float)2.5);
        }
        else if (this.ballState == BallState.DECREASE){
            this.ballState = BallState.NORMAL;
            normalBall();
        }
    }
    public void decreaseBallSpeed(){
        ballTimer.removeCallbacks(ballCallback);
        ballTimer.postDelayed(ballCallback, BALL_POWERUP_TIME);
        if (this.ballState == BallState.NORMAL){
            this.ballState = BallState.DECREASE;
            this.velocity.setSpeed((float)0.25);
        }
        else if (this.ballState == BallState.INCREASE){
            this.ballState = BallState.NORMAL;
            normalBall();
        }

    }
    public void normalBall(){
        setSprite(R.drawable.ball3);
        this.ballState = BallState.NORMAL;
        this.velocity.x = X_VELOCITY;
        this.velocity.y = Y_VELOCITY;
    }

    public void update (float fps, float screenWidth){
        if ((hitbox.right > screenWidth && velocity.x > 0)
                || (hitbox.left < 0 && velocity.x < 0)){
            velocity.reverseX();
        }
        if ((hitbox.top < statsBarOffset && velocity.y < 0)){
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

    //Kills ball zero.
    public void die (Paddle paddle_zero){
        reset (paddle_zero);
        normalBall();
        paddle_zero.paddleWidthNormal();
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
