package com.hubaoqi.circularprogressbar.ui;


import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.hubaoqi.circularprogressbar.BaseActivity;
import com.hubaoqi.circularprogressbar.R;
import com.hubaoqi.circularprogressbar.widget.CircleProgressBar;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Context context;
    private Button button;
    private CircleProgressBar circleProgressBar;

    @Override
    public int getLayoutId() {
        context = MainActivity.this;
        return R.layout.activity_main;
    }

    @Override
    public void initHolder() {
        button = (Button) findViewById(R.id.btn_mainActivity);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.cpb_mainActivity);
    }

    @Override
    public void initLayoutParams() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void bindListener() {
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mainActivity:
                startProgress();
                break;
        }
    }

    private void startProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    for(int i=0;i<100;i++){
                        try {
                        final int finalI = i;
                            button.post(new Runnable() {
                          @Override
                          public void run() {
                              circleProgressBar.setProgress(finalI + 1);
                          }
                      });
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }).start();
    }
}
