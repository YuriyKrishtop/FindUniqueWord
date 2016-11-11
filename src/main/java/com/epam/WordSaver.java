package com.epam;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class WordSaver {
    private Map<String, Integer> mapOfUniqueWords;
    private String filePathName;

    public WordSaver(Map<String, Integer> mapOfUniqueWords, String filePathName) {
        this.mapOfUniqueWords = mapOfUniqueWords;
        this.filePathName = filePathName;
    }

    public void saveUniqueWordsFromMap() throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(filePathName);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {
            mapOfUniqueWords.forEach((key, value) -> {
                if (value == 1) {
                    try {
                        bufferedWriter.write(key.concat(" "));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
