package com.xiongz.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xz.android.core.activities.XzActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        textView = findViewById(R.id.tv_detail);
        textView.setText("我不做大哥好多年");
    }
}
