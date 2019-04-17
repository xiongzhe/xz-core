package com.xz.android.core.util.phone;

import android.content.Intent;
import android.content.IntentFilter;

import com.xz.android.core.app.Xz;

/**
 * 手机健康工具类
 *
 * @author xiongz
 * @date 2018/9/28
 */
public class HealthUtil {

    /**
     * 获取手机电量
     */
    public static int getDeviceBattery() {
        Intent batteryInfoIntent = Xz.getApplicationContext()
                .registerReceiver(null,
                        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = batteryInfoIntent.getIntExtra("level", 0);//电量（0-100）
//        int status = batteryInfoIntent.getIntExtra("status", 0);
//        int health = batteryInfoIntent.getIntExtra("health", 1);
//        boolean present = batteryInfoIntent.getBooleanExtra("present", false);
//        int scale = batteryInfoIntent.getIntExtra("scale", 0);
//        int plugged = batteryInfoIntent.getIntExtra("plugged", 0);//
//        int voltage = batteryInfoIntent.getIntExtra("voltage", 0);//电压
//        int temperature = batteryInfoIntent.getIntExtra("temperature", 0); // 温度的单位是10℃
//        String technology = batteryInfoIntent.getStringExtra("technology");
        return level;
    }
}
