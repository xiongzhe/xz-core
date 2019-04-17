package com.xz.android.core.util.string;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.xz.android.core.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 字符串工具类
 * Created by xiongz on 2017/4/19.
 */
public class StringUtil {

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }


    /**
     * 将double精确到小数点后一位
     *
     * @param value
     * @return
     */
    public static String formatDouble1(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * 将double精确到小数点后两位
     *
     * @param value
     * @return
     */
    public static String formatDouble2(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * html转化成文本
     *
     * @param context
     * @param htmlInput
     * @return
     */
    public static CharSequence getEmolHtml(final Context context, String htmlInput) {
        CharSequence charSequence = Html.fromHtml(htmlInput, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                // 获得系统资源的信息，比如图片信息
                Drawable drawable = context.getResources().getDrawable(getResourceId(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        }, null);
        return charSequence;
    }

    /**
     * 通过反射机制获取资源id
     *
     * @param name
     * @return
     */
    public static int getResourceId(String name) {
        try {
            // 根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            Field field = R.drawable.class.getField(name);
            // 取得并返回资源的id的字段(静态变量)的值，使用反射机制
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 隐藏手机号中间四位
     *
     * @param mobile
     */
    public static String hidenPhone(String mobile) {
        char[] chars = mobile.toCharArray();
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            index++;
            if (index > 4 && index < 9) {
                chars[i] = '*';
            }
        }
        return new String(chars);
    }

    /**
     * 从assets读取json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJsonFromAsset(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = null;
        BufferedReader bufferedReader = null;
        try {
            is = context.getAssets().open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        if (str.length() < 8) return false;
        boolean isDigit = false; // 定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false; // 定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }
}
