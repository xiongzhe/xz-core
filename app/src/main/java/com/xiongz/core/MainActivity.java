package com.xiongz.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xz.android.core.activities.XzActivity;
import com.xz.android.core.net.rx.RxNetClient;
import com.xz.android.core.ui.loader.XzLoader;
import com.xz.android.core.util.json.FastjsonUtil;
import com.xz.android.core.util.log.XzLogger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    @OnClick({R.id.btn, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 按钮
            case R.id.btn:
                KeyboardUtils.hideSoftInput(this);
                ActivityUtils.startActivity(TestActivity.class);
                break;
            // 上传
            case R.id.btn_upload:
                break;
        }
    }
}
