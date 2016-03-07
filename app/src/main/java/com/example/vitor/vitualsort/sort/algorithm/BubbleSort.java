package com.example.vitor.vitualsort.sort.algorithm;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Vitor Chen on 16-3-7.
 * mail: exen3995@gmail.com
 */
public class BubbleSort extends BaseSortAlgo{

    public BubbleSort(@Nullable ISortAlgoCallback callback) {
        super(callback);
    }

    @Override
    protected ArrayList<Integer> internalStart(ArrayList<Integer> srcArray) {
        for (int i = srcArray.size() - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++){
                int key = srcArray.get(j - 1);
                if(key > srcArray.get(j)){
                    srcArray.set(j - 1, srcArray.get(j));
                    srcArray.set(j, key);
                }
            }
            if(mCallback != null){
                onSortStep(srcArray);
            }
        }
        return srcArray;
    }
}
