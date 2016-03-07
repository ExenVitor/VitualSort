package com.example.vitor.vitualsort.testutils;

import java.util.ArrayList;

/**
 * @author <a href="mailto:exen3995@gmail.com">Vitor Chen</a>
 * @version v1.0
 * @create 2016/3/7 14:42
 * @update 2016/3/7 14:42
 * @since v1.0
 */
public class TestUtils {
    public static boolean verifySortingArray(ArrayList<Integer> array, boolean isDesc){
        if(array == null){
            return false;
        }
        if(array.size() == 1){
            return true;
        }

        int key = array.get(0);
        for (int i = 1; i < array.size(); i++){
            if((key > array.get(i) && !isDesc) || (key < array.get(i) && isDesc)){
                return false;
            }
            key = array.get(i);
        }
        return true;
    }

}
