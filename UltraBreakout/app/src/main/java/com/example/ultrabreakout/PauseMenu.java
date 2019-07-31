package com.example.ultrabreakout;

//Drawable Pause Menu using canvas and paint

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class PauseMenu {
    RectF Area;
    int width;
    int height;
    DrawableButton ResumeButton;
    DrawableButton ExitButton;
    Rect ResumeArea;
    Rect ExitArea;
    Paint paint;
    Canvas canvas;

    public PauseMenu(int height, int width, Paint paint, Canvas canvas){
        Area = new RectF(0,0,width,height);
        ResumeArea = new Rect();
        ExitArea = new Rect();
        this.width = width;
        this.height = height;
        this.paint = new Paint();
        this.canvas = new Canvas();
        paint.setTextSize(height/8);
        paint.getTextBounds("Resume", 0, 6, ResumeArea);
        paint.getTextBounds("Exit", 0, 4, ExitArea);
        int resumeY =  (int)((height * 7 / 12) - ( paint.descent() + paint.ascent()) / 2);
        int exitY = (int)((height * 9 / 12) - ( paint.descent() + paint.ascent()) / 2);
        adjustBoxArea(ResumeArea, width/2, resumeY);
        adjustBoxArea(ExitArea, width/2, exitY);

        ResumeButton = new DrawableButton(ResumeArea, width/2, resumeY, height/8, "Resume");
        ExitButton = new DrawableButton(ExitArea, width/2, exitY, height/8, "Exit");
    }


    public void draw(Canvas canvas, Paint paint){
        //Draw PAUSED in middle of the screen
        paint.setARGB(100,130,130,180);
        canvas.drawRect(Area,paint);
        paint.setARGB(255,255,255,255);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(height/3);
        int centerX = width/2;
        int centerY = (int)((height / 4) - ( paint.descent() + paint.ascent()) / 2);
        canvas.drawText("PAUSED", centerX, centerY, paint);
        //Draw below the PAUSE
        ResumeButton.draw(canvas,paint);
        ExitButton.draw(canvas,paint);
    }

    public int handleClick(float x, float y){
        Log.d("X", Float.toString(x));
        Log.d("Y", Float.toString(y));
        Log.d("RESUME BUTTON-LEFT:", Float.toString(ResumeButton.Area.left));
        Log.d("RESUME BUTTON-RIGHT:", Float.toString(ResumeButton.Area.right));
        Log.d("RESUME BUTTON-TOP:", Float.toString(ResumeButton.Area.top));
        Log.d("RESUME BUTTON-BOTTOM:", Float.toString(ResumeButton.Area.bottom));


        if (ResumeButton.contains(x,y)){
            Log.d("option", "2");
            return 2;
        } else if (ExitButton.contains(x,y)){
            Log.d("option", "1");
            return 1;
        } else {
            Log.d("option", "0");
            return 0;
        }
    }

    public void adjustBoxArea(Rect Area, int offsetX, int offsetY){
        int uncenter = (Area.right - Area.left)/2;
        Area.bottom = Area.bottom + offsetY;
        Area.top = Area.top + offsetY;
        Area.left = Area.left + offsetX - uncenter;
        Area.right = Area.right + offsetX - uncenter;
    }

}
