package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.Color;

class Paddle extends Actor {
    private static final int PADDLE_WIDTH = 160;
    private static final int PADDLE_HEIGHT = 40;

    public Paddle(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                Color.RED);
    }
}
