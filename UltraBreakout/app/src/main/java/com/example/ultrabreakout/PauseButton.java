package com.example.ultrabreakout;

import android.graphics.RectF;

public class PauseButton {
    float buttonWidth;
    float buttonHeight;


    RectF hitbox;
    public PauseButton ( int screenWidth, int screenHeight) {
        this.buttonWidth = screenWidth / (float) 10;
        this.buttonHeight = screenHeight / (float) 10;

        hitbox = new RectF(screenWidth/2, screenHeight/2, screenWidth/2+buttonWidth,screenHeight/2+buttonHeight);

    }


}
