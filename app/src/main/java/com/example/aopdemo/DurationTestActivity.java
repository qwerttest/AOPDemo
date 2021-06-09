package com.example.aopdemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.aop.DurationTrace;
import com.example.aop.MonitorTrace;

/**
 * Des
 *
 * @author WangJian on 2021/6/9 18
 */
public class DurationTestActivity extends Activity {


    @MonitorTrace(event = "DurationTestActivity")
    @DurationTrace(event = "DurationTestActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DurationTrace(event = "DurationTestActivity")
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
