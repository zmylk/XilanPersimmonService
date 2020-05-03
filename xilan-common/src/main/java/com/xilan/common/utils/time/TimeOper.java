package com.xilan.common.utils.time;

import com.sun.org.omg.SendingContext.CodeBaseOperations;
import com.xilan.common.utils.arithmetic.BasicOperations;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/24 21:52
 */
public class TimeOper {
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_MIN = "yyyy-MM-dd HH:mm";
    /**
     * 英文全称  如：2010-12-01-23
     */
    public static String FORMAT_HOUR = "yyyy-MM-dd-HH";
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    public static void main(String[] args) throws ParseException {
//        System.out.println(daysBetween(new Date(),new Date()));
//        System.out.println(getTimeString(new Date()));
//        System.out.println(getTimeDate(getTimeString()));
//        System.out.println("返回日期年份:"+getYear(new Date()));
//        System.out.println("返回月份："+getMonth(new Date()));
//        System.out.println("返回当天日份"+getDay(new Date()));
//        System.out.println("返回当天小时"+getHour(new Date()));
//        System.out.println("返回当天分"+getMinute(new Date()));
//        System.out.println("返回当天秒"+getSecond(new Date()));
//        System.out.println("返回当天毫秒"+getMillis(new Date()));

//        String date1 = "2020-04-03 14:35";
//        String date2 = "2020-04-03 16:20";
//        Date timeDate = getTimeDate(date1, TimeOper.FORMAT_MIN);
//        Date timeDate1 = getTimeDate(date2, TimeOper.FORMAT_MIN);
//        System.out.println(timeDate +"====="+ timeDate1);
//        Map<Integer, Double> needTime = getNeedTime(timeDate, timeDate1);
//        for (Map.Entry<Integer, Double> entry : needTime.entrySet()) {
//            System.out.println(entry.getKey()+":::"+entry.getValue());
//        }

        Double tenTime = getTenTime(new Date());
        System.out.println(tenTime);


    }

    /**
     * 获取十进制时间
     */
    public static Double getTenTime(Date date){
        double hour = (double) getHour(date);
        long timeOnHour = getTimeOnHour(date);
        long tmpTime = date.getTime() - timeOnHour;
        long oneHour = 3600000L;
        String txfloat = BasicOperations.txfloat(tmpTime, oneHour);
        Double aDouble = Double.valueOf(txfloat);
        return aDouble+hour;
    }


    /**
     * 获取时间区间学习情况
     */
    public static Map<Integer,Double> getNeedTime(Date preDate, Date lastDate){
        //String starTime = getTimeString(preDate, TimeOper.FORMAT_HOUR);

        int prehour = getHour(preDate);
        int lasthour = getHour(lastDate);
        long oneHour = 3600000L;
        Map<Integer,Double> map = new HashMap<>();
        for (int i=prehour;i<=lasthour;i++){
            if (i==prehour){
                long start = getTimeOnHour(preDate) + oneHour -  preDate.getTime() ;
                String txfloat = BasicOperations.txfloat(start, oneHour);
                Double aDouble = Double.valueOf(txfloat);
                map.put(i,aDouble);
            }else if (i==lasthour){
                long end = lastDate.getTime() - getTimeOnHour(lastDate) ;
                String txfloat = BasicOperations.txfloat(end, oneHour);
                Double aDouble = Double.valueOf(txfloat);
                map.put(i,aDouble);
            }else {
                map.put(i,1.0);
            }
        }
        return map;
    }

    /**
     * 得到精确到分钟的毫秒数
     * 2020-04-02 15
     */

    public static long getTimeOnHour(Date date){
        String timeString = getTimeString(date);
        String[] split = timeString.split("[- :]");
        String new_time = split[0] +"-" + split[1] +"-"+ split[2] + "-" + split[3];
        return getTimeDate(new_time,TimeOper.FORMAT_HOUR).getTime();
    }

    /**
     * 获取当前时间字符形式
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    public static String getTimeString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        return df.format(date);
    }
    public static String getTimeString(Date date,String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 获取当前时间Date格式
     */

    public static Date getTimeDate(String date)  {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Date parse = null;
        try {
            parse = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取当前时间Date格式
     */

    public static Date getTimeDate(String date ,String format)  {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取日期年份
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 功能描述：返回月
     *
     * @param date
     *            Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日期
     *
     * @param date
     *            Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
    /**
     * 计算两个日期之间的天数
     */
    public static int daysBetween(Date preTime, Date lastTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(preTime);
        long time1 = cal.getTimeInMillis();
        cal.setTime(lastTime);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int hourBetween(Date preTime, Date lastTime) {
        long time2 = preTime.getTime();
        long time1 = lastTime.getTime();
        long between_days=(time2-time1)/(1000*60);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
