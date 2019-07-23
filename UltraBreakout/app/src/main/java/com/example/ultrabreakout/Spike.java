package com.example.ultrabreakout;

/*
 * Handles the spikes that instantly kill you.
 *
 * TODO
 * Shouldn't be too hard.
 */

import android.graphics.Color;

class Spike extends Actor {
    public static final int SPIKE_HEIGHT = 40;
    public static final int SPIKE_WIDTH = 40;


    public Spike(float x_pos, float y_pos) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos,0, 0, SPIKE_WIDTH, SPIKE_HEIGHT,
                Color.BLACK);
    }
}
