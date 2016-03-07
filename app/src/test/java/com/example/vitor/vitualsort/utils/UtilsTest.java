package com.example.vitor.vitualsort.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author <a href="mailto:exen3995@gmail.com">Vitor Chen</a>
 * @version v1.0
 * @create 2016/3/7 14:28
 * @update 2016/3/7 14:28
 * @since v1.0
 */
public class UtilsTest {

    @Test
    public void testGenRandomNumArray() throws Exception {
        int arraySize = 10;
        ArrayList<Integer> randomArray = Utils.genRandomNumArray(arraySize);
        System.out.println(randomArray);
        Assert.assertNotNull(randomArray);
        Assert.assertEquals(arraySize, randomArray.size());
    }
}