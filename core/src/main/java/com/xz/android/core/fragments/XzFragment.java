package com.xz.android.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * App fragment
 * Created by xiongz on 2017/12/9.
 */
public abstract class XzFragment extends PermissionCheckerFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
