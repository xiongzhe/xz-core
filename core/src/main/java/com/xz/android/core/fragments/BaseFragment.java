package com.xz.android.core.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xz.android.core.net.rx.RxApiManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * Created by xiongz on 2017/10/12
 */
public abstract class BaseFragment extends Fragment {

    //Activity
    protected FragmentActivity _mActivity = null;
    //ButterKnife绑定
    private Unbinder mUnbinder = null;
    //是否注册EventBus
    protected boolean mIsRegisterBus;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _mActivity = getActivity();
        //EventBus注册
        if (mIsRegisterBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }

        //绑定
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);

        return rootView;
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public Activity getProxyActivity() {
        return _mActivity;
    }

    @Override
    public void onDestroyView() {
        //销毁EventBus
        if (mIsRegisterBus) {
            EventBus.getDefault().unregister(this);
        }
        //解绑
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        //清除当前页面的网络请求
        RxApiManager rxApiManager = RxApiManager.getInstance();
        if (rxApiManager != null) {
            rxApiManager.cancel(getContext());
        }

        super.onDestroyView();
    }
}
