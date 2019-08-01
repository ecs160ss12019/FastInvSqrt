package com.example.ultrabreakout;

import android.graphics.Rect;
import android.graphics.RectF;

public class DrawableMenu {
    RectF Area;
    int width;
    int height;

    //Adjust Rect Area obtained from
    public void adjustBoxArea(Rect Area, int offsetX, int offsetY){
        int uncenter = (Area.right - Area.left)/2;
        Area.bottom = Area.bottom + offsetY;
        Area.top = Area.top + offsetY;
        Area.left = Area.left + offsetX - uncenter;
        Area.right = Area.right + offsetX - uncenter;
    }

}
