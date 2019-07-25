package com.example.ultrabreakout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

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

        // Get display of size, then pass of app to UltraBreakout class.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

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
    protected void onResume() {
        super.onResume();

        ultraBreakout.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ultraBreakout.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ultraBreakout.destroy();
    }
}
