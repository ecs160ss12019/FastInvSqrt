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

import java.util.HashMap;
import java.util.Map;
class Item extends Actor {
    private static final int ITEM_WIDTH = 40;
    private static final int ITEM_HEIGHT = 40;
    private static final Map<PowerUpType, Integer> itemMap = new HashMap<PowerUpType, Integer>(){{
        put(PowerUpType.PADDLE_WIDTH_INCREASE,R.drawable.breakout_tiles_48);
        put(PowerUpType.PADDLE_WIDTH_DECREASE,R.drawable.breakout_tiles_48);
        put(PowerUpType.GOLDEN_BALL,R.drawable.goldenball);
        put(PowerUpType.EXTRA_LIFE,R.drawable.lifeball2);
        put(PowerUpType.BALL_SPEED_INCREASE,R.drawable.featherball2);
        put(PowerUpType.BALL_SPEED_DECREASE,R.drawable.featherball2);
    }};


    public PowerUpType powerup;

    public Item(float x_pos, float y_pos, float x_vel, float y_vel, PowerUpType powerup) {
        super(x_pos, y_pos, x_vel, y_vel, ITEM_WIDTH, ITEM_HEIGHT,
                BitmapFactory.decodeResource(sprites,itemMap.get(powerup)));
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
        velocity.y = 400;
        updatePos(fps);
    }
}
