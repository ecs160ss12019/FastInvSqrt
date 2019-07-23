package com.example.ultrabreakout;

import android.graphics.BitmapFactory;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import android.graphics.Bitmap;
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
            y = -y;
        }

    }

    RectF hitbox;
    Velocity velocity;
    @ColorInt int color;

    //Should be constant width/height
    float width;
    float height;
    Bitmap sprite;

    Actor (float x_pos, float y_pos, float x_vel, float y_vel,
           float _width, float _height,
           @ColorInt int _color){
        width = _width;
        height = _height;
        hitbox = new RectF(x_pos,y_pos,x_pos + width,y_pos + height);
        velocity = new Velocity(x_vel, y_vel);
        color = _color;
        sprite = null;
    }


    //Updates position of the Actor based on velocity.
    void update (float fps){
        hitbox.left += velocity.x / fps;
        hitbox.top += velocity.y / fps;
        hitbox.right = hitbox.left + width;
        hitbox.bottom = hitbox.top + height;
    }
}
