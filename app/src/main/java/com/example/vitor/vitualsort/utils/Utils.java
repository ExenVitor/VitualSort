package com.example.vitor.vitualsort.utils;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vitor on 16-3-6.
 */
public class Utils {

    /**
     * Generate random none repetition number array, range [1, arraySize + 1]
     * @param arraySize
     * @return null if arraySize <= 0
     */
    @Nullable
    public static ArrayList<Integer> genRandomNumArray(int arraySize){
        if(arraySize > 0){
            ArrayList<Integer> randomArray = new ArrayList<>(arraySize);
            ArrayList<Integer> tmpArray = new ArrayList<>(arraySize);
            for (int i = 0; i < arraySize; i++){
                tmpArray.add(i + 1);
            }

            Random random = new Random();
            while (tmpArray.size() > 0){
                int randomIndex = Math.abs(random.nextInt() % tmpArray.size());
                randomArray.add(tmpArray.remove(randomIndex));
            }

            return randomArray;
        }
        return null;
    }
}
