package com.unistrong.demo;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.FingerGestureHelp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Spinner sp;
    private TextView tvMsg;
    private View ivFinger;

    @Override
    protected void initMvp() {
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.sp_test);
        tvMsg = findViewById(R.id.tv_msg);
        ivFinger = findViewById(R.id.iv_finger);
        tvMsg.setText("test_JNI_method");
        tvMsg.setOnClickListener(v -> {
            click();
        });
        ivFinger.setOnClickListener(v -> {
            FingerGestureHelp.getInstance().initFinger(this, new FingerGestureHelp.OnGestureResult() {
                @Override
                public void success() {
                    Log.d(TAG, "success() called");
                }

                @Override
                public void failure() {
                    Log.d(TAG, "failure() called");
                }

                @Override
                public void error() {
                    Log.d(TAG, "error() called");
                }
            });
        });
    }

    private void click() {
        tvMsg.setText(Sample1.stringMethod());
        //      tvMsg.setText(String.valueOf(Sample1.intMethod()));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void test() {
        sp.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, buildArrays()));
    }

    @NonNull
    private String[] buildArrays() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("index:" + i);
        }
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;
    }
}
