package com.example.vitor.vitualsort.utils;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by vitor on 16-3-6.
 */
public class UtilsTest extends TestCase {

    public void testGenRandomNumArray() throws Exception {
        int arraySize = 10;
        ArrayList<Integer> randomArray = Utils.genRandomNumArray(arraySize);
        System.out.println(randomArray);
        assertNotNull(randomArray);
        assertEquals(arraySize, randomArray.size());
    }
}