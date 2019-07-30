package com.example.ultrabreakout;

/*
 * Handles the spikes that instantly kill you.
 *
 * TODO
 * Shouldn't be too hard.
 */

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.content.Context;

class Spike extends Actor {
    public static int SPIKE_HEIGHT;
    public static int SPIKE_WIDTH;


    public Spike(float x_pos, float y_pos) {
        super(x_pos, y_pos,0, 0, SPIKE_WIDTH, SPIKE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.spike5));
        //TODO: BITMAP IMAGE TOO SMALL, NEED TO SIZE UP TO MATCH HITBOX
    }

    public void collide (Ball ball){
        
    }
}
