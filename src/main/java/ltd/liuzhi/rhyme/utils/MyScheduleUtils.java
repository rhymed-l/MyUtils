//package ltd.liuzhi.rhyme.utils;
//
//
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.*;
//
///**
// * 定时器工具类
// *
// * @author LiuZhi
// * @Date 2020-05-11 18:11
// * @Version V1.0
// */
//public class MyScheduleUtils
//{
//    private static Map map = new ConcurrentHashMap<>();
//
//    private static ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
//
//    private MyScheduleUtils() {}
//
//    /**
//     * 开启一个定时器
//     * @param runnable 需要执行的方法
//     * @param milliseconds 毫秒
//     * @param isPeriodic 是否重复执行
//     */
//    public static void newTaskByMilliseconds(Runnable runnable,long milliseconds,boolean isPeriodic)
//    {
//        RunnableScheduledFuture future = scheduledExecutorService.(runnable,milliseconds,milliseconds,TimeUnit.MILLISECONDS);
//
//    }
//    public static ScheduledFuture newTaskBySeconds(Runnable runnable,int seconds)
//    {
//        ScheduledFuture  future = scheduledExecutorService.scheduleAtFixedRate(runnable,seconds,seconds,TimeUnit.SECONDS);
//        return future;
//    }
//
//    public static void newTaskByMinutes(Runnable runnable,int minutes)
//    {
//        scheduledExecutorService.scheduleAtFixedRate(runnable,minutes,minutes,TimeUnit.MINUTES);
//    }
//
//    public static void newTaskByHours(Runnable runnable,int hours)
//    {
//        scheduledExecutorService.scheduleAtFixedRate(runnable,hours,hours,TimeUnit.HOURS);
//    }
//
//    public static void newTask(Runnable runnable,long time,TimeUnit timeUnit)
//    {
//        scheduledExecutorService.scheduleAtFixedRate(runnable,time,time,timeUnit);
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.err.println("2秒试试");
//            }
//        };
//        ScheduledFuture future = newTaskBySeconds(runnable,2);
//        Thread.sleep(3000);
//        if(!future.isCancelled())
//        {
//            future.cancel(true);
//        }
//    }
//}
