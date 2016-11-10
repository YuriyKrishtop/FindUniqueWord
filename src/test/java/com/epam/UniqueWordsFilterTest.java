package com.epam;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UniqueWordsFilterTest {

    @Test
    public void testGetListOfUniqueWords(){
        List<String> list = UniqueWordsFinder.getListOfBiggestUniqueWords("src/main/resources/100mb.txt");
        list.forEach(System.out::println);
//        assertTrue(list.get(0).length() > 3);
        assertTrue(!list.isEmpty());
    }
}
