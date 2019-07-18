package com.example.ultrabreakout;

import android.graphics.RectF;
import androidx.annotation.ColorInt;

/*
 * Superclass for all in-game objects.
 * They all have position, velocity, and images.
 * Everything is a rectangle, by the way.
 *
 * TODO
 * Graphics support! Must be done ASAP.
 *
 */

class Actor {

    class Velocity {

        float x;
        float y;

        Velocity (){
            x = 0;
            y = 0;
        }

        Velocity (float _x, float _y){
            x = _x;
            y = _y;
        }

        //Actual velocity; speed and direction
        void setVelocity(float _x, float _y){
            x = _x;
            y = _y;
        }

        //Just speed, not direction
        void setSpeed(float speed_multiplier){
            x *= speed_multiplier;
            y *= speed_multiplier;
        }

        void reverseX(){
            x = -x;
        }

        void reverseY(){
            x = -x;
        }

    }

    RectF hitbox;
    Velocity velocity;
    @ColorInt int color;

    //Should be constant width/height
    float width;
    float height;


    Actor (float x_pos, float y_pos, float x_vel, float y_vel,
           float _width, float _height,
           @ColorInt int _color){
        width = _width;
        height = _height;
        hitbox = new RectF(x_pos,y_pos + height,x_pos + width,y_pos);
        velocity = new Velocity(x_vel, y_vel);
        color = _color;
    }

    //Updates position of the Actor based on velocity.
    void Update (float fps){
        hitbox.left += velocity.x / fps;
        hitbox.top += velocity.y / fps;
        hitbox.right = hitbox.left + width;
        hitbox.bottom = hitbox.top + height;
    }
}
