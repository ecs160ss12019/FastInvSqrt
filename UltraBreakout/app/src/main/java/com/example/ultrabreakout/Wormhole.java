package com.example.ultrabreakout;

/*
 * Handles the wormholes that teleport the ball to
 * other locations.
 *
 * TODO
 * Doubt there's much to do here if the ball handles
 * the collision event.
 */

import android.graphics.BitmapFactory;
import android.graphics.Color;

class Wormhole extends Actor {
    private static final int PADDLE_WIDTH = 160;
    private static final int PADDLE_HEIGHT = 40;

    public Wormhole(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, PADDLE_WIDTH, PADDLE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball));
    }
}
