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
        if(executorService == null){
            synchronized (MyThreadPoolUtils.class)
            {
                if(executorService == null){
                    executorService = newFixedThreadPool(5,10);
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
