package com.xilan.begin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author like
 * @version 1.0
 * @date 2020/4/24 20:48
 */
public class GetWeekNum {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
// 当前内日期是本月第几周
        int weeks = c.get(Calendar.WEEK_OF_MONTH);
        System.out.println(weeks);
// 当前是星期几 java中一周第一天为星期天，所以1代表星容期日，2代表星期一，以此类推，7代表星期6
        int week = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(week);
    }
    public static void Current_week(long startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);//可以不用设置
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        calendar.setTimeInMillis(startTime);//时间戳
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
        System.out.println("第几年："+weekYear);
        System.out.println("第几周："+weekOfYear);
        calendar.setWeekDate(weekYear, weekOfYear, 2);//获得指定年的第几周的开始日期
        long starttime = calendar.getTime().getTime();//创建日期的时间该周的第一天，
        calendar.setWeekDate(weekYear, weekOfYear, 1);//获得指定年的第几周的结束日期
        long endtime = calendar.getTime().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStart = simpleDateFormat.format(starttime);//将时间戳格式化为指定格式
        String dateEnd = simpleDateFormat.format(endtime);
        System.out.println(dateStart);
        System.out.println(dateEnd);
    }

}
