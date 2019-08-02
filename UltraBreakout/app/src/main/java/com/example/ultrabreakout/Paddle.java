package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.BitmapFactory;
import android.os.Handler;

import java.util.ArrayList;

class Paddle extends Actor {
    public static final int PADDLE_WIDTH = 160;
    public static final int PADDLE_HEIGHT = 40;
    public static final int PADDLE_SPEED = 900;
    public static final int PADDLE_POWERUP_TIME = 10000;

    // Timer and handler to implement paddle width powerup object.
    public Handler paddleWidthTimer;
    private Runnable paddleWidthCallback;
    private ActorState paddleState;

    public Paddle(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.breakout_tiles_56));

        // Initialize handler and callback for paddle width powerup. Handler is to reset
        // the paddle width after a certain amount of time.
        paddleWidthTimer = new Handler();
        paddleWidthCallback = new Runnable() {
            @Override
            public void run() {
                paddleWidthNormal();
            }
        };
    }

    public void collide (Ball ball){
        float x_diff = ball.hitbox.centerX() - hitbox.centerX();
        float x_velocity = (x_diff / (width / 2)) * Ball.X_VELOCITY;
        ball.velocity.setVelocity(x_velocity, -ball.velocity.y);
    }

    public void update(float fps, Input input, float screenWidth){
        if (input.isPressLeft() && (hitbox.left > 0)){
            velocity.x = -PADDLE_SPEED;
        } else if (input.isPressRight() && (hitbox.right < screenWidth)){
            velocity.x = PADDLE_SPEED;
        } else {
            velocity.x = 0;
        }
        updatePos(fps);
    }

    public void powerup(Item item, ArrayList<Ball> balls, Stats stats){
        switch (item.powerup) {
            case PADDLE_WIDTH_INCREASE:
                this.paddleWidthIncrease();
                break;
            case PADDLE_WIDTH_DECREASE:
                this.paddleWidthDecrease();
                break;
            case GOLDEN_BALL:
                for (Ball ball : balls){
                    ball.setGoldenBall();
                }
                break;
            case EXTRA_LIFE:
                stats.incrementLives();
                break;
            case BALL_SPEED_DECREASE:
                for (Ball ball : balls){
                    ball.decreaseBallSpeed();
                }
                break;
            case BALL_SPEED_INCREASE:
                for (Ball ball : balls){
                    ball.increaseBallSpeed();
                }
                break;
            case DOUBLE_BALL:
                balls.add(new Ball (balls.get(0)));
        }
    }

    public void reset(float xpos){
        this.hitbox.left = xpos;
        this.hitbox.top = 950;
        this.hitbox.right = xpos + width;
        this.hitbox.bottom = height;
        this.velocity.x = 0;
        this.velocity.y = 0;
    }

    /* Increases the paddle width on powerup pickup.
     *
     */
    public void paddleWidthIncrease() {
        // Reset any current timer for the paddle width powerup.
        paddleWidthTimer.removeCallbacks(paddleWidthCallback);
        paddleWidthTimer.postDelayed(paddleWidthCallback, PADDLE_POWERUP_TIME);

        // Set the new coordinates and size for the paddle if not already bigger.
        if (paddleState == ActorState.NORMAL) {
            paddleState = ActorState.INCREASE;
            hitbox.right += 0.5 * PADDLE_WIDTH;
            hitbox.left -= 0.5 * width;
            width = PADDLE_WIDTH * 2;
        }
        else if(paddleState == ActorState.DECREASE){
            paddleWidthNormal();
            paddleState = ActorState.NORMAL;
            width = PADDLE_WIDTH * 2;
        }
    }

    /* Reset the paddle back to original width after powerup ends.
     *
     */
    public void paddleWidthDecrease() {
        paddleWidthTimer.removeCallbacks(paddleWidthCallback);
        paddleWidthTimer.postDelayed(paddleWidthCallback, PADDLE_POWERUP_TIME);
        if (paddleState == ActorState.NORMAL) {
            paddleState = ActorState.INCREASE;
            hitbox.right -= 0.5 * PADDLE_WIDTH;
            hitbox.left += 0.5 * width;
            width = PADDLE_WIDTH / 2;
        }
        else if(paddleState == ActorState.INCREASE){
            paddleWidthNormal();
            paddleState = ActorState.NORMAL;
            width = PADDLE_WIDTH / 2;
        }


    }
    public void paddleWidthNormal() {
        if (paddleState == ActorState.DECREASE) {
            paddleState = ActorState.INCREASE;
            hitbox.right += 0.5 * PADDLE_WIDTH;
            hitbox.left -= 0.5 * width;
            width = PADDLE_WIDTH * 2;
        }
        else if(paddleState == ActorState.INCREASE){
            hitbox.right -= 0.5 * PADDLE_WIDTH;
            hitbox.left += 0.5 * width;
            width = PADDLE_WIDTH / 2;
        }
        paddleState = ActorState.NORMAL;
        width = PADDLE_WIDTH;
        paddleWidthTimer.removeCallbacks(paddleWidthCallback);
    }

    public void destroy() {
        paddleWidthTimer.removeCallbacks(paddleWidthCallback);
    }
}
