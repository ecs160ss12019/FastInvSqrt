package com.example.ultrabreakout;

import android.content.Context;
import android.view.SurfaceView;

public class UltraBreakout extends SurfaceView implements Runnable {

    private int screenWidth;
    private int screenHeight;

    public UltraBreakout(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void run() {
    }

    void draw() {
    }
}
