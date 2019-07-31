package com.xiongz.core;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.xz.android.core.activities.XzActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends XzActivity {

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
        tvDetail.setText("我不做大哥好多年");

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        ActivityUtils.startActivity(TestActivity.class);
    }
}
