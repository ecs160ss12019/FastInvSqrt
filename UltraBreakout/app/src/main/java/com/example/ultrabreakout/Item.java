package com.example.ultrabreakout;

/*,fs you.
 *
 * TODO
 * Figure out what items to implement to make the appropriate
 * changes to Paddle/Ball/Stats.
 *
 * If an Item is on the screen, the game update function
 * needs to additionally check if it collides with the Paddle
 * or vice versa.
 */

import android.graphics.BitmapFactory;
import android.graphics.Color;

class Item extends Actor {
    private static final int ITEM_WIDTH = 64;
    private static final int ITEM_HEIGHT = 64;

    public Item(float x_pos, float y_pos, float x_vel, float y_vel) {
        //FIXME: Come up with a standardized ball size
        super(x_pos, y_pos, x_vel, y_vel, ITEM_WIDTH, ITEM_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.spike));
    }
    public void update(float fps){
        velocity.x = 0;
        velocity.y = 450;
        updatePos(fps);
    }
}
