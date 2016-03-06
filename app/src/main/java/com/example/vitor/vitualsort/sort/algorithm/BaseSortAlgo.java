package com.example.vitor.vitualsort.sort.algorithm;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Vitor Chen on 16-3-6.
 * mail: exen3995@gmail.com
 */
public abstract class BaseSortAlgo {

    protected ISortAlgoCallback mCallback;
    protected ArrayList<Integer> mTmpArray;

    protected BaseSortAlgo(@Nullable ISortAlgoCallback callback){
        mCallback = callback;
    }

    public ArrayList<Integer> beginSort(ArrayList<Integer> srcArray){
        if(srcArray == null)
            return null;
        if(srcArray.size() <= 1){
            return new ArrayList<>(srcArray);
        }

        if(mCallback != null){
            mTmpArray = new ArrayList<>(srcArray);
        }

        ArrayList<Integer> result = internalStart(mTmpArray);
        return result;
    }

    protected abstract ArrayList<Integer> internalStart(ArrayList<Integer> srcArray);

    protected void onSortStep(ArrayList<Integer> currentArray){
        if(mCallback != null){
            mCallback.onSortStep(currentArray);
        }
    }
}
