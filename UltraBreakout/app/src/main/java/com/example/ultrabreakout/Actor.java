package com.example.ultrabreakout;

import android.graphics.RectF;

/*
 * Superclass for all in-game objects.
 * They all have position, velocity, and images.
 * Everything is a rectangle, by the way.
 * Note that it's impossible to make an Actor object.
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

        void setVelocity(float _x, float _y){
            x = _x;
            y = _y;
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

    //Updates position of Actor based on velocity.
    void Update (float fps){
        hitbox.left += velocity.x / fps;
        hitbox.top += velocity.y / fps;
        hitbox.right = hitbox.left + size.width;
        hitbox.bottom = hitbox.top + size.height;
    }

}
