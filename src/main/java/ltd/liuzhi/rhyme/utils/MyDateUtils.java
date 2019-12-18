package ltd.liuzhi.rhyme.utils;

import ltd.liuzhi.rhyme.utils.type.DateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LiuZhi
 * 时间日期工具类
 */
public class MyDateUtils
{
    private MyDateUtils() {
    }

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
     * 获取指定的时间加上多少天的时间
     * @return 指定的时间
     */
    public static Date addAssignTime(Date date,Integer day)
    {
        Calendar cal=Calendar.getInstance();
        if(date==null){
            date = new Date();
        }
        cal.setTime(date);
        cal.add(Calendar.DATE,day==null?0:day);
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
     * 获取指定的0点0分的时间
     * @return 指定的时间
     */
    public static Date getAssignZeroTime(Integer integer)
    {
        Date date = getAssignTime(integer);
        return getAssignZeroTime(date,null);
    }

    /**
     * 获取指定的天数的结束时间
     * @return 指定的时间
     */
    public static Date getAssignEndTime(Integer integer)
    {
        Date date = getAssignTime(integer);
        return getAssignEndTime(date,null);
    }
    /**
     * 获取指定的天数的结束时间
     * @return 指定的时间
     */
    public static Date getAssignEndTime(Date date)
    {
        return getAssignEndTime(date,null);
    }

    /**
     * 获取今天的结束时间
     * @return 指定的时间
     */
    public static Date getTodayEndTime()
    {
        return getAssignEndTime(null,null);
    }

    private static Date getAssignEndTime(Date date,Integer day)
    {
        Calendar cal=Calendar.getInstance();
        if(date==null){
            date = new Date();
        }
        cal.setTime(date);
        cal.add(Calendar.DATE,day==null?0:day);
        cal.set(Calendar.HOUR_OF_DAY, 23);//设置时
        cal.set(Calendar.MINUTE,59);//设置分
        cal.set(Calendar.SECOND,59);//设置秒
        cal.set(Calendar.MILLISECOND,999);//设置毫秒
        return cal.getTime();
    }

    /**
     * 获取今日0点0分的时间
     * @return 指定的时间
     */
    public static Date getTodayZeroTime()
    {
        return getAssignZeroTime(new Date());
    }



    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day,Integer hour,Integer minute,Integer second)
    {
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR,year==null?0:year);
        cal.add(Calendar.MONTH,month==null?0:month);
        cal.add(Calendar.DATE,day==null?0:day);
        cal.add(Calendar.HOUR_OF_DAY,hour==null?0:hour);//设置时
        cal.add(Calendar.MINUTE, minute==null?0:minute);//设置分
        cal.add(Calendar.SECOND, second==null?0:second);//设置秒
        cal.set(Calendar.MILLISECOND,0);//设置毫秒
        return cal.getTime();
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day,Integer hour,Integer minute)
    {
        return addAssignTime(year,month,day,hour,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day,Integer hour)
    {
        return addAssignTime(year,month,day,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day)
    {
        return addAssignTime(year,month,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month)
    {
        return addAssignTime(year,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeYear(Integer year)
    {
        return addAssignTime(year,null,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMonth(Integer month)
    {
        return addAssignTime(null,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeDay(Integer day)
    {
        return addAssignTime(null,null,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeHour(Integer hour)
    {
        return addAssignTime(null,null,null,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMinute(Integer minute)
    {
        return addAssignTime(null,null,null,null,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeSecond(Integer second)
    {
        return addAssignTime(null,null,null,null,null,second);
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
        return getAssignTimeToStr(getTodayZeroTime(),all);
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
            str = DateFormatter.TIME_FORMAT_YMD_G_HMS.getFormatter();
        }else
        {
            str = DateFormatter.TIME_FORMAT_YMD_G.getFormatter();
        }
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
        SimpleDateFormat df = new SimpleDateFormat(DateFormatter.TIME_FORMAT_TIMESTMP.getFormatter());//设置日期格式
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
     * 计算两个日期之间相差多少秒(取绝对值)
     * @param date1 日期1
     * @param date2 日期2
     * @return 两个时间相差的天数
     */
    public static int differentSecondByMillisecond(Date date1,Date date2)
    {
        int second = (int) ((date2.getTime() - date1.getTime()) / (1000));
        return Math.abs(second);
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

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 计算两个时间相加后的时间
     * @param d1 时间1
     * @param d2 时间2
     * @return
     */
    public static Date dateAddDate(Date d1,Date d2)
    {
        long dl1 = d1.getTime();
        long dl2 = d2.getTime();
        long now = new Date().getTime();
        return new Date(dl1+dl2-now);
    }
    /**
     * String 转 时间格式
     * @param str 时间必须为 XXXX-XX-XX XX:XX:XX
     * @return 转换后的时间
     */
    public static Date strToDate(String str)
    {
        String format =null;
        if(str.contains("-") && str.contains(":"))
        {
            format = DateFormatter.TIME_FORMAT_YMD_G_HMS.getFormatter();
        }else if(str.contains("/") && str.length()==10)
        {
            format = DateFormatter.TIME_FORMAT_YMD_G.getFormatter();
        }else if(str.contains("/") && str.contains(":"))
        {
            format = DateFormatter.TIME_FORMAT_YMD_H_HMS.getFormatter();
        }else if(str.contains("/") && str.length()==10)
        {
            format = DateFormatter.TIME_FORMAT_YMD_H.getFormatter();
        }else if(str.contains("年") && str.contains("月")&& str.contains("日") && str.contains(":"))
        {
            format = DateFormatter.TIME_FORMAT_CHINASTMP.getFormatter();
        }else if(str.contains("年") && str.contains("月")&& str.contains("日") && str.length()==10)
        {
            format = DateFormatter.TIME_FORMAT_CHINA.getFormatter();
        }else if(str.length()==14)
        {
            format = DateFormatter.TIME_FORMAT_TIMESTMP.getFormatter();
        }else if(str.length()==8)
        {
            format = DateFormatter.TIME_FORMAT_TIME.getFormatter();
        }
        if(format==null)
        {
           throw new RuntimeException("被转化的日期不正确");
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("被转化的日期不正确");
        }
    }

    /**
     * 时间 转 String格式
     * @param dateFormatter 时间格式
     * @return 转换后的时间
     */
    public static String dateToStr(Date date, DateFormatter dateFormatter)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormatter.getFormatter());
        return simpleDateFormat.format(date);
    }
    /**
     * 时间 转 String格式
     * @return 转换后的时间 yyyy-MM-dd hh:mm:ss
     */
    public static String dateToStr(Date date)
    {
        return dateToStr(date,DateFormatter.TIME_FORMAT_YMD_G_HMS);
    }

    /**
     * 判断日期1的时间是否大于日期2的时间
     * @param date1 日期1
     * @param date2 日期2
     * @return 如果大于返回真,否则假
     */
    public static Boolean dateCompareGreater(Date date1,Date date2)
    {
        return date1.getTime()>date2.getTime();
    }

    /**
     * 获取当前年份
     * @return 返回当前年份
     */
    public static String getCurrentYeay()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前月份
     * @return 返回当前月份
     */
    public static String getCurrentMonth()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM");
        return simpleDateFormat.format(new Date());
    }
    /**
     * 获取当前天数
     * @return 返回当前天数
     */
    public static String getCurrentDay()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
        return simpleDateFormat.format(new Date());
    }
}