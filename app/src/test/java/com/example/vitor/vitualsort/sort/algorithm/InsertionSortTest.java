package com.example.vitor.vitualsort.sort.algorithm;

import com.example.vitor.vitualsort.testutils.TestUtils;
import com.example.vitor.vitualsort.utils.Utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author <a href="mailto:exen3995@gmail.com">Vitor Chen</a>
 * @version v1.0
 * @create 2016/3/7 14:34
 * @update 2016/3/7 14:34
 * @since v1.0
 */
public class InsertionSortTest {

    @Test
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
        Assert.assertTrue("Sorting algorithm incorrect！！", TestUtils.verifySortingArray(result, false));
    }
}