package com.xz.android.core.util.timer;

import java.util.TimerTask;

/**
 * 倒计时监听器
 * Created by xiongz on 2017/12/22
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mListener;

    public BaseTimerTask(ITimerListener listener) {
        this.mListener = listener;
    }

    @Override
    public void run() {
        if (mListener != null) {
            mListener.onTimer();
        }
    }
}
