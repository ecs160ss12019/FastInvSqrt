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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LevelSelectPopUpScreen extends Activity {
    //array storing the names of all files in the level folder
    String[] level_file_names;
    //array storing resource id's of previous button so we can layout the next button right below it
    List<Integer> r_ids = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set_pop_up_screen_size();
        get_all_Levels();
        setUpLevelSelectButtons();
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
                Intent i = new Intent(LevelSelectPopUpScreen.this, UltraBreakoutActivity.class);
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
            level_file_names = assetMgr.list("levels/");
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
