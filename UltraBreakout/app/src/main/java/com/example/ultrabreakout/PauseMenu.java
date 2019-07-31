package com.example.ultrabreakout;

import android.graphics.RectF;

public class PauseMenu{
    RectF hitbox;
    PauseMenu(int screenWidth, int screenHeight) {
        hitbox =  new RectF((screenWidth/2) - (screenWidth / 3), (screenHeight/2) - (float) ((screenHeight / 1.5)), (screenWidth / 2) +(screenWidth/3), (screenHeight/2)+((float) (screenHeight/1.5)));
    }
}
