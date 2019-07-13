package com.example.ultrabreakout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

/* Main activity for project, simply sets up app and sends it to UltraBreakout
 * class.
 */
public class UltraBreakoutActivity extends AppCompatActivity {

    // Class that contains all the main logic for the game.
    private UltraBreakout ultraBreakout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get display of size, then pass of app to UltraBreakout class.

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ultraBreakout = new UltraBreakout(this, size.x, size.y);
        setContentView(ultraBreakout);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Leave for later
        //ultraBreakout.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Leave for later
        //ultraBreakout.pause();
    }
}
