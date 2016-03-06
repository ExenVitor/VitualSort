package com.example.vitor.vitualsort.sort.algorithm;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Vitor Chen on 16-3-6.
 * mail: exen3995@gmail.com
 */
public class InsertionSort extends BaseSortAlgo{
    public InsertionSort(@Nullable ISortAlgoCallback callback) {
        super(callback);
    }

    @Override
    protected ArrayList<Integer> internalStart(ArrayList<Integer> srcArray) {
        for (int i = 1 ; i < srcArray.size(); i++){
            int key = srcArray.get(i);
            int j = i - 1;
            for(; j >= 0 && key < srcArray.get(j); j--){
                srcArray.set(j + 1, srcArray.get(j));
            }
            srcArray.set(j + 1, key);
            if(mCallback != null){
                onSortStep(srcArray);
            }
        }
        return srcArray;
    }
}
