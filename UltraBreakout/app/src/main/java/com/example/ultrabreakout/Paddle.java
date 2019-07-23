package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.content.Context;

class Paddle extends Actor {
    public static final int PADDLE_WIDTH = 160;
    public static final int PADDLE_HEIGHT = 40;
    public static final int PADDLE_SPEED = 600;

    public Paddle(float x_pos, float y_pos, Context context) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                Color.RED);
        this.sprite = BitmapFactory.decodeResource(context.getResources(),R.drawable.paddle);
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
