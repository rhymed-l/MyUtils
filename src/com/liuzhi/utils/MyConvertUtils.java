package com.liuzhi.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象转换类
 */
public class MyConvertUtils
{
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
                        //不完善的,这里只能转普通的String类,如果是其他类型,必然报错!
                        field.setAccessible(true);
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
