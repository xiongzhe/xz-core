package com.xz.android.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * App fragment
 * Created by xiongz on 2017/12/9.
 */
public abstract class XzActivity extends PermissionCheckerActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
