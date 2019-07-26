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
import android.graphics.Bitmap;

class Item extends Actor {
    private static final int ITEM_WIDTH = 64;
    private static final int ITEM_HEIGHT = 64;
    public enum PowerUpType {
        NONE,                   // Normal block.
        PADDLE_WIDTH_INCREASE,  // Increase the width of the paddle.
        NUM_POWERUP_TYPES,
        GOLDEN_BALL,
    }
    public PowerUpType powerup;
    private static final int[] ITEM_SPRITES = new int[]{
            R.drawable.ball, R.drawable.spike};
    public Item(float x_pos, float y_pos, float x_vel, float y_vel, PowerUpType powerup) {
        super(x_pos, y_pos, x_vel, y_vel, ITEM_WIDTH, ITEM_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.spike));
        //trying to set the correct sprite with the correct item
        // currently only dropping one sprite (either ball or spike)
//        switch(powerup){
//            case NONE:
//                setSprite(BitmapFactory.decodeResource(sprites,ITEM_SPRITES[0]));
//            case PADDLE_WIDTH_INCREASE:
//                setSprite(BitmapFactory.decodeResource(sprites,ITEM_SPRITES[1]));
//                break;
//        }
        this.powerup = powerup;
    }
    public boolean hasFallen (int screenHeight){
        if (hitbox.bottom > screenHeight && velocity.y > 0){
            return true;
        }
        return false;
    }

    public void update(float fps){
        velocity.x = 0;
        velocity.y = 450;
        updatePos(fps);
    }
}
