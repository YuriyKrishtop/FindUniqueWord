package com.epam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueWordsFinder {
    private static List<String> listOfBiggestUniqueWords = new ArrayList<>();
    private static Map<String, Integer> mapOfUniqueWords = new HashMap<>();

    public static List<String> getListOfBiggestUniqueWords(String fileName) {
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "utf8");) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            while ((currentLine = inFile.readLine()) != null) {
                String[] words = currentLine.split("[^a-zA-Z']+");
                putWordToMapOfUniqueWords(words);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        mapOfUniqueWords.forEach(UniqueWordsFinder::addWordToListOfBiggestUniqueWords);
        return listOfBiggestUniqueWords;
    }

    private static void putWordToMapOfUniqueWords(String[] words) {
        for (String word : words) {
            if (!word.isEmpty()) {
                if (mapOfUniqueWords.containsKey(word)) {
                    int oldVal = mapOfUniqueWords.get(word);
                    mapOfUniqueWords.put(word, ++oldVal);
                } else {
                    mapOfUniqueWords.put(word, 1);
                }
            }
        }
    }

    private static void addWordToListOfBiggestUniqueWords(String key, int val) {
        if (listOfBiggestUniqueWords.isEmpty()) {
            listOfBiggestUniqueWords.add(key);
        } else {
            if (val == 1) {
                if (key.length() == listOfBiggestUniqueWords.get(0).length()) {
                    listOfBiggestUniqueWords.add(key);
                } else if (key.length() > listOfBiggestUniqueWords.get(0).length()) {
                    listOfBiggestUniqueWords.clear();
                    listOfBiggestUniqueWords.add(key);
                }
            }
        }
    }
}
