package com.example.ultrabreakout;


//Drawable Pause Menu using canvas and paint

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

public class PauseMenu extends DrawableMenu{

    public PauseMenu(int height, int width) {
        Area = new RectF(0,0,width,height);
        this.paint = new Paint();
        this.canvas = new Canvas();
        this.width = width;
        this.height = height;

        ButtonsList = new ArrayList<DrawableButton>();
        paint.setTextSize(height/8);
        ButtonsList.add(buildDrawableButton("EXIT", width/2,
                (int)((height * 9 / 12) - ( paint.descent() + paint.ascent()) / 2) ));
        ButtonsList.add(buildDrawableButton("RESUME", width/2,
                (int)((height * 7 / 12) - ( paint.descent() + paint.ascent()) / 2) ));
    }

}
