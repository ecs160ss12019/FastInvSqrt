package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

class Paddle extends Actor {
    public static final int PADDLE_WIDTH = 160;
    public static final int PADDLE_HEIGHT = 40;
    public static final int PADDLE_SPEED = 600;
    public static final int PADDLE_POWERUP_TIME = 10000;

    // Timer and handler to implement paddle width powerup object.
    public Handler paddleWidthTimer;
    private Runnable paddleWidthCallback;

    public Paddle(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.breakout_tiles_56));

        // Initialize handler and callback for paddle width powerup. Handler is to reset
        // the paddle width after a certain amount of time.
        paddleWidthTimer = new Handler();
        paddleWidthCallback = new Runnable() {
            @Override
            public void run() {
                paddleWidthDecrease();
            }
        };
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
        if (width == PADDLE_WIDTH) {
            hitbox.right += 0.5 * PADDLE_WIDTH;
            hitbox.left -= 0.5 * width;
            width = PADDLE_WIDTH * 2;
        }
    }

    /* Reset the paddle back to original width after powerup ends.
     *
     */
    public void paddleWidthDecrease() {
        width = PADDLE_WIDTH;
        hitbox.left += 0.5 * width;
        hitbox.right -= 0.5 * width;
    }

    public void destroy() {
        paddleWidthTimer.removeCallbacks(paddleWidthCallback);
    }
}
