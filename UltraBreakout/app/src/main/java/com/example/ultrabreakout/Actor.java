package com.example.ultrabreakout;

import android.graphics.RectF;
import android.graphics.Canvas;

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

    //Contains the RGB+A coloring for the object
    class Color {
        int red;
        int green;
        int blue;
        int alpha;

        Color (int r_color_channel, int g_color_channel,
               int b_color_channel, int a_color_channel){
            red = r_color_channel;
            green = g_color_channel;
            blue = b_color_channel;
            alpha = a_color_channel;
        }
    }

    RectF hitbox;
    Velocity velocity;
    Color color;

    float width;
    float height;

    //TODO: Graphics

    Actor (float x_pos, float y_pos, float x_vel, float y_vel,
           float _width, float _height,
           int r_color_channel, int g_color_channel,
           int b_color_channel, int a_color_channel){
        width = _width;
        height = height;
        hitbox = new RectF(x_pos,y_pos + height,x_pos + width,y_pos);
        velocity = new Velocity(x_vel, y_vel);
        color = new Color(r_color_channel, g_color_channel, b_color_channel, a_color_channel);
    }

    Actor (){
        hitbox = new RectF(0,0,0,0);
        velocity = new Velocity (0,0);
        color = new Color(0,0,0,0);
    }

    //Draws the actor on the canvas based on position and color
    void Draw (){

    }

    //Updates position of the Actor based on velocity.
    void Update (float fps){
        hitbox.left += velocity.x / fps;
        hitbox.top += velocity.y / fps;
        hitbox.right = hitbox.left + width;
        hitbox.bottom = hitbox.top + height;
    }
}
