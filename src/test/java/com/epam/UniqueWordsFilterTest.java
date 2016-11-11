package com.epam;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UniqueWordsFilterTest {

    @Test
    public void testGetListOfUniqueWords() throws Exception{
        List<String> list = UniqueWordsFinder.getListOfBiggestUniqueWords("src/main/resources/1000mb.txt");
        list.forEach(System.out::println);
        assertTrue(!list.isEmpty());
    }
}
