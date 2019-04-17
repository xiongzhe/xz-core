package com.xz.android.core.util.phone;

import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.util.Utils;

/**
 * 电话工具类
 *
 * @author xiongz
 * @date 2018/9/19
 */
public class PhoneUtil {

    /**
     * 打电话
     *
     * @param phoneNumber
     */
    public static void call(final String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 发短信
     *
     * @param phoneNumber
     * @param content
     */
    public static void sendSms(final String phoneNumber, final String content) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
