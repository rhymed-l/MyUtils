package com.liuzhi.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * 对象转换类
 */
public class MyConvertUtils
{
    public static  <T>T MapToObject(Map map,Class<T> tClass)
    {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        Field[] fields = tClass.getFields();
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String,Object> entry = iterator.next();
            for(Field field : fields)
            {
                if(field.getName().equalsIgnoreCase(entry.getKey()))
                {
                    try {
                        field.set(t,entry.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return t;
    }
}
