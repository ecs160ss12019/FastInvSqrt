package com.example.ultrabreakout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

/* Main activity for project, simply sets up app and sends it to UltraBreakout
 * class.
 */
public class UltraBreakoutActivity extends AppCompatActivity {

    // Class that contains all the main logic for the game.
    private UltraBreakout ultraBreakout;
    private Level level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Get display of size, then pass of app to UltraBreakout class.

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);

        // Read in level file
        String level_file = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            level_file = bundle.getString("csv_file");
        }
        level = new Level(level_file, this);


        ultraBreakout = new UltraBreakout(this, size.x, size.y, level);
        setContentView(ultraBreakout);
    }

    @Override
    public void onResume() {
        super.onResume();
        ultraBreakout.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        ultraBreakout.pause();
    }
}
