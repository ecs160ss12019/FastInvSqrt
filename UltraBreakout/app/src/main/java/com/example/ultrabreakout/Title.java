package com.example.ultrabreakout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Title extends AppCompatActivity {

    Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureFullScreen();
        configureLevelsButton();

        sound = Sound.getInstance();
        sound.play_background(getApplicationContext(), R.raw.background_1);
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
                startActivity(new Intent(Title.this, LevelSelectPopUpScreen.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        sound.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        sound.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
