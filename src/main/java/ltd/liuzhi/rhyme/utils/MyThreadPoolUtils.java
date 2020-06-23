package ltd.liuzhi.rhyme.utils;


import java.util.concurrent.*;

/**
 * 多线程工具包
 * @author LiuZhi
 * @Date 2020-05-20 10:58
 * @Version V1.0
 */
public class MyThreadPoolUtils
{
    private static ExecutorService executorService = null;

    /**
     * 多线程执行某一个任务
     * @param task 需要执行的任务方法
     */
    public static void execute(Runnable task)
    {
        execute(task,5,15);
    }

    /**
     * 多线程执行某一个任务
     * @param task 需要执行的任务方法
     * @param corePoolSize 需要多少个核心线程处理
     * @param maximumPoolSize 如果超出核心线程数处理的则需要多少额外的线程处理
     */
    public static void execute(Runnable task,int corePoolSize,int maximumPoolSize)
    {
        if(corePoolSize <=0)
        {
            corePoolSize = 5;
        }
        if(maximumPoolSize<=0)
        {
            maximumPoolSize = 15;
        }
        if(executorService == null){
            synchronized (MyThreadPoolUtils.class)
            {
                if(executorService == null){
                    executorService = newFixedThreadPool(corePoolSize,maximumPoolSize);
                }
            }
        }
        executorService.execute(task);
    }


    private static ExecutorService newFixedThreadPool(int corePoolSize, int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                160L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1024),Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
