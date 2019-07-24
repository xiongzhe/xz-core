package com.xz.android.core.util.time;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * Created by xiongz on 2018/4/20.
 */
public class TimeUtil {

    /**
     * 计算2个日期之间的差值，返回值是秒数
     *
     * @param time1 formate yyy-MM-dd
     * @param time2 formate yyy-MM-dd
     * @return
     */
    public static long minusTime(String time1, String time2) {
        if (time1 != null && time2 != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = TimeUtils.string2Date(time1, format);
            Date date2 = TimeUtils.string2Date(time2, format);
            return minusDate(date1, date2);
        }
        return -1;
    }

    /**
     * 计算2个时间之间的差值，返回值是秒数
     *
     * @param date1
     * @param date2
     * @return seconds between time1 and time2
     */
    public static long minusDate(Date date1, Date date2) {
        long result = 0;
        if (date1 != null && date2 != null) {
            result = (date1.getTime() - date2.getTime()) / 1000;
        }
        return result;
    }

    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取时分秒
     *
     * @return
     */
    public static SimpleDateFormat getFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * 获取时分秒
     *
     * @return
     */
    public static SimpleDateFormat getFullFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    private static String getFormatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(time));
    }

    /**
     * 获取年月日
     *
     * @return
     */
    public static SimpleDateFormat getDayFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * 获取时分秒
     *
     * @return
     */
    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * 获取时分
     *
     * @return
     */
    public static SimpleDateFormat getHourMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * 获取时分
     *
     * @return
     */
    public static SimpleDateFormat getBirthFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        SDF_THREAD_LOCAL.set(simpleDateFormat);
        return simpleDateFormat;
    }

    /**
     * 获取年-月-日
     *
     * @param time formate yyyy-MM-dd
     * @return
     */
    public static String getMonthDay(String time) {
        if (time != null) {
            Date date = TimeUtils.string2Date(time, getFullFormat());
            if (date == null) {
                return "";
            } else {
                long timeLong = date.getTime();
                return TimeUtils.millis2String(timeLong, getDateFormat());
            }
        }
        return "";
    }

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /**
     * 获取时间差
     *
     * @param time1
     * @param time2
     * @return
     */
    public static String getTimes(long time1, long time2) {
        Date newDate = new Date(time2);
        Date oldDate = new Date(time1);
        long diff = newDate.getTime() - oldDate.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟";
        }
        return "0分钟";
    }

    /**
     * 返回文字描述的日期
     *
     * @param time
     * @return
     */
    public static String getTimeFormatText(long time) {
        Date date = new Date(time);
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            if (r == 1) {
                return "昨天";
            } else {
                return r + "天前";
            }
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 返回友好文字描述的日期
     *
     * @param timeStr
     * @return
     */
    public static String getTimeFormatText2(String timeStr) {
        long time = TimeUtils.string2Millis(timeStr, TimeUtil.getFullFormat());
        Date date = new Date(time);
        long diff = new Date().getTime() - date.getTime();
        long r;
        if (diff > year || diff > month) {
            return TimeUtils.millis2String(time, TimeUtil.getFullFormat());
        }
        if (diff > day) {
            r = (diff / day);
            if (r == 1) {
                return TimeUtils.getFriendlyTimeSpanByNow(timeStr);
            } else {
                return timeStr;
            }
        }
        if (diff > hour) {
            r = (diff / hour);
            return TimeUtils.getFriendlyTimeSpanByNow(timeStr);
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 返回文字描述的日期
     *
     * @param timeStr
     * @return
     */
    public static String getTimeFormatText3(String timeStr) {
        long time = TimeUtils.string2Millis(timeStr, TimeUtil.getFullFormat());
        Date date = new Date(time);
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            if (r == 1) {
                return "昨天";
            } else {
                return r + "天前";
            }
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }
}
