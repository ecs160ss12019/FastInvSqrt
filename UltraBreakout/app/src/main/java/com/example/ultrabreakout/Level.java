package com.example.ultrabreakout;

/*
 * Has a set placement of bricks and other actors,
 * and possibly graphics/music.
 *
 * TODO
 * Will this be read from a file,
 * or randomly generated each time?
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Level {
    private static final int NUM_ROWS = 16;
    private static final int NUM_COLUMNS = 16;
    int[][] game_level = new int[NUM_ROWS][NUM_COLUMNS];


    public Level(String csv_file_name){
        List<List<String>> csv_file_data =  read_level(csv_file_name);
    }

    private List<List<String>> read_level(String csv_file_name){
        List<List<String>> csv_file_data =  new ArrayList<List<String>>();
        File csv_file = new File(csv_file_name);
        try {
            Scanner scanner = new Scanner(csv_file);
            while(scanner.hasNextLine()){
                csv_file_data.add(read_level_row(scanner.nextLine()));
            }

        }
        catch(FileNotFoundException e){
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
