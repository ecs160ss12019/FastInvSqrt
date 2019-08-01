package com.example.ultrabreakout;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class GameOverMenu extends DrawableMenu{
    DrawableButton RetryButton;
    DrawableButton ExitButton;
    Rect RetryArea;
    Rect ExitArea;
    Paint paint;
    Canvas canvas;

    public GameOverMenu(int height, int width){
        Area = new RectF(0,0,width,height);
        RetryArea = new Rect();
        ExitArea = new Rect();
        this.width = width;
        this.height = height;
        this.paint = new Paint();
        this.canvas = new Canvas();
        paint.setTextSize(height/8);
        paint.getTextBounds("RETRY", 0, 5, RetryArea);
        paint.getTextBounds("EXIT", 0, 4, ExitArea);
        int resumeY =  (int)((height * 7 / 12) - ( paint.descent() + paint.ascent()) / 2);
        int exitY = (int)((height * 9 / 12) - ( paint.descent() + paint.ascent()) / 2);
        adjustBoxArea(RetryArea, width/2, resumeY);
        adjustBoxArea(ExitArea, width/2, exitY);
        RetryButton = new DrawableButton(RetryArea, width/2, resumeY, height/8, "RETRY");
        ExitButton = new DrawableButton(ExitArea, width/2, exitY, height/8, "EXIT");
    }



    public void draw(Canvas canvas, Paint paint, String str, boolean victory){
        //Draw GAMEOVER in middle of the screen
        if (victory) {
            paint.setARGB(80,70,130,150);
        } else {
            paint.setARGB(120, 220, 30, 30);
        }
        canvas.drawRect(Area,paint);
        paint.setARGB(255,255,255,255);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(height/3);
        int centerX = width/2;
        int centerY = (int)((height / 4) - ( paint.descent() + paint.ascent()) / 2);
        canvas.drawText(str, centerX, centerY, paint);
        //Draw below the GAMEOVER
        RetryButton.draw(canvas,paint);
        ExitButton.draw(canvas,paint);
    }

    public int handleClick(float x, float y){
        if (RetryButton.contains(x,y)){
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

}
