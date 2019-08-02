package com.example.ultrabreakout;

import android.graphics.BitmapFactory;

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
