package com.example.ultrabreakout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.tv.TvView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectPopUpScreen extends Activity {

    //array storing all csv file names of our stage levels
    List<String> Levels = new ArrayList<String>();




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
    //Currently Only 1 button, button is responsible for starting a UltraBreakout Activity
    private void configureButton(final int level){
        Button Levels_button = (Button) findViewById(R.id.Level1);
        Levels_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LevelSelectPopUpScreen.this, UltraBreakoutActivity.class);
                i.putExtra("csv_file",Levels.get(level));
                startActivity(i);
            }
        });
    }

    //reads in all file names from assets/levels into our Levels array
    private void get_all_Levels(){
        AssetManager assetMgr = getResources().getAssets();
        try {
            final String[] level_file_names = assetMgr.list("levels/");
            Log.d("Debugging", "In get_all_Levels");
        } catch (IOException e ){
          e.printStackTrace();
        }


        /*File folder = new File("/assets/levels/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String file_name = file.getName();
                System.out.println(file_name);
                Levels.add(file_name);
            }
        }*/
    }
}
