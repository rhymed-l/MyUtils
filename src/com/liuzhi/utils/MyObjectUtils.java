package com.liuzhi.utils;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 对象处理工具类
 */
public class MyObjectUtils
{
    private MyObjectUtils(){}

    /**
     * 实例化对象
     * @param clazz 类
     * @return 对象
     */
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiate(clazz);
    }

    /**
     * 实例化对象
     * @param clazzStr 类名
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T instantiate(@NotNull Class<T> clazz) {
        MyUtils.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            throw new RuntimeException("不能对接口实例化");
        } else {
            try {
                return clazz.newInstance();
            } catch (InstantiationException var2) {
                throw new RuntimeException("不能对抽象类创建实例");
            } catch (IllegalAccessException var3) {
                throw new RuntimeException("改类构造函数私有化不能创建实例");
            }
        }
    }


    /**
     * List根据class重新生成List对象
     * @param list 需要被转换的数据
     * @param clazz 需要转换的对象
     * @return 返回对应对象的List
     */
    public static <T> List replaceListObj(List list, Class<T> clazz)
    {
        Iterator iterator = list.iterator();
        List newList = new ArrayList();
        while (iterator.hasNext())
        {
            Object obj = iterator.next();
            Object newObj = MyObjectUtils.copy(obj,clazz);
            newList.add(newObj);
        }
        return newList;
    }

    /**
     * copy 简单的对象属性到另一个对象
     * @param obj 对象
     * @param clazz 类名
     * @return T 返回新的对象
     */
    public static <T> T copy(Object obj, Class<T> clazz) {
        T to = newInstance(clazz);
        Class source = obj.getClass();
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = clazz.getDeclaredFields();

        for(Field sourceField : sourceFields)
        {
            for(Field targetField : targetFields)
            {
                if(sourceField.getName().equalsIgnoreCase(targetField.getName()) && sourceField.getType() == targetField.getType())
                {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    try {
                        targetField.set(to,sourceField.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return to;
    }
}