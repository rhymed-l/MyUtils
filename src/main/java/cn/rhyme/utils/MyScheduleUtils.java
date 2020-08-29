package cn.rhyme.utils;


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

    /**
     * 自动检查定时任务标识
     */
    private static boolean autoCheckTag = false;
    /**
     * 自动检查定时任务标识
     */
    private static Integer autoCheckTaskFlag;

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
            throw new RuntimeException("找不到相关的任务");
        }

        boolean result = future.cancel(isCloseNow);
        if(result || future.isDone() || future.isCancelled())
        {
            map.remove(taskFlag);
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
        Integer code = null;
        try {
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
            code = future.hashCode();
            if(map == null)
            {
                map = new ConcurrentHashMap<>(200);
            }
            map.put(code,future);
            //开启自动检查
            autoCheck();
            return code;
        }catch (Exception e)
        {
            if(code != null)
            {
               shutdownTask(code);
            }
            throw new RuntimeException("开启定时任务失败");
        }
    }

    /**
     * 自动检查是否存在一次性定时任务或者已经执行完毕的定时任务进行删除
     */
    private static synchronized void autoCheck()
    {
        try {
            if(!autoCheckTag)
            {
                //置初始值
                autoCheckTag = true;
                autoCheckTaskFlag = openTask(()->{
                    map.entrySet().forEach(e->{
                        if(e == null || e.getValue().isDone() || e.getValue().isCancelled())
                        {
                            shutdownTask(e.getKey(),true);
                        }
                        if(map.size() == 1)
                        {
                            shutdownTask(autoCheckTaskFlag);
                            autoCheckTag = false;
                        }
                    });
                },3,TimeUnit.SECONDS,true);
            }
        }catch (Exception e)
        {
            shutdownTask(autoCheckTaskFlag);
            autoCheckTag = false;
        }
    }
}
