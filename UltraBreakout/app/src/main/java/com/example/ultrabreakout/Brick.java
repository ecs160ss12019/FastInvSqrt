package com.example.ultrabreakout;

/*
 * Handles the destroyable bricks on the field.
 * Each has its own HP.
 *
 * TODO
 * Decide which constructor to use, I guess
 */

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import java.util.Random;

class Brick extends Actor {

    // The powerup types that a block can hold.
    public enum PowerUpType {
        NONE,                   // Normal block.
        PADDLE_WIDTH_INCREASE,  // Increase the width of the paddle.
        NUM_POWERUP_TYPES,
    }

    private static final int[] BRICK_SPRITES = new int[]{
            R.drawable.breakout_tiles_01, R.drawable.breakout_tiles_03, R.drawable.breakout_tiles_05,
            R.drawable.breakout_tiles_07, R.drawable.breakout_tiles_09, R.drawable.breakout_tiles_11,
            R.drawable.breakout_tiles_13, R.drawable.breakout_tiles_15, R.drawable.breakout_tiles_17,
            R.drawable.breakout_tiles_19 };

    public static int BRICK_WIDTH;
    public static int BRICK_HEIGHT;
    private int health;

    // The powerup types that the brick holds.
    public PowerUpType powerup;

    public Brick(float x_pos, float y_pos, PowerUpType powerup) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,
                        BRICK_SPRITES[new Random().nextInt(BRICK_SPRITES.length)]));
        this.powerup = powerup;
        this.health = 2;
    }

    public Brick(float x_pos, float y_pos, PowerUpType powerup, int sprite_num) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,sprite_num));
        this.powerup = powerup;
    }

    //Colliding event with ball
    //Either reduces its HP if it has any, or drops a powerup.
    public void collide (Ball ball, Paddle paddle){
        //If HP>1, reduce HP
        //Else, do the following powerup code
        switch (powerup) {
            case PADDLE_WIDTH_INCREASE:
                paddle.paddleWidthIncrease();
                break;
        }
    }

    //Updates the Brick
    public void Update (float fps){
        updatePos(fps);
    }
}
