package com.example.ultrabreakout;

/*
 * Handles the paddle object.
 * Basically just a rectangle that moves back and forth.
 */

import android.graphics.Color;

class Paddle extends Actor {


    public Paddle(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel,y_vel, 40,40,
                Color.GREEN);
    }
}
