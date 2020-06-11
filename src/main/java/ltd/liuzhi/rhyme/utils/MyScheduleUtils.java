package ltd.liuzhi.rhyme.utils;


import java.util.Map;
import java.util.concurrent.*;

/**
 * 定时器工具类
 *
 * @author LiuZhi
 * @Date 2020-05-11 18:11
 * @Version V1.0
 */
public class MyScheduleUtils
{
    private static Map<Integer,RunnableScheduledFuture> map;

    private static ScheduledThreadPoolExecutor scheduledExecutorService;

    private MyScheduleUtils() {}

    /**
     * 开启一个定时器
     * @param runnable 需要执行的方法
     * @param milliseconds 毫秒
     * @param isPeriodic 是否重复执行
     * @return 返回该任务的标识
     */
    public static Integer newTaskByMilliseconds(Runnable runnable,long milliseconds,boolean isPeriodic)
    {
        return openTask(runnable,milliseconds,TimeUnit.MILLISECONDS,isPeriodic);
    }

    /**
     * 开启一个定时器
     * @param runnable 需要执行的方法
     * @param seconds 秒
     * @param isPeriodic 是否重复执行
     * @return 返回该任务的标识
     */
    public static Integer newTaskBySeconds(Runnable runnable,int seconds,boolean isPeriodic)
    {
        return openTask(runnable,seconds,TimeUnit.SECONDS,isPeriodic);
    }

    /**
     * 开启一个定时器
     * @param runnable 需要执行的方法
     * @param time 时间
     * @param timeUnit 时间单位
     * @param isPeriodic 是否重复执行
     * @return 返回该任务的标识
     */
    public static Integer newTask(Runnable runnable,long time,TimeUnit timeUnit,boolean isPeriodic)
    {
        return openTask(runnable,time,timeUnit,isPeriodic);
    }

    /**
     * 开启一个定时器
     * @param runnable 需要执行的方法
     * @param minutes 分钟
     * @param isPeriodic 是否重复执行
     * @return 返回该任务的标识
     */
    public static Integer newTaskByMinutes(Runnable runnable,int minutes,boolean isPeriodic)
    {
        return openTask(runnable,minutes,TimeUnit.MINUTES,isPeriodic);
    }

    /**
     * 开启一个定时器
     * @param runnable 需要执行的方法
     * @param hours 小时
     * @param isPeriodic 是否重复执行
     * @return 返回该任务的标识
     */
    public static Integer newTaskByHours(Runnable runnable,int hours,boolean isPeriodic)
    {
        return openTask(runnable,hours,TimeUnit.HOURS,isPeriodic);
    }

    /**
     * 关闭一个定时任务
     * @param taskFlag 任务标识
     * @param isCloseNow 是否
     * @return 停止成功则为true,否则false
     */
    public static synchronized boolean shutdownTask(Integer taskFlag,boolean isCloseNow)
    {
        if(map == null)
        {
            throw new RuntimeException("找不到相关的任务标识");
        }
        RunnableScheduledFuture future = map.get(taskFlag);
        if(future == null)
        {
            throw new RuntimeException("找不到相关的任务标识");
        }
        boolean result = future.cancel(isCloseNow);
        if(result)
        {
            map.remove(future);
            if(map.size() == 0)
            {
                map = null;
                scheduledExecutorService.shutdown();
                scheduledExecutorService = null;
            }
        }
        return result;
    }

    /**
     * 获取任务的Future
     * @param taskFlag 任务标识
     * @return 返回任务的Future
     */
    public static RunnableScheduledFuture getTaskFuture(Integer taskFlag)
    {
        if(map == null){
            throw new RuntimeException("找不到相关的任务标识");
        }
        RunnableScheduledFuture future = map.get(taskFlag);
        if(future == null)
        {
            throw new RuntimeException("找不到相关的任务标识");
        }
        return future;
    }

    /**
     * 关闭一个定时任务
     * @param taskFlag 任务标识
     * @return 停止成功则为true,否则false
     */
    public static boolean shutdownTask(Integer taskFlag)
    {
        return shutdownTask(taskFlag,false);
    }

    private static synchronized Integer openTask(Runnable runnable,long time,TimeUnit timeUnit,boolean isPeriodic)
    {
        if(scheduledExecutorService == null)
        {
            scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        }
        RunnableScheduledFuture future;
        if(isPeriodic)
        {
            future =  (RunnableScheduledFuture) scheduledExecutorService.scheduleAtFixedRate(runnable,time,time,timeUnit);
        }else
        {
            future =  (RunnableScheduledFuture) scheduledExecutorService.schedule(runnable,time,timeUnit);
        }
        Integer code = future.hashCode();
        if(map == null)
        {
            map = new ConcurrentHashMap<>();
        }
        map.put(code,future);
        return code;
    }

}
