package com.example.ultrabreakout;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import static com.example.ultrabreakout.UltraBreakout.statsBarOffset;

public class StatsBar {
    Stats stats;
    RectF Area;
    Paint paint;

    public StatsBar(Stats stats, int ScreenWidth){
        this.stats = stats;
        paint = new Paint();
        Area = new RectF(0,0, ScreenWidth, statsBarOffset + 20);

    }

    public void draw(Canvas canvas){
        paint.setARGB(255,5,8,38);
        canvas.drawRect(Area, paint);
        paint.setARGB(255,255,255,255);
        paint.setTextSize(statsBarOffset - 5);
        String livesText = " Lives: " + stats.lives + " ";
        float scoreOffset = paint.measureText(livesText);
        canvas.drawText(livesText, 0, statsBarOffset-3, paint);
        String scoreText = "Score: " + stats.score + " ";
        canvas.drawText(scoreText,scoreOffset+100,statsBarOffset-3,paint);
    }

}
