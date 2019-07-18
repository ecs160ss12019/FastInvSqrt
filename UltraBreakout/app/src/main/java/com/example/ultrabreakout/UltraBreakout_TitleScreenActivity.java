package com.example.ultrabreakout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class UltraBreakout_TitleScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureFullScreen();
        configureLevelsButton();
    }

    //makes activity FullScreen and sets contentView
    private void configureFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ultra_breakout__title_screen);
    }

    //Sets up On Click for Levels Button
    private void configureLevelsButton() {
        Button Levels_button = (Button) findViewById(R.id.LevelsButton);
        Levels_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(UltraBreakout_TitleScreenActivity.this, LevelSelectPopUpScreen.class));
            }
        });

    }

}
