package com.example.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aop.PointTrace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        pointTest(100, "HaHaHa");
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }

    @PointTrace(event = "MainActivity")
    private void pointTest(long id, String name){

    }

    public void test2(View view) {
        startActivity(new Intent(this, DurationTestActivity.class));
    }
}