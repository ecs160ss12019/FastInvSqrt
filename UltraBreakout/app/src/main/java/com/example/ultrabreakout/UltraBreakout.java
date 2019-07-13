package com.example.ultrabreakout;

import android.content.Context;
import android.view.SurfaceView;

public class UltraBreakout extends SurfaceView implements Runnable {

    private int screen_width;
    private int screen_height;

    public UltraBreakout(Context context, int screen_width, int screen_height) {
        super(context);

        this.screen_width = screen_width;
        this.screen_height = screen_height;
    }

    @Override
    public void run() {
    }

    void draw() {
    }
}
