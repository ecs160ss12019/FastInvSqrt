package com.example.ultrabreakout;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.util.function.Consumer;


public class DrawableButton {
    Rect Area;
    Boolean isText;
    Boolean isImage;
    int startX;
    int startY;
    String Text;
    float textSize;

    public DrawableButton(Rect Area, int x, int y, float textSize, String Text) {
        this.startX = x;
        this.startY = y;
        this.textSize = textSize;
        this.isText = true;
        this.Text = Text;
        this.Area = Area;
    }

    public DrawableButton(Rect Area, int x, int y) {

    }

    public boolean contains(float x, float y){
        return (x > Area.left && x < Area.right && y > Area.top && y < Area.bottom);
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        canvas.drawText(Text, startX, startY, paint);
    }


}
