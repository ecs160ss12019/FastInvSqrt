package com.example.ultrabreakout;

//Drawable Pause Menu using canvas and paint

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class PauseMenu {
    RectF Area;


    public PauseMenu(int left, int top, int right, int bottom){
        RectF Area = new RectF(left,top,right,bottom);
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setARGB(100,130,130,180);
        canvas.drawRect(Area,paint);
    }
}
