package com.example.ultrabreakout;

/*
 * Handles the destroyable bricks on the field.
 * Each has its own HP.
 *
 * TODO
 * Decide which constructor to use, I guess
 */

import android.graphics.BitmapFactory;

import androidx.annotation.DrawableRes;

import java.util.Random;

class Brick extends Actor {

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
    public int health;
    private int brick_index;


    // The powerup types that the brick holds.
    public PowerUpType powerup;

    public Brick(float x_pos, float y_pos, PowerUpType powerup, int sprite_num, int health) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,sprite_num));
        this.powerup = powerup;
        this.health = health;
    }

    //Generates a brick; to be used with the generateActors function
    public static Brick generateBrick (final float j, final float i){
        float x_pos = j * BRICK_WIDTH;
        float y_pos = i * BRICK_HEIGHT * 2;
        PowerUpType power_up = PowerUpType.NONE;
        @DrawableRes int sprite = BRICK_SPRITES[new Random().nextInt(BRICK_SPRITES.length)];
        int health = 2; //Defaults to 2 hits per brick

        //Roughly 10% of Bricks will contain a powerup
        if (Math.random() > 0.6) {
            health = 1; //One hit for powerup bricks
            switch ((int) (Math.random() * 7)) {
                //Set above number to one above number of powerups
                case 0:
                    power_up = PowerUpType.PADDLE_WIDTH_INCREASE;
                    sprite = R.drawable.breakout_tiles_48;
                    break;
                case 1:
                    power_up = PowerUpType.GOLDEN_BALL;
                    sprite = R.drawable.goldenball_tile;
                    break;
                case 2:
                    power_up = PowerUpType.PADDLE_WIDTH_DECREASE;
                    sprite = R.drawable.breakout_tiles_48;
                    break;
                case 3:
                    power_up = PowerUpType.BALL_SPEED_INCREASE;
                    sprite = R.drawable.featherball;
                    break;
                case 4:
                    power_up = PowerUpType.BALL_SPEED_DECREASE;
                    sprite = R.drawable.featherball2;
                    break;
                case 5:
                    power_up = PowerUpType.EXTRA_LIFE;
                    sprite = R.drawable.lifeball;
                case 6:
                    power_up = PowerUpType.DOUBLE_BALL;
                    sprite = R.drawable.lifeball2;
            }
        }
        return new Brick(
            x_pos,
            y_pos,
            power_up,
            sprite,
            health
        );
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
        if (ball.ballState != ActorState.GOLDEN){
            if (vertical_dist >= horizontal_dist){
                ball.velocity.reverseX();
            }
            else {
                ball.velocity.reverseY();
            }
        }

        //Make brick take damage
        if (--health == 1) {
            setBrokenSprite();
        }

    }


    public void setBrokenSprite() {
        setSprite(BRICK_BROKEN_SPRITES[brick_index]);
    }


    //Updates the Brick
    public void Update (float fps){
        updatePos(fps);
    }
}
