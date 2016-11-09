package com.epam;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class UniqueWordsFilterTest {

    @Test
    public void testGetListOfUniqueWords(){
        List<String> list = UniqueWordsFinder.getListOfBiggestUniqueWords("src/main/resources/romeo.txt");
        list.forEach(System.out::println);
        assertTrue(list.get(0).length() > 3);
    }
}
