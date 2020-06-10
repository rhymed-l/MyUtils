package ltd.liuzhi.rhyme.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象转换类
 * @author LiuZhi
 */
public class MyConvertUtils
{
    private MyConvertUtils(){}

    /**
     * 将map对象中的数据映射为对象
     * @param map map对象
     * @param tClass 需要映射的类
     * @param ignoreUnderscore 是否忽略下划线
     * @return 返回map映射后的对象
     */
    public static  <T>T mapToObject(Map map,Class<T> tClass,boolean ignoreUnderscore)
    {
        T t;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        List<Field> list = new ArrayList<>();

        for(Field f : tClass.getFields())
        {
            list.add(f);
        }
        for(Field f : tClass.getDeclaredFields())
        {
            list.add(f);
        }

        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String,Object> entry = iterator.next();
            for(Field field : list)
            {
                if((ignoreUnderscore && entry.getKey() != null) ? field.getName().replaceAll("_","").equalsIgnoreCase(entry.getKey().replaceAll("_",""))
                : field.getName().equalsIgnoreCase(entry.getKey()))
                {
                    try {
                        //不完善的
                        field.setAccessible(true);
                        if(field.getClass().equals(entry.getValue().getClass()))
                        {
                            field.set(t,entry.getValue());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return t;
    }

    /**
     * 将map对象中的数据映射为对象
     * @param map map对象
     * @param tClass 需要映射的类
     * @return 返回map映射后的对象
     */
    public static  <T>T mapToObject(Map map,Class<T> tClass)
    {
       return mapToObject(map,tClass,false);
    }
}
