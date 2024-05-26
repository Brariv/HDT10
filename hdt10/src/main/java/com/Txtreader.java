package com;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Txtreader {
    

    public ArrayList<ArrayList<String>> readTxt(String path) {
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                ArrayList<String> fline = new ArrayList<String>();
                String[] parts = line.split(" ");
                for (String part : parts) {
                    fline.add(part);
                }
                line = reader.readLine();
                lines.add(fline);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }    
}
