package ltd.liuzhi.rhyme.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象转换类
 */
public class MyConvertUtils
{
    private MyConvertUtils(){}

    public static  <T>T mapToObject(Map map,Class<T> tClass)
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
                if(field.getName().equalsIgnoreCase(entry.getKey()))
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
}
