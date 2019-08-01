package com.example.ultrabreakout;

import android.content.Intent;
import android.content.res.AssetManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Activity for the level select screen.
 * Able to select a level from a preconfigured number, and it sends
 * the choice to the UltraBreakoutActivity.
 */

public class LevelSelectMenu extends PopUpScreen {
    //array storing the names of all files in the level folder
    String[] level_file_names;
    //array storing resource id's of previous button so we can layout the next button right below it
    List<Integer> r_ids = new ArrayList<Integer>();

    Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configurePopUpScreen();
        get_all_Levels();
        setUpLevelSelectButtons();
        sound = Sound.getInstance();
        sound.play_background(getApplicationContext(), R.raw.background_1);
    }


    //Set up the Level selection
    private void configureButton(final int level, LinearLayout ll ){

        Button button = new Button(this);
        button.setBackgroundResource(R.drawable.breakout_tiles_01);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/8bit.TTF");
        button.setTypeface(typeface);


        int id = View.generateViewId();
        button.setId(id);
        r_ids.add(id);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        button.setLayoutParams(params);
        params.setMargins(0, 20, 0, 20);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(LevelSelectMenu.this, UltraBreakoutActivity.class);
                i.putExtra("csv_file",level_file_names[level]); //Sends level file name to activity
                startActivity(i);
            }
        });
        button.setText(level_file_names[level].substring(0,level_file_names[level].length() - 4) );
        ll.addView(button,params);

    }

    //For each file in the levels directory, builds a button that starts an UltraBreakoutActivity that sends the file name to the created activity
    private void setUpLevelSelectButtons(){
        //for (int i = 0; i < level_file_names.length; i++){
        //temporarily set as 2 until over lapping button position bug is fixed.
        ScrollView sv = (ScrollView)findViewById(R.id.popup_window);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < level_file_names.length; i++){    //currently there is a bug when generating greater than 2 buttons
            configureButton(i, ll);
        }
        sv.addView(ll);
    }

    //reads in all file names from assets/levels into our Levels array
    private void get_all_Levels(){
        AssetManager assetMgr = getResources().getAssets();
        try {
            level_file_names = assetMgr.list("levels");
        } catch (IOException e ){
            e.printStackTrace();
        }
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
