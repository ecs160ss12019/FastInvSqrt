package com.example.ultrabreakout;

/*
 * Handles the wormholes that teleport the ball to
 * other locations.
 *
 * TODO
 * Graphics for it
 */
import android.graphics.BitmapFactory;

class Wormhole extends Actor {
    private static final int WORMHOLE_WIDTH = 160;
    private static final int WORMHOLE_HEIGHT = 40;

    public float x_teleport;
    public float y_teleport;

    public Wormhole(float x_pos, float y_pos,
                    float x_teleport, float y_teleport) {
        super(x_pos, y_pos, 0, 0, WORMHOLE_WIDTH, WORMHOLE_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.ball));
        this.x_teleport = x_teleport;
        this.y_teleport = y_teleport;
    }

    public void collide (Ball ball){
        //TODO Should the wormhole disappear?
    }
}
