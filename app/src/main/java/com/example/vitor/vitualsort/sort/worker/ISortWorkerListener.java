package com.example.vitor.vitualsort.sort.worker;

import java.util.ArrayList;

/**
 * Created by vitor on 16-3-6.
 */
public interface ISortWorkerListener {
    void onStarted(SortWorker worker);
    void onProgressUpdate(SortWorker worker, ArrayList<Integer> currentArray);
    void onFinished(SortWorker worker, ArrayList<Integer> result);
    void onCanceled(SortWorker worker);
}
