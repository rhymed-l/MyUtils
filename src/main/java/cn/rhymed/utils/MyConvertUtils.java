package cn.rhymed.utils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Field> list = MyObjectUtils.getObjectAllField(tClass);
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

    /**
     * 将Object对象映射为Map对象
     * @param object object对象
     * @return 返回map映射后的对象
     */
    public static  Map<String,Object> objectToMap(Object object)
    {
        Map<String,Object> map = new HashMap<>();
        List<Field> list = MyObjectUtils.getObjectAllField(object.getClass());
        list.forEach(f->{
            f.setAccessible(true);
            try {
                map.put(f.getName(),f.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * 将Object对象映射为Map对象
     * @param object object对象
     * @return 返回map映射后的对象
     */
    public static  Map<String,String> objectToMapString(Object object)
    {
        Map<String,String> map = objectToMap(object).entrySet().stream().collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue() == null ? "" : m.getValue().toString()));
        return map;
    }
}
