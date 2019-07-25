package com.example.ultrabreakout;

/*
 * Handles the destroyable bricks on the field.
 * Each has its own HP.
 *
 * TODO
 * Decide how to put x_pos and y_pos in csv file for Level
 */

import android.graphics.BitmapFactory;


class Brick extends Actor {
    public static int BRICK_WIDTH;
    public static int BRICK_HEIGHT;

    public Brick(float x_pos, float y_pos) {
        super(x_pos, y_pos, 0, 0, BRICK_WIDTH, BRICK_HEIGHT,
                BitmapFactory.decodeResource(sprites,R.drawable.brick2));
    }

    //Updates the Brick
    public void Update (Ball ball){
            //Calculate which side of the brick the ball hit more
            //There's only vertical and horizontal hits; we always
            //reverse y if we hit either top or bottom regardless
            //Take the min because we won't intersect more than halfway
            /*FIXME: Ball can hit middle of two bricks and reverseX twice
             * when ascending, need to make check for if ball is "colliding"
             * with side of brick it's moving away from
             */
            float vertical_dist = Math.min (
                    Math.abs(hitbox.bottom - ball.hitbox.top),
                    Math.abs(hitbox.top - ball.hitbox.bottom)
            );
            float horizontal_dist = Math.min (
                    Math.abs(hitbox.left - ball.hitbox.right),
                    Math.abs(hitbox.right - ball.hitbox.left)
            );
            if (vertical_dist >= horizontal_dist){
                ball.velocity.reverseX();
            }
            else{
                ball.velocity.reverseY();
            }
            //Item dropped = new Item(bricks.get(i).hitbox.right,bricks.get(i).hitbox.bottom, 0, 5);
    }
}
