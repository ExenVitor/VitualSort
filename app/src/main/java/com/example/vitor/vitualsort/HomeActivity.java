package com.example.vitor.vitualsort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vitor.vitualsort.sort.worker.ISortWorkerListener;
import com.example.vitor.vitualsort.sort.worker.SortWorker;
import com.example.vitor.vitualsort.utils.Utils;
import com.example.vitor.vitualsort.widget.SortCanvas;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ISortWorkerListener {

    private int mArraySize;
    private ArrayList<Integer> mArray;
    private SortCanvas mSortCanvas;
    private SortWorker mSortWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupView();
    }

    private void setupView(){
        mArraySize = 20;
        mArray = Utils.genRandomNumArray(mArraySize);

        mSortCanvas = (SortCanvas) findViewById(R.id.sortCanvas);
        mSortCanvas.setArray(mArray);

        mSortWorker = new SortWorker(this);
    }

    public void startSorting(View view) {
        mSortWorker.start(mArray);
    }

    @Override
    public void onStarted(SortWorker worker) {

    }

    @Override
    public void onProgressUpdate(SortWorker worker, ArrayList<Integer> currentArray) {
        mSortCanvas.setArray(currentArray);
    }

    @Override
    public void onFinished(SortWorker worker, ArrayList<Integer> result) {
        mSortCanvas.setArray(result);
    }

    @Override
    public void onCanceled(SortWorker worker) {

    }

    public void restore(View view) {
        mSortCanvas.setArray(mArray);
    }

    public void roll(View view) {
        mArray = Utils.genRandomNumArray(mArraySize);
        mSortCanvas.setArray(mArray);
    }
}
