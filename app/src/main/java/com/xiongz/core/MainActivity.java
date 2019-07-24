package com.xiongz.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xz.android.core.activities.XzActivity;

public class MainActivity extends XzActivity {

    private TextView textView;

    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mIsFullScreen = true;
        super.onCreate(savedInstanceState);

        textView = findViewById(R.id.tv_detail);
        textView.setText("我不做大哥好多年");
    }
}
