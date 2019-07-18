package com.example.ultrabreakout;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;


public class LevelSelectPopUpScreen extends Activity {

    String[] level_file_names;     //array storing the names of all files in the level folder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set_pop_up_screen_size();
        get_all_Levels();
        configureButton(0);
    }

    private void set_pop_up_screen_size(){
        setContentView(R.layout.level_popup_window);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(0.8 * width),(int)(0.8* height));
    }

    //Set up the Level selection
    //Currently Only 1 button
    //TODO: dynamically generate buttons based on number of csv files.
    private void configureButton(final int level){
        String buttonID = "button" + level;
        int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
        Button button = (Button) findViewById(resourceID);
        if (button == null){
            Log.d("Debugging: ","can't find button");
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(LevelSelectPopUpScreen.this, UltraBreakoutActivity.class);
                i.putExtra("csv_file",level_file_names[level]); //Sends level file name to activity
                startActivity(i);
            }
        });

    }

    //reads in all file names from assets/levels into our Levels array
    private void get_all_Levels(){
        AssetManager assetMgr = getResources().getAssets();
        try {
            level_file_names = assetMgr.list("levels/");
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
