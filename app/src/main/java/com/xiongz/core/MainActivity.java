package com.xiongz.core;

import android.os.Bundle;

import com.xz.android.core.activities.XzActivity;

public class MainActivity extends XzActivity {

    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(this, "标题");
    }
}
