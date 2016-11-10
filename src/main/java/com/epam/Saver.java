package com.epam;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class Saver {
    private Map<String, Integer> mapOfUniqueWords;
    private String filePathName;

    public Saver(Map<String, Integer> mapOfUniqueWords, String filePathName) {
        this.mapOfUniqueWords = mapOfUniqueWords;
        this.filePathName = filePathName;
    }

    public void saveUniqueWordsFromMap() {
        try (FileOutputStream fileOutput = new FileOutputStream(filePathName);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput);) {
            BufferedWriter outFile = new BufferedWriter(outputStreamWriter);


        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
