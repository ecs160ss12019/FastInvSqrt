package com.example.ultrabreakout;

import android.content.Intent;
import android.content.res.AssetManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Activity for the level select screen.
 * Able to select a level from a preconfigured number, and it sends
 * the choice to the UltraBreakoutActivity.
 */

public class LevelSelectMenu extends PopUpMenu {
    //array storing the names of all files in the level folder
    String[] level_file_names;
    //array storing resource id's of previous button so we can layout the next button right below it
    List<Integer> r_ids = new ArrayList<Integer>();

    Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureScreen();
        configurePopUpScreen();
        get_all_Levels();
        setUpLevelSelectButtons();
        sound = Sound.getInstance();
        sound.play_background(getApplicationContext(), R.raw.background_1);
    }


    //Set up the Level selection
    private void configureButton(final int level){
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.popup_window);
        Button button = new Button(this);
        int id = View.generateViewId();
        button.setId(id);
        r_ids.add(id);
        Log.d("debugging", Integer.toString(button.getId()) );

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (level == 0) {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        } else {
            params.addRule(RelativeLayout.BELOW, r_ids.get(level-1));//r_ids.get(level - 1));
        }
        button.setLayoutParams(params);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(LevelSelectMenu.this, UltraBreakoutActivity.class);
                i.putExtra("csv_file",level_file_names[level]); //Sends level file name to activity
                startActivity(i);
            }
        });
        button.setText("button"+level);
        rl.addView(button);

    }

    //For each file in the levels directory, builds a button that starts an UltraBreakoutActivity that sends the file name to the created activity
    private void setUpLevelSelectButtons(){
        //for (int i = 0; i < level_file_names.length; i++){
        //temporarily set as 2 until over lapping button position bug is fixed.
        for (int i = 0; i < 2; i++){    //currently there is a bug when generating greater than 2 buttons
            configureButton(i);
        }
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
