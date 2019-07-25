package com.example.ultrabreakout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Title extends ScreenActivity {

    Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureScreen();
        setContentView(R.layout.activity_ultra_breakout__title_screen);
        configureLevelsButton();

        sound = Sound.getInstance();
        sound.play_background(getApplicationContext(), R.raw.background_1);
    }

    //makes activity FullScreen and sets contentView



    //Sets up On Click for Levels Button
    private void configureLevelsButton() {
        Button Levels_button = (Button) findViewById(R.id.LevelsButton);
        Levels_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Title.this, LevelSelectMenu.class));
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
