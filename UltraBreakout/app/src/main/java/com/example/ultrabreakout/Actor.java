package com.example.ultrabreakout;

import android.graphics.RectF;

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

    class Size {

        float width;
        float height;

        Size (){
            width = 0;
            height = 0;
        }

        Size (float x, float y){
            width = x;
            height = y;
        }

        void changeSize (float x, float y){
            width = x;
            height = y;
        }

        void Rotate(){
            width = -width;
            height = -height;
        }

    }

    RectF hitbox;
    Velocity velocity;
    Size size;
    //TODO: Graphics

    Actor (RectF _hitbox, float x_vel, float y_vel, float width, float height){
        hitbox = new RectF(_hitbox);
        velocity = new Velocity(x_vel, y_vel);
        size = new Size(width, height);
    }

    Actor (){
        hitbox = new RectF(0,0,0,0);
        velocity = new Velocity (0,0);
        size = new Size (0,0);
    }

    //Updates position of the Actor based on velocity.
    void update (float fps){
        this.hitbox.left += velocity.x / fps;
        this.hitbox.top += velocity.y / fps;
        this.hitbox.right = hitbox.left + size.width;
        this.hitbox.bottom = hitbox.top + size.height;
    }
}
