package com.example.ultrabreakout;

/*
 * Handles the destroyable bricks on the field.
 * Each has its own HP.
 *
 * TODO
 * Decide how to put x_pos and y_pos in csv file for Level
 */

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.content.Context;

class Brick extends Actor {
    public static int BRICK_WIDTH = 100;
    public static int BRICK_HEIGHT = 100;

    public Brick(float x_pos, float y_pos, Context context) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                Color.GREEN);
        //TODO: NEED TO SCALE BITMAP IMAGE TO HITBOX SIZE
        this.sprite = BitmapFactory.decodeResource(context.getResources(),R.drawable.brick);
    }
}
