package com.xz.android.core.util.device;

import android.content.Context;
import android.os.Vibrator;

/**
 * 手机振动工具类
 *
 * @author xiongz
 * @date 2018/12/8
 */
public class VibratorUtil {

    /**
     * 手机振动器
     */
    public static void vibrate(Context context) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}
