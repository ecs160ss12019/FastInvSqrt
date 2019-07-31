package com.example.ultrabreakout;

//Drawable Pause Menu using canvas and paint

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class PauseMenu {
    RectF Area;
    int width;
    int height;

    public PauseMenu(int height, int width){
        Area = new RectF(0,0,width,height);
        this.width = width;
        this.height = height;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setARGB(100,130,130,180);
        canvas.drawRect(Area,paint);
        paint.setARGB(255,255,255,255);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(height/3);
        int centerX = width/2;
        int centerY = (int)((height / 4) - ( paint.descent() + paint.ascent()) / 2);
        canvas.drawText("PAUSED", centerX, centerY, paint);
        paint.setTextSize(height/8);
        int resumeY =  (int)((height * 7 / 12) - ( paint.descent() + paint.ascent()) / 2);
        int exitY = (int)((height * 9 / 12) - ( paint.descent() + paint.ascent()) / 2);
        canvas.drawText("Resume", centerX, resumeY, paint);
        canvas.drawText("Exit", centerX, exitY, paint);
    }

}
