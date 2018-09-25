package com.unistrong.demo;

import android.widget.TextView;

import com.unistrong.baselibs.style.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected void initMvp() {
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        tv.setClickable(true);
//
    }

}
