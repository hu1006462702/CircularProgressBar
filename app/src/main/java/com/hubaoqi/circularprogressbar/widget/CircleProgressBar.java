package com.hubaoqi.circularprogressbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hubaoqi.circularprogressbar.utils.TextUtil;

/**
 * Created by 胡宝齐 on 2016/11/17.
 * CircleProgressBar
 */
public class CircleProgressBar extends View {
    /**
     * 进度条百分比
     */
    private int progress = 0;
    private Context context;
    private Paint mPaint;// 画笔

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init() {
        mPaint = new Paint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode =MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize =MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode =MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize =MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode ==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(TextUtil.getWidthSize(600),TextUtil.getHightSize(600));
        }else if (widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(TextUtil.getWidthSize(600),heightSpecSize);
        }else if (heightSpecMode==MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize,TextUtil.getHightSize(600));
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        final int paddingLeft =getPaddingLeft();
        final int paddingRight =getPaddingRight();
        final int paddingBottom =getPaddingBottom();
        final int paddingTop =getPaddingTop();
        int width =getWidth()-paddingLeft-paddingRight;
        int height =getHeight()-paddingBottom-paddingTop;
        
        super.onDraw(canvas);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.FILL);

        
        for (int i = 0; i < 100; i++) {
            if (i >= progress) {
                //进行
                mPaint.setColor(Color.parseColor("#8dc5ff"));//
            } else {
                mPaint.setColor(Color.parseColor("#2ecc71"));//
            }
            canvas.save();//保存当前画布状态
            Log.d(width / 2 + "", height / 2 + "");
            canvas.translate(width / 2, height / 2); //将坐标中心平移到要围绕的坐标点x,y
            canvas.rotate(45 + i * 3.6f);//旋转角度，这里比如90度
            canvas.drawLine(width / 4, height / 4, width / 4 - 10, height / 4 - 10, mPaint);
            canvas.restore();//恢复画图状态到保存前
        }
        mPaint.setColor(Color.parseColor("#5990c9"));//
        canvas.drawCircle(width / 2, height / 2, width / 4, mPaint);
        mPaint.setColor(Color.parseColor("#f4f4f4"));//
        mPaint.setTextSize(30 * width / 216);
        if (progress < 10) {
            canvas.drawText(progress + "%", width / 2 - TextUtil.getWidthSize(30), height / 2+TextUtil.getHightSize(30), mPaint);
        } else if (progress < 100) {
            canvas.drawText(progress + "%", width / 2 - TextUtil.getWidthSize(60), height / 2+TextUtil.getHightSize(30), mPaint);
        } else {
            canvas.drawText(progress + "%", width / 2 - TextUtil.getWidthSize(90), height / 2 +TextUtil.getHightSize(30), mPaint);
        }
        mPaint.setTextSize(20 * width / 216);

    }

    public void setProgress(int progress) {
        this.progress = progress;
        //todo 没做Progress差很多时候的ui
        postInvalidate();
    }
}
