package com.example.ultrabreakout;

import android.graphics.RectF;

/*
 * Superclass for all in-game objects.
 * They all have position, velocity, and images.
 * Everything is a rectangle, by the way.
 *
 * TODO
 *
 */

class Actor {
    // The size and position of the object.
    private int width;
    private int height;

    private int x;
    private int y;

    // The getters for the variables of this class.
    public int getWidth() {
        return width;
    }
    public int getHeight() { return height; }

    public int getX() { return x; }
    public int getY() { return y; }
}
