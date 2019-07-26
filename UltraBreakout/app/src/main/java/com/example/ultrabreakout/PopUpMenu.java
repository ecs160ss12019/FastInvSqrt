package com.example.ultrabreakout;

import android.graphics.Point;
import android.util.DisplayMetrics;

public class PopUpMenu extends ScreenActivity {

    public void configurePopUpScreen(){
        setContentView(R.layout.level_popup_window);
        configureScreen();
        Point size;
        size = obtainScreenSize();
        getWindow().setLayout((int)(0.8 * size.x),(int)(0.8* size.y));
    }

}
