package com.example.ultrabreakout;

import android.content.res.Resources;
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
    public static Resources sprites;
        //FIXME Is this really a good idea, to have Actor store the resources as essentially global?

    RectF hitbox;
    Velocity velocity;

    //Should be constant width/height, unless size is increased
    float width;
    float height;
    Bitmap sprite;

    Actor (float x_pos, float y_pos, float x_vel, float y_vel,
           float _width, float _height,
           Bitmap _sprite){
        width = _width;
        height = _height;
        hitbox = new RectF(x_pos,y_pos,x_pos + width,y_pos + height);
        velocity = new Velocity(x_vel, y_vel);
        sprite = _sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    //Puts the actor in another position
    public void reposition (float x_pos, float y_pos){
        this.hitbox.left = x_pos;
        this.hitbox.top = y_pos;
        this.hitbox.right = x_pos + width;
        this.hitbox.bottom = y_pos + height;
    }

    //Updates pos based on velocity, shouldn't need to be called outside
    public void updatePos (float fps){
        hitbox.left += velocity.x / fps;
        hitbox.top += velocity.y / fps;
        hitbox.right = hitbox.left + width;
        hitbox.bottom = hitbox.top + height;
    }
}
