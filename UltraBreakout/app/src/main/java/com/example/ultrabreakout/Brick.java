package com.example.ultrabreakout;

/*
 * Handles the destroyable bricks on the field.
 * Each has its own HP.
 *
 * TODO
 * Decide how to put x_pos and y_pos in csv file for Level
 */

import android.graphics.Color;

class Brick extends Actor {
    public static int BRICK_WIDTH = 100;
    public static int BRICK_HEIGHT = 100;

    public Brick(float x_pos, float y_pos) {
        super(x_pos * BRICK_WIDTH, y_pos*BRICK_HEIGHT, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                Color.GREEN);
    }
}
