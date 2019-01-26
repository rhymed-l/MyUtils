package com.liuzhi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils
{
    public static final String TIME_FORMAT_YMDHMS   = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_FORMAT_YMD      = "yyyy-MM-dd";
    public static final String TIME_FORMAT_TIMESTMP = "yyyyMMddhhmmss";

    /**
     * 获取昨日时间
     * @return 昨天的时间
     */
    public static Date getYesterdayTime()
    {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return cal.getTime();
    }

    /**
     * 获取指定的时间加上某天的时间0点0分的时间
     * @return 指定的时间
     */
    public static Date getAssignZeroTime(Date date,Integer day)
    {
        Calendar cal=Calendar.getInstance();
        if(date==null){
            date = new Date();
        }
        cal.setTime(date);
        cal.add(Calendar.DATE,day==null?0:day);
        cal.set(Calendar.HOUR_OF_DAY, 0);//设置时
        cal.set(Calendar.MINUTE,0);//设置分
        cal.set(Calendar.SECOND,0);//设置秒
        cal.set(Calendar.MILLISECOND,0);//设置毫秒
        return cal.getTime();
    }
    /**
     * 获取指定的0点0分的时间
     * @return 指定的时间
     */
    public static Date getAssignZeroTime(Date date)
    {
        return getAssignZeroTime(date,null);
    }

    /**
     * 获取今日0点0分的时间
     * @return 昨天的时间
     */
    public static Date getTodaysZeroTime()
    {
        return getAssignTime();
    }



    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day,Integer hour,Integer minute,Integer second)
    {
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,day==null?0:day);
        cal.set(Calendar.HOUR_OF_DAY,hour==null?cal.get(Calendar.HOUR_OF_DAY):hour);//设置时
        cal.set(Calendar.MINUTE, minute==null?cal.get(Calendar.MINUTE):minute);//设置分
        cal.set(Calendar.SECOND, second==null?cal.get(Calendar.SECOND):second);//设置秒
        cal.set(Calendar.MILLISECOND,0);//设置毫秒
        return cal.getTime();
    }
    /**
     * 获取指定的时间
     * @return 返回指定的时间
     */
    public static Date getAssignTime()
    {
        return getAssignTime(null);
    }
    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day)
    {
        return getAssignTime(day,null);
    }
    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day,Integer hour)
    {
        return getAssignTime(day,hour,null);
    }
    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day,Integer hour,Integer minute)
    {
        return getAssignTime(day,hour,minute,null);
    }


    /**
     * 返回今天的String格式的时间
     * @return 返回String格式的时间
     */
    public static String getTodayZeroTimeToStr()
    {
        return getTodayZeroTimeToStr(null);
    }

    /**
     * 返回今天的String格式的时间
     * @param all 是否显示时分秒
     * @return 返回String格式的时间
     */
    public static String getTodayZeroTimeToStr(Boolean all)
    {
        return getAssignTimeToStr(getTodaysZeroTime(),all);
    }

    /**
     * 返回指定的String格式的时间
     * @param date 需要转换的时间
     * @param all 是否显示时分秒
     * @return 返回String格式的时间
     */
    public static String getAssignTimeToStr(Date date,Boolean all)
    {
        if(date == null)
        {
            date = new Date();
        }
        String str ;
        if(all == null)
        {
            all = true;
        }
        if(all)
        {
            str = TIME_FORMAT_YMDHMS;
        }else
        {
            str = TIME_FORMAT_YMD;
        }
        date = getTodaysZeroTime();
        SimpleDateFormat df = new SimpleDateFormat(str);//设置日期格式
        return df.format(date);
    }

    /**
     * 获取当前时间的时间戳
     * @return 返回当前时间时间戳
     */
    public static String getTimestamp()
    {
        return getTimestamp(null);
    }

    /**
     * 获取指定时间的时间戳
     * @param date 需要转换的时间戳
     * @return 返回指定时间的时间戳
     */
    public static String getTimestamp(Date date)
    {
        if(date ==null)
        {
            date = new Date();
        }
        SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT_TIMESTMP);//设置日期格式
        return df.format(date);
    }
    /**
     * 计算两个日期之间相差多少天(取绝对值)
     * @param date1 日期1
     * @param date2 日期2
     * @return 两个时间相差的天数
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return Math.abs(days);
    }
    /**
     * 计算两个日期之间相差多少天,不足一天按一天算(取绝对值)
     * @param date1 日期1
     * @param date2 日期2
     * @return 两个时间相差的天数
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)
        {//不同年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return Math.abs(timeDistance + (day2-day1));
        }
        else
        {//同一年
            return Math.abs(day2-day1);
        }
    }
}
