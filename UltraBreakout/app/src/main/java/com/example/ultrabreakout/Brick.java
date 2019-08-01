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

import java.util.ArrayList;
import java.util.Random;

class Brick extends Actor {

    // The powerup types that a block can hold.
    public enum PowerUpType {
        NONE,                   // Normal block.
        PADDLE_WIDTH_INCREASE,  // Increase the width of the paddle.
        NUM_POWERUP_TYPES,
        GOLDEN_BALL,
        PADDLE_WIDTH_DECREASE,
        BALL_SPEED_INCREASE,
        BALL_SPEED_DECREASE,
    }

    private static final int[] BRICK_SPRITES = new int[]{
            R.drawable.breakout_tiles_01, R.drawable.breakout_tiles_03, R.drawable.breakout_tiles_05,
            R.drawable.breakout_tiles_07, R.drawable.breakout_tiles_09, R.drawable.breakout_tiles_11,
            R.drawable.breakout_tiles_13, R.drawable.breakout_tiles_15, R.drawable.breakout_tiles_17,
            R.drawable.breakout_tiles_19 };

    private static final int[] BRICK_BROKEN_SPRITES = new int[]{
            R.drawable.breakout_tiles_02, R.drawable.breakout_tiles_04, R.drawable.breakout_tiles_06,
            R.drawable.breakout_tiles_08, R.drawable.breakout_tiles_10, R.drawable.breakout_tiles_12,
            R.drawable.breakout_tiles_14, R.drawable.breakout_tiles_16, R.drawable.breakout_tiles_18,
            R.drawable.breakout_tiles_20 };

    public static int BRICK_WIDTH;
    public static int BRICK_HEIGHT;
    private int health;
    private int brick_index;


    // The powerup types that the brick holds.
    public PowerUpType powerup;

    public Brick(float x_pos, float y_pos, PowerUpType powerup) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,
                        BRICK_SPRITES[0]));
        brick_index = new Random().nextInt(BRICK_SPRITES.length);
        setSprite(BRICK_SPRITES[brick_index]);
        this.powerup = powerup;
        this.health = 2;
    }

    public Brick(float x_pos, float y_pos, PowerUpType powerup, int sprite_num) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,sprite_num));
        this.powerup = powerup;
    }

    //Generates a brick; to be used with the generateActors function
    public static Brick generateBrick (final float j, final float i){
        float x_pos = j * BRICK_WIDTH;
        float y_pos = i * BRICK_HEIGHT * 2;
        if (Math.random() > 0.95) {
            return new Brick(
                            x_pos,
                            y_pos,
                            PowerUpType.PADDLE_WIDTH_INCREASE,
                            R.drawable.breakout_tiles_48
            );
        } else if (Math.random() > 0.95) {
            return new Brick(
                            x_pos,
                            y_pos,
                            PowerUpType.GOLDEN_BALL,
                            R.drawable.goldenball_tile
            );
        } else if (Math.random() > 0.95) {
            return new Brick(
                            x_pos,
                            y_pos,
                            PowerUpType.PADDLE_WIDTH_DECREASE,
                            R.drawable.breakout_tiles_48
            );
        } else {
            return new Brick(
                            x_pos,
                            y_pos,
                            PowerUpType.NONE
            );
        }
    }

    //Colliding event with ball
    //Either reduces its HP if it has any, or drops a powerup.
    public void collide (Ball ball){
        float vertical_dist = Math.min (
                Math.abs(hitbox.bottom - ball.hitbox.top),
                Math.abs(hitbox.top - ball.hitbox.bottom)
        );
        float horizontal_dist = Math.min (
                Math.abs(hitbox.left - ball.hitbox.right),
                Math.abs(hitbox.right - ball.hitbox.left)
        );
        if (ball.ballState != Ball.BallState.GOLDEN){
            if (vertical_dist >= horizontal_dist){
                ball.velocity.reverseX();
            }
            else {
                ball.velocity.reverseY();
            }
        }

        //Ball is trapped between two bricks, no damage
        if (!ball.isActive){
            return;
        }

        //Make brick take damage
        if (--health == 1) {
            setBrokenSprite();
        }

    }
    public void decrementHealth() {
            health --;

    }

    public int returnHealth() {

        return health;
    }


    public void setBrokenSprite() {
        setSprite(BRICK_BROKEN_SPRITES[brick_index]);
    }

    public Item.PowerUpType checkPowerUp(){
        switch(this.powerup){
            case PADDLE_WIDTH_DECREASE:
                return Item.PowerUpType.PADDLE_WIDTH_DECREASE;
            case PADDLE_WIDTH_INCREASE:
                return Item.PowerUpType.PADDLE_WIDTH_INCREASE;
            case GOLDEN_BALL:
                return Item.PowerUpType.GOLDEN_BALL;
            case BALL_SPEED_DECREASE:
                return Item.PowerUpType.BALL_SPEED_DECREASE;
            case BALL_SPEED_INCREASE:
                return Item.PowerUpType.BALL_SPEED_INCREASE;
            default:
                return Item.PowerUpType.NONE;
        }
    }



    //Updates the Brick
    public void Update (float fps){
        updatePos(fps);
    }
}
