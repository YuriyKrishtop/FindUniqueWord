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
    private static int numberTmpFile;

    public static List<String> getListOfBiggestUniqueWords(String fileName) throws InterruptedException {
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords);) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            while ((currentLine = inFile.readLine()) != null) {
                String[] words = currentLine.split("[^a-zA-Z']+");
                putWordToMapOfUniqueWords(words);
                if(Runtime.getRuntime().freeMemory() < 10*1024*1024) {
                    long a1 = Runtime.getRuntime().freeMemory();
                    WordSaver wordSaver = new WordSaver(mapOfUniqueWords,
                            fileName.concat("_part_").concat(Integer.toString(++numberTmpFile).concat(".tmp")));
                    long a2 = Runtime.getRuntime().freeMemory();
                    wordSaver.saveUniqueWordsFromMap();
                    long a4 = Runtime.getRuntime().freeMemory();
                    long a5 = Runtime.getRuntime().freeMemory();
                    mapOfUniqueWords.clear();
                    Runtime.getRuntime().runFinalization();
                    Thread.sleep(5000);
                    long a6 = Runtime.getRuntime().freeMemory();
                    Thread.sleep(5000);
                }
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
