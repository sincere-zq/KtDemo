package com.witation.ktdemo.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */

public class DateUtils {
    /**
     * 年月日时分秒
     */
    public static final String YEAR_MONTH_DAY_HOUR_MIN_SEC = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_HOUR_MIN_SEC_ = "yyyy/MM/dd HH:mm:ss";
    public static final String MONTH_DAY_HOUR_MIN = "MM-dd HH:mm";
    /**
     * 时分秒
     */
    public static final String HOUR_MIN_SEC = "HH:mm:ss";
    /**
     * 时分
     */
    public static final String HOUR_MIN = "HH:mm";
    /**
     * 年月日时分
     */
    public static final String YEAR_MONTH_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
    public static final String YEAR_MONTH_DAY_HOUR_MIN_POINT = "yyyy.MM.dd  HH:mm";
    /**
     * 年月日
     */
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    /**
     * 年月日 点
     */
    public static final String YEAR_MONTH_DAY_POINT = "yyyy.MM.dd";
    /**
     * 年月日 星期
     */
    public static final String YEAR_MONTH_DAY_WEEK = "yyyy-MM-dd  EEEE";

    /**
     * 时分
     */
    public static final String HOURR_MIN = "H:mm";

    /**
     * 月日
     */
    public static final String MONTH_DAY = "M.dd";

    /**
     * 获取年月时分
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 获取年月日
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取传入日期的前一天的日期
     *
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取传入日期的后一天数据
     *
     * @param date
     * @return
     */
    public static Date getAfterDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取前一天的日期
     *
     * @return
     */
    public static String getYesterdayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1);    //得到前一天
        return format.format(calendar.getTime());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getCurSystemDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(System.currentTimeMillis());
        return format.format(d1);
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getSystemTime() {
        return getSystemTime("yyyy-MM-dd HH:mm");
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getSystemTime(String formatTime) {
        SimpleDateFormat format = new SimpleDateFormat(formatTime);
        Date d1 = new Date(System.currentTimeMillis());
        return format.format(d1);
    }

    /**
     * 获取当前系统日期及星期
     *
     * @return
     */
    public static String getCurSystemDateWeek() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  EEEE");
        Date d1 = new Date(System.currentTimeMillis());
        return format.format(d1);
    }

    /**
     * 获取年月日星期
     *
     * @param date
     * @return
     */
    public static String getDateWeek(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  EEEE");
        return format.format(date);
    }


    /**
     * 根据时间获取星期
     *
     * @param pTime
     * @return
     */
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 从年月日时分秒类型的时间中，获取时分
     *
     * @param date
     * @return
     */
    public static String getDateTime(String date) {
        SimpleDateFormat _oldformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date _date = null;
        try {
            _date = _oldformat.parse(date);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat _newformat = new SimpleDateFormat("H:mm");
        return _newformat.format(_date);
    }

    /**
     * 从年月日时分秒类型的时间中，获取时分
     *
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 获取新的时间格式
     *
     * @param date
     * @return
     */
    public static String getNewformatByOldformat(String date, String oldformat, String newformat) {
        SimpleDateFormat _oldformat = new SimpleDateFormat(oldformat);
        Date _date = null;
        try {
            _date = _oldformat.parse(date);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat _newformat = new SimpleDateFormat(newformat);
        return _newformat.format(_date);
    }

    /**
     * 从年月日类型的时间中，月日
     *
     * @param date
     * @return
     */
    public static String getMonthDay(String date) {
        SimpleDateFormat _oldformat = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = null;
        try {
            _date = _oldformat.parse(date);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat _newformat = new SimpleDateFormat("M.dd");
        return _newformat.format(_date);
    }

    /**
     * 通过时分秒获取时分
     *
     * @param date
     * @return
     */
    public static String getDateTimeByTime(String date) {
        SimpleDateFormat _oldformat = new SimpleDateFormat("HH:mm:ss");
        Date _date = null;
        try {
            _date = _oldformat.parse(date);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat _newformat = new SimpleDateFormat("H:mm");//一个H  那么09:00  会返回9:00
        return _newformat.format(_date);
    }

    /**
     * 通过日期星期得到日期
     *
     * @param date
     * @return
     */
    public static String getDateByEEEE(String date) {
        SimpleDateFormat _oldformat = new SimpleDateFormat("yyyy-MM-dd  EEEE");
        Date _date = null;
        try {
            _date = _oldformat.parse(date);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat _newformat = new SimpleDateFormat("yyyy-MM-dd");
        return _newformat.format(_date);
    }

    /**
     * 比较两个时间，结束时间大于开始时间为TRUE
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean timeCompare(String startTime, String endTime) {
        //格式化时间
        return timeCompare("yyyy-MM-dd HH:mm", startTime, endTime);
    }

    /**
     * 比较两个时间，结束时间大于开始时间为TRUE
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean timeCompare(String pattern, String startTime, String endTime) {
        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat(pattern);
        try {
            Date startDate = CurrentTime.parse(startTime);
            Date endDate = CurrentTime.parse(endTime);
            if (endDate.getTime() - startDate.getTime() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个日期，结束日期大于等于开始日期为TRUE   30天内
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean dateCompare(String startTime, String endTime) {
        //格式化时间
        try {
            SimpleDateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = currentTime.parse(startTime);
            Date endDate = currentTime.parse(endTime);
            long dateDistance = endDate.getTime() - startDate.getTime();
            long dayDistance = dateDistance / (1000 * 60 * 60 * 24);
            if (dayDistance >= 0 && dayDistance <= 30) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个日期，结束日期大于等于开始日期为TRUE
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean dateCompareYMD(String startTime, String endTime) {
        //格式化时间
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = currentTime.parse(startTime);
            Date endDate = currentTime.parse(endTime);
            long dateDistance = endDate.getTime() - startDate.getTime();
            long dayDistance = dateDistance / (1000 * 60 * 60 * 24);
            if (dayDistance >= 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个日期带上下午，结束日期大于等于开始日期为TRUE
     * 同一天 如果开始是上午，则结束可以上午 下午
     * 同一天 如果开始是下午，结束是下午
     */
    public static boolean DateCompareYMDAmPm(String startYMD, boolean startIsAm, String endYMD, boolean endIsAm) {
        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = CurrentTime.parse(startYMD);
            Date endDate = CurrentTime.parse(endYMD);
            long dateDistance = endDate.getTime() - startDate.getTime();
            long dayDistance = dateDistance / (1000 * 60 * 60 * 24);
            if (dayDistance > 0) {
                //开始时间比结束时间大一天以上
                return true;
            } else if (dayDistance == 0) {
                //开始时间和结束时间同一天
                if (startIsAm) {
                    return true;
                } else {
                    return !endIsAm;
                }
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取年月时分
     *
     * @param date
     * @return
     */
    public static String getTime(Date date, String pattern) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String getDateByFormat(long date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 根据格式字符串时间获取Calender
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Calendar getCalender(String dateString, String pattern) {
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                Date parse = dateFormat.parse(dateString);
                calendar.setTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar;
    }

    /**
     * 根据Calendar获取年月日
     *
     * @param calendar
     * @return
     */
    public static String getYearMonthDayByCalender(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        return calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间间隔几天  2018-6-14  2018-6-15 返回2
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long dateDiff(String startTime, String endTime, String format) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            return 0;
        }
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day >= 1) {
                return day + 1;
            } else {
                if (day == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMonthFormat(int month) {
        if ((month + 1) < 10) {
            return "0" + (month + 1);
        }
        return "" + (month + 1);
    }
}
