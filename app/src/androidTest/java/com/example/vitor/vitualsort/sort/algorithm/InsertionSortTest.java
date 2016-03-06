package com.example.vitor.vitualsort.sort.algorithm;

import com.example.vitor.vitualsort.utils.Utils;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Vitor Chen on 16-3-6.
 * mail: exen3995@gmail.com
 */
public class InsertionSortTest extends TestCase {

    public void testBeginSort() throws Exception {
        ArrayList<Integer> srcArray = Utils.genRandomNumArray(10);
        System.out.println("Src: " + srcArray.toString());
        InsertionSort insertionSort = new InsertionSort(new ISortAlgoCallback() {
            @Override
            public void onSortStep(ArrayList<Integer> currentArray) {
                System.out.println("onSortStep: " + currentArray.toString());
            }
        });
        ArrayList<Integer> result = insertionSort.beginSort(srcArray);
        System.out.println("Result: " + result.toString());
    }
}