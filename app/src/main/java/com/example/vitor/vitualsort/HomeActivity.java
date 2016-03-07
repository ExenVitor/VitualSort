package com.example.vitor.vitualsort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vitor.vitualsort.sort.algorithm.BaseSortAlgo;
import com.example.vitor.vitualsort.sort.algorithm.BubbleSort;
import com.example.vitor.vitualsort.sort.algorithm.InsertionSort;
import com.example.vitor.vitualsort.sort.worker.ISortWorkerListener;
import com.example.vitor.vitualsort.sort.worker.SortWorker;
import com.example.vitor.vitualsort.utils.Utils;
import com.example.vitor.vitualsort.widget.SortCanvas;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ISortWorkerListener {

    private static class AlgoInfo{
        int menuId;
        Class<? extends BaseSortAlgo> clazz;
        int titleResId;

        public AlgoInfo(int menuId, Class<? extends BaseSortAlgo> clazz, int titleResId) {
            this.menuId = menuId;
            this.clazz = clazz;
            this.titleResId = titleResId;
        }
    }

    private static final AlgoInfo[] ALGO_INFO_ARRAY = new AlgoInfo[]{
            new AlgoInfo(R.id.bubble_sort, BubbleSort.class, R.string.bubbleSort),
            new AlgoInfo(R.id.insertion_sort, InsertionSort.class, R.string.insertionSort)
    };

    private static final SparseArray<AlgoInfo> ALGO_INFO_MAP = new SparseArray<>();
    static {
        for (int i = 0; i < ALGO_INFO_ARRAY.length; i++){
            AlgoInfo info = ALGO_INFO_ARRAY[i];
            ALGO_INFO_MAP.put(info.menuId, info);
        }
    }

    private int mArraySize;
    private ArrayList<Integer> mArray;
    private SortCanvas mSortCanvas;
    private SortWorker mSortWorker;
    private TextView mAlgoTitle;

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

        mAlgoTitle = (TextView) findViewById(R.id.sortTitle);

        mSortWorker = new SortWorker(this);

        AlgoInfo defaultInfo = ALGO_INFO_ARRAY[0];
        mSortWorker.setSortAlgorithm(defaultInfo.clazz);
        mAlgoTitle.setText(defaultInfo.titleResId);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        for (int i = 0; i < ALGO_INFO_ARRAY.length; i++) {
            AlgoInfo info = ALGO_INFO_ARRAY[i];
            menu.add(0, info.menuId, Menu.FIRST + i, info.titleResId);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int menuId = item.getItemId();
        AlgoInfo info = ALGO_INFO_MAP.get(menuId);
        if(info != null){
            mSortWorker.setSortAlgorithm(info.clazz);
            mAlgoTitle.setText(info.titleResId);
        }
        return true;
    }
}
