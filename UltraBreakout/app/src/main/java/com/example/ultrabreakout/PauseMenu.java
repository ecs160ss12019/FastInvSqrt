package com.example.ultrabreakout;


//Drawable Pause Menu using canvas and paint

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class PauseMenu extends DrawableMenu{
    DrawableButton ResumeButton;
    DrawableButton ExitButton;
    Rect ResumeArea;
    Rect ExitArea;
    Paint paint;
    Canvas canvas;

    public PauseMenu(int height, int width) {
        Area = new RectF(0,0,width,height);
        ResumeArea = new Rect();
        ExitArea = new Rect();
        this.width = width;
        this.height = height;
        this.paint = new Paint();
        this.canvas = new Canvas();
        paint.setTextSize(height/8);
        paint.getTextBounds("RESUME", 0, 6, ResumeArea);
        paint.getTextBounds("EXIT", 0, 4, ExitArea);
        int resumeY =  (int)((height * 8 / 12) - ( paint.descent() + paint.ascent()) / 2);
        int exitY = (int)((height * 10 / 12) - ( paint.descent() + paint.ascent()) / 2);
        adjustBoxArea(ResumeArea, width/2, resumeY);
        adjustBoxArea(ExitArea, width/2, exitY);
        ResumeButton = new DrawableButton(ResumeArea, width/2, resumeY, height/8, "RESUME");
        ExitButton = new DrawableButton(ExitArea, width/2, exitY, height/8, "EXIT");
    }



    public void draw(Canvas canvas, Paint paint, String str){
        //Draw PAUSED in middle of the screen
        paint.setARGB(100,130,130,180);
        canvas.drawRect(Area,paint);
        paint.setARGB(255,255,255,255);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(height/4);
        int centerX = width/2;
        int centerY = (int)((height / 4) - ( paint.descent() + paint.ascent()) / 2);
        canvas.drawText(str, centerX, centerY, paint);
        //Draw below the PAUSE
        ResumeButton.draw(canvas,paint);
        ExitButton.draw(canvas,paint);
    }

    public int handleClick(float x, float y){
        if (ResumeButton.contains(x,y)){
            return 2;
        } else if (ExitButton.contains(x,y)){
            return 1;
        } else {
            return 0;
        }
    }



}
