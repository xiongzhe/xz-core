package com.xiongz.core;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.xz.android.core.activities.XzActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends XzActivity {

    @BindView(R.id.tv_detail)
    TextView tvDetail;

    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // mIsFullScreen = true;
        super.onCreate(savedInstanceState);

        setTitle(this, "简单示例");
        tvDetail = findViewById(R.id.tv_detail);
        tvDetail.setText("对不起，我是警察!");
    }
}
