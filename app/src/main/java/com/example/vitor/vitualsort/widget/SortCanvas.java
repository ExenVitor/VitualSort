package com.example.vitor.vitualsort.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.vitor.vitualsort.R;

import java.util.ArrayList;

/**
 * Created by vitor on 16-3-6.
 */
public class SortCanvas extends View {

    private ArrayList<Integer> mArray;
    private float mHistogramUnitW;
    private float mHistogramUnitH;
    private Paint mHistFillPaint;
    private Paint mHistStrokePaint;
    private int mHistFillColor;
    private int mHistStrokeColor;
    private float mHistStrokeWidth;
    private float mHistSpacing;
    private RectF mHistRect;
    private RectF mStrokeRect;

    public SortCanvas(Context context) {
        this(context, null);
    }

    public SortCanvas(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SortCanvas(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public void setArray(ArrayList<Integer> array){
        mArray = array;
        calcHistogramSize();
    }

    private void initView()
    {
        setWillNotDraw(false);
        Resources res = getResources();
        mHistRect = new RectF();
        mStrokeRect = new RectF();

        mHistFillColor = res.getColor(R.color.histFillColor);
        mHistStrokeColor = res.getColor(R.color.histStrokeColor);
        mHistSpacing = res.getDimension(R.dimen.histSpacing);
        mHistStrokeWidth = res.getDimension(R.dimen.histStokeWidth);

        mHistFillPaint = new Paint();
        mHistFillPaint.setStyle(Paint.Style.FILL);
        mHistFillPaint.setAntiAlias(true);
        mHistFillPaint.setColor(mHistFillColor);

        mHistStrokePaint = new Paint();
        mHistStrokePaint.setStyle(Paint.Style.STROKE);
        mHistStrokePaint.setAntiAlias(true);
        mHistStrokePaint.setColor(mHistStrokeColor);
        mHistStrokePaint.setStrokeWidth(mHistStrokeWidth);

    }

    private void calcHistogramSize(){
        if(mArray != null && getWidth() > 0 && getHeight() > 0){
            int canvasW = getWidth() - getPaddingLeft() - getPaddingRight();
            int canvasH = getHeight() - getPaddingTop() - getPaddingBottom();
            int arraySize = mArray.size();
            mHistogramUnitW = (canvasW - mHistSpacing * (arraySize - 1)) / arraySize;
            mHistogramUnitH = (float)canvasH / arraySize;
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calcHistogramSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mArray != null && mHistogramUnitW > 0 && mHistogramUnitH > 0){
            float baseLineY = getHeight() - getPaddingBottom();
            float baseLineStartX = getPaddingLeft();
            float halfStroke = mHistStrokeWidth / 2.f;
            for (int i = 0; i < mArray.size(); i++){
                float preSpacingSum = mHistSpacing * i;
                float preHistWSum = mHistogramUnitW * i;
                float histLeft = baseLineStartX + preHistWSum + preSpacingSum;
                float histHeight = mHistogramUnitH * mArray.get(i);

                mHistRect.left = histLeft;
                mHistRect.right = mHistRect.left + mHistogramUnitW;
                mHistRect.top = baseLineY - histHeight;
                mHistRect.bottom = baseLineY;

                mStrokeRect.set(mHistRect);
                mStrokeRect.inset(halfStroke, halfStroke);

                canvas.drawRect(mHistRect, mHistFillPaint);
                canvas.drawRect(mStrokeRect, mHistStrokePaint);
            }
        }
    }
}
