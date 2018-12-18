package com.liuzhi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils
{
    public static final String TIME_FORMAT_YMDHMS   = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_FORMAT_YMD      = "yyyy-MM-dd";

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
        cal.set(Calendar.HOUR_OF_DAY, hour==null?0:hour);//设置时
        cal.set(Calendar.MINUTE, minute==null?0:minute);//设置分
        cal.set(Calendar.SECOND, second==null?0:second);//设置秒
        cal.set(Calendar.MILLISECOND,0);//设置毫秒
        return cal.getTime();
    }
    /**
     * 获取指定的时间
     * @return 返回指定的时间
     */
    public static Date getAssignTime()
    {
        return getAssignTime(0);
    }
    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day)
    {
        return getAssignTime(day,0);
    }
    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Integer day,Integer hour)
    {
        return getAssignTime(day,hour,0);
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
        return getAssignTime(day,hour,minute,0);
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
}
