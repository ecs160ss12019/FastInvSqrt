package com.example.ultrabreakout;

/*
 * Has a set placement of bricks and other actors,
 * and possibly graphics/music.
 *
 * TODO
 * Will this be read from a file,
 * or randomly generated each time?
 */


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Level {
    private static final int NUM_ROWS = 16;
    private static final int NUM_COLUMNS = 16;
    int[][] game_level = new int[NUM_ROWS][NUM_COLUMNS];
    List<List<String>> csv_file_data = new ArrayList<List<String>>();

    public Level(String csv_file_name, Context myContext){
        csv_file_data =  read_level(csv_file_name, myContext);
    }

    private List<List<String>> read_level(String csv_file_name, Context myContext){
        String path = "levels/" + csv_file_name;

        AssetManager assetManager = myContext.getAssets();
        try{
            InputStream csvStream = assetManager.open(path);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            BufferedReader reader = new BufferedReader(csvStreamReader);
            String line = null;
            for(int i =0; i < NUM_ROWS; i++){
                line = reader.readLine();
                csv_file_data.add(read_level_row(line));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return csv_file_data;
    }

    private List<String> read_level_row(String line){
        List<String> row = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)){
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                row.add(rowScanner.next());
            }
        }
        return row;
    }


}
