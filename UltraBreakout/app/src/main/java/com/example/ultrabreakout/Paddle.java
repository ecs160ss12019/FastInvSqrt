package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.BitmapFactory;


class Paddle extends Actor {
    public static final int PADDLE_WIDTH = 160;
    public static final int PADDLE_HEIGHT = 40;
    public static final int PADDLE_SPEED = 600;

    public Paddle(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.paddle));
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
}
