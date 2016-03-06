package com.example.vitor.vitualsort.sort.worker;

import android.os.AsyncTask;

import com.example.vitor.vitualsort.sort.algorithm.BaseSortAlgo;
import com.example.vitor.vitualsort.sort.algorithm.ISortAlgoCallback;
import com.example.vitor.vitualsort.sort.algorithm.InsertionSort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vitor on 16-3-6.
 */
public class SortWorker {
    private static final long SLEEPING_TIME = 300;
    protected ArrayList<Integer> mSrcArray;
    protected ArrayList<Integer> mDstArray;

    protected ISortWorkerListener mListener;

    private SortTask mSortTask;

    public SortWorker(ISortWorkerListener listener){
        mListener = listener;
    }

    public ArrayList<Integer> getResult(){
        return mDstArray;
    }

    public void start(ArrayList<Integer> src){
        abort();
        mSrcArray = new ArrayList<>(src);
        if(!isRunning()){
            mSortTask = new SortTask();
            mSortTask.execute();
        }
    }

    public boolean isRunning(){
        if(mSortTask != null && !mSortTask.isCancelled()){
            return true;
        }
        return false;
    }

    public void abort(){
        if(isRunning()){
            mSortTask.cancel(true);
            mSortTask = null;
        }
    }

    private class SortTask extends AsyncTask<Void, Integer, ArrayList<Integer>>
            implements ISortAlgoCallback{
        private BaseSortAlgo mSortAlgo;
        public SortTask(){
            mSortAlgo = new InsertionSort(this);
        }

        @Override
        public void onSortStep(ArrayList<Integer> currentArray) {
            try {
                Thread.sleep(SLEEPING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(currentArray.toArray(new Integer[currentArray.size()]));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(mListener != null){
                mListener.onStarted(SortWorker.this);
            }
        }

        @Override
        protected ArrayList<Integer> doInBackground(Void... params) {
            return mSortAlgo.beginSort(mSrcArray);
        }

        @Override
        protected void onPostExecute(ArrayList<Integer> result) {
            super.onPostExecute(result);
            mSortTask = null;
            mDstArray = result;
            if(mListener != null){
                mListener.onFinished(SortWorker.this, mDstArray);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ArrayList<Integer> currentResult = null;
            if(values != null && values.length > 0){
                currentResult = new ArrayList<>(values.length);
                Collections.addAll(currentResult, values);
            }
            if(mListener != null){
                mListener.onProgressUpdate(SortWorker.this, currentResult);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if(mListener != null){
                mListener.onCanceled(SortWorker.this);
            }
        }
    }
}
