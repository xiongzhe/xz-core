package com.xz.android.core.util.animation;

import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 动画工具类
 *
 * @author xiongz
 * @date 2018/12/7
 */
public class AnimationUtil {

    /**
     * 晃动动画 左右晃动
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeHorizontalAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * 晃动动画 上下晃动
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeVerticalAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 0, 0, -10);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * 晃动动画 左右晃动
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(-5, 5, -5, 5);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(600);
        return translateAnimation;
    }
}
