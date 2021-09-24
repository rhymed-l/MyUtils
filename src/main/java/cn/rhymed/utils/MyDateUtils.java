package cn.rhymed.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * @author LiuZhi
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
    public static Date addAssignTime(Date date,Integer year,Integer month,Integer day,Integer hour,Integer minute,Integer second)
    {
        Calendar cal=Calendar.getInstance();
        if(date==null){
            date = new Date();
        }
        cal.setTime(date);
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
        return addAssignTime(null,year,month,day,hour,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day,Integer hour)
    {
        return addAssignTime(null,year,month,day,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month,Integer day)
    {
        return addAssignTime(null,year,month,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Integer year,Integer month)
    {
        return addAssignTime(null,year,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeYear(Integer year)
    {
        return addAssignTime(null,year,null,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMonth(Integer month)
    {
        return addAssignTime(null,null,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeDay(Integer day)
    {
        return addAssignTime(null,null,null,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeHour(Integer hour)
    {
        return addAssignTime(null,null,null,null,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMinute(Integer minute)
    {
        return addAssignTime(null,null,null,null,null,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeSecond(Integer second)
    {
        return addAssignTime(null,null,null,null,null,null,second);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Date date,Integer year,Integer month,Integer day,Integer hour,Integer minute)
    {
        return addAssignTime(date,year,month,day,hour,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Date date,Integer year,Integer month,Integer day,Integer hour)
    {
        return addAssignTime(date,year,month,day,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Date date,Integer year,Integer month,Integer day)
    {
        return addAssignTime(date,year,month,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTime(Date date,Integer year,Integer month)
    {
        return addAssignTime(date,year,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeYear(Date date,Integer year)
    {
        return addAssignTime(date,year,null,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMonth(Date date,Integer month)
    {
        return addAssignTime(date,null,month,null,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeDay(Date date,Integer day)
    {
        return addAssignTime(date,null,null,day,null,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeHour(Date date,Integer hour)
    {
        return addAssignTime(date,null,null,null,hour,null,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeMinute(Date date,Integer minute)
    {
        return addAssignTime(date,null,null,null,null,minute,null);
    }
    /**
     * 增加指定的时间(参数都可空)
     * @return 返回指定的时间
     */
    public static Date addAssignTimeSecond(Date date,Integer second)
    {
        return addAssignTime(date,null,null,null,null,null,second);
    }

    /**
     * 获取指定的时间(参数都可空)
     * @param day 距离今天相差多少
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 返回指定的时间
     */
    public static Date getAssignTime(Date date,Integer day,Integer hour,Integer minute,Integer second)
    {
        Calendar cal=Calendar.getInstance();
        if(date == null)
        {
            date = new Date();
        }
        cal.setTime(date);
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
        return getAssignTime(null,day,hour,minute,null);
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
     * 返回今天零点的String格式的时间
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
            str = MyDateFormatterEnum.TIME_FORMAT_YMD_G_HMS.getFormatter();
        }else
        {
            str = MyDateFormatterEnum.TIME_FORMAT_YMD_G.getFormatter();
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
        SimpleDateFormat df = new SimpleDateFormat(MyDateFormatterEnum.TIME_FORMAT_TIMESTMP.getFormatter());//设置日期格式
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
        long now = System.currentTimeMillis();
        return new Date(dl1+dl2-now);
    }

    /**
     * String 转 时间格式
     * @param str 被转换的时间
     * @myDateFormatterEnum 需要用什么格式去转换
     * @return 转换后的时间
     */
    public static Date strToDate(String str,MyDateFormatterEnum myDateFormatterEnum)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myDateFormatterEnum.getFormatter());
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("被转化的日期不正确");
        }
    }
    /**
     * String 转 时间格式
     * @param str 被转换的时间
     * @return 转换后的时间
     */
    public static Date strToDate(String str)
    {
        if(str==null)
        {
            throw new RuntimeException("需要转换的字符串不能为null");
        }
        try {
            String format =null;
            //2020-06-09T09:00:00.000+0800
            if(str.contains("T") && str.contains(".") && !str.contains("Z") && str.length() > 24)
            {
               String localStr = MyStringUtils.getTextLeft(str,".").replaceAll("T"," ");
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MyDateFormatterEnum.TIME_FORMAT_YMD_G_HMS.getFormatter());
               Date date = simpleDateFormat.parse(localStr);
               return MyDateUtils.addAssignTimeHour(date,-8);
            }
            //'2018-08-06T10:00:00.000Z
            else if(str.contains("T") && str.contains(".") && str.contains("Z") && str.length() == 24)
            {
                str = MyStringUtils.getTextLeft(str,".").replaceAll("T"," ");
            }

            else if(str.contains("-") && !str.contains(":") && str.length() == 10)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD_G.getFormatter();
            }else if(str.contains("-") && str.contains(":"))
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD_G_HMS.getFormatter();
            }else if(str.contains("/") && !str.contains(":") && str.length()==10)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD_G.getFormatter();
            }else if(str.contains("/") && str.contains(":"))
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD_H_HMS.getFormatter();
            }else if(str.contains("/") && str.length()==10)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD_H.getFormatter();
            }else if(str.contains("年") && str.contains("月")&& str.contains("日") && str.contains(":"))
            {
                format = MyDateFormatterEnum.TIME_FORMAT_CHINASTMP.getFormatter();
            }else if(str.contains("年") && str.contains("月")&& str.contains("日") && str.length() == 10)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_CHINA.getFormatter();
            }else if(str.length()==14)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_TIMESTMP.getFormatter();
            }else if(str.length()==8)
            {
                format = MyDateFormatterEnum.TIME_FORMAT_YMD.getFormatter();
            }
            if(format==null)
            {
               throw new RuntimeException("被转化的日期不正确");
            }
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
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
    public static String dateToStr(Date date, MyDateFormatterEnum dateFormatter)
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
        return dateToStr(date,MyDateFormatterEnum.TIME_FORMAT_YMD_G_HMS);
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
        return getAssignYeay(new Date());
    }

    /**
     * 获取当前月份
     * @return 返回当前月份
     */
    public static String getCurrentMonth()
    {
       return getAssignMonth(new Date());
    }
    /**
     * 获取当前天数
     * @return 返回当前天数
     */
    public static String getCurrentDay()
    {
        return getAssignDay(new Date());
    }

    /**
     * 获取指定日期的天数
     * @return 返回指定日期的天数
     */
    public static String getAssignDay(Date date)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定日期的月份
     * @return 返回指定日期的月份
     */
    public static String getAssignMonth(Date date)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定日期的年份
     * @return 返回指定日期的年份
     */
    public static String getAssignYeay(Date date)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(date);
    }

    /**
     * 比较时间1是否大于时间2
     * @param date1 时间1
     * @param date2 时间2
     * @return 如果大于则返回真,否则假
     */
    public static boolean timeCompare(Date date1,Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); //创建时间转换对象：时 分 秒
        try {
             date1 = df.parse(MyDateUtils.dateToStr(date1,MyDateFormatterEnum.TIME_FORMAT_TIME)); //转换为 date 类型 Debug：Thu Jan 01 11:11:11 CST 1970
             date2 = df.parse(MyDateUtils.dateToStr(date2,MyDateFormatterEnum.TIME_FORMAT_TIME)); // 		 Debug：Thu Jan 01 12:12:12 CST 1970
             return date1.getTime() > date2.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        throw new RuntimeException("时间转换失败!");
    }

    /**
     * @author LiuZhi
     *
     * 时间格式化枚举
     *     TIME_FORMAT_YMD_G_HMS  ("yyyy-MM-dd HH:mm:ss"    )  ,
     *     TIME_FORMAT_YMD_G      ("yyyy-MM-dd"             )  ,
     *     TIME_FORMAT_YMD_H      ("yyyy/MM/dd"             )  ,
     *     TIME_FORMAT_YMD_H_HMS  ("yyyy/MM/dd HH:mm:ss"    )  ,
     *     TIME_FORMAT_TIMESTMP   ("yyyyMMddHHmmss"         )  ,
     *     TIME_FORMAT_TIME       ("yyyyMMdd"               )  ,
     *     TIME_FORMAT_CHINA      ("yyyy年MM月dd日"            ),
     *     TIME_FORMAT_CHINASTMP  ("yyyy年MM月dd日 HH:mm:ss"   );
     */
    public enum MyDateFormatterEnum
    {
        TIME_FORMAT_YMD        ("yyyyMMdd"                  ),
        TIME_FORMAT_MD         ("MMdd"                      ),
        TIME_FORMAT_YMD_G_HMS  ("yyyy-MM-dd HH:mm:ss"       ),
        TIME_FORMAT_YMD_G      ("yyyy-MM-dd"                ),
        TIME_FORMAT_MD_G       ("MM-dd"                     ),
        TIME_FORMAT_YMD_H      ("yyyy/MM/dd"                ),
        TIME_FORMAT_MD_H       ("MM/dd"                     ),
        TIME_FORMAT_YMD_H_HMS  ("yyyy/MM/dd HH:mm:ss"       ),
        TIME_FORMAT_TIMESTMP   ("yyyyMMddHHmmss"            ),
        TIME_FORMAT_CHINA      ("yyyy年MM月dd日"             ),
        TIME_FORMAT_CHINASTMP  ("yyyy年MM月dd日 HH:mm:ss"    ),
        TIME_FORMAT_TIME       ("HH:mm:ss"                  );

        private final String formatter;

        MyDateFormatterEnum(String formatter)
        {
            this.formatter = formatter;
        }

        public String getFormatter()
        {
            return formatter;
        }
//        public static MyDateFormatterEnum getTimeByString(String formatter)
//        {
//            return MyDateFormatterEnum.TIME_FORMAT_CHINA.formatter = formatter;
//        }
    }
}