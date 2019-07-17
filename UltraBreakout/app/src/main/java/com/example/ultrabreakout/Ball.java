package com.example.ultrabreakout;

import android.content.Context;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

/**
 * Handles the ball(s).
 * When the game updates, check if the ball has collided
 * (intersected) with anything after bouncing off the paddle.
 *
 * TODO
 * See "Ball.java" from Ch. 11
 * THE BALL HANDLES CHECKING WHEN AND WHERE IT HIT(S).
 */
//

class Ball extends Actor {
    private ImageView ball;
    Ball (Context context, ImageView ball){
        this.ball = ball;
        //ball.setX(100);// how i move it
    }
    void setY(float val){
        this.ball.setY(val);
    }
    void setX(float val){
        this.ball.setX(val);
    }
}
