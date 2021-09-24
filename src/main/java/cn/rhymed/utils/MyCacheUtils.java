package cn.rhymed.utils;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具
 *
 * 已知的缺陷...如果是引用对象存进去,外部的修改将会导致内部的修改,
 * 即传缓存一个list进去,如果外部这个list添加了对象,缓存中的list也会添加
 * 这个缺陷将在未来修复
 *
 * @author LiuZhi
 * @Date 2020-06-11 17:50
 * @Version V1.0
 */
public class MyCacheUtils
{
    /**
     * 该容器为了防止内存超出,只缓存1000条数据,超出则会被算法移除
     */
   private static Map<String, Object> data;

    /**
     * 缓存大小容量
     */
   private static int cacheSize = 1000;

    /**
     * 获取缓存中的值
     * @param key 缓存中的key值
     * @param tClass 需要返回的对象Class
     * @param <T> 返回的对象
     * @return 返回缓存中的对象
     */
   public static synchronized  <T> T get(String key,Class<T> tClass)
   {
       if(data == null){
           return null;
       }
       return (T) data.get(key);
   }

    /**
     * 获取缓存中的值
     * @param key 缓存中的key值
     * @return 返回缓存中的对象
     */
   public static Object get(String key)
   {
       return get(key,Object.class);
   }

    /**
     * 缓存中是否存在某个key的值
     * @param key 缓存中的key值
     * @return 缓存中存在该值则true 否则false
     */
   public static boolean exist(String key)
   {
       return get(key) != null;
   }

    /**
     * 存储值到缓存中
     * @param key 缓存中的key值
     * @param value 需要存储的值
     */
    public static void put(String key,Object value)
    {
        put(key,value,0,null);
    }

    /**
     * 存储值到缓存中
     * @param key 缓存中的key值
     * @param value 需要存储的值
     * @param time 多少时间后过期
     * @param timeUnit 过期的时间单位
     */
    public static void put(String key, Object value, long time, TimeUnit timeUnit)
    {
        Object newObj = value;
//        if(MyObjectUtils.isBaseType(value)){
//            newObj = value;
//        }else if(value instanceof String){
//            newObj = String.valueOf(value);
//        }else {
//            newObj = MyObjectUtils.copy(value,value.getClass());
//        }
        if(data == null)
        {
            synchronized (MyCacheUtils.class)
            {
                if(data == null)
                {
                    data = new LinkedHashMap<>();
                }
            }
        }
        if(MyStringUtils.isEmpty(key)){
            throw new RuntimeException("缓存的key值不允许为空");
        }
        verifyTime(time,timeUnit);
        //超出大小移除首位
        if(data.size() > cacheSize)
        {
            data.entrySet().forEach(e->data.remove(e.getKey()));
        }
        data.put(key,newObj);
        if(time > 0 )
        {
            MyScheduleUtils.newTask(()-> data.remove(key),time,timeUnit,false);
        }

    }

    /**
     * 存储值到缓存中
     * @param key 缓存中的key值
     * @param value 需要存储的值
     * @param seconds 多少时间后过期 0 则永不过期
     */
    public static void put(String key, Object value, long seconds)
    {
       put(key,value,seconds,TimeUnit.SECONDS);
    }

    /**
     * 清除全部缓存数据
     */
    public static void clearCache()
    {
        if(data != null){
            data.clear();
        }
        data = null;
    }
    /**
     * 删除一个缓存
     *  @param key 缓存中的key值
     */
    public static synchronized void remove(String key)
    {
        if(data == null)
        {
            return;
        }
        data.remove(key);
    }

    private static void verifyTime(long time, TimeUnit timeUnit)
    {
        if(time < 0)
        {
            throw new RuntimeException("非法的缓存时间,不允许小于0");
        }
        if(time > 0 && timeUnit == null)
        {
            throw new RuntimeException("缓存的时间单位不能为null");
        }
    }
}
