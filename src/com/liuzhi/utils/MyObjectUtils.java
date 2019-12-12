package com.liuzhi.utils;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.*;


/**
 * 对象处理工具类
 * @author LiuZhi
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

    /**
     * copyNotNull 将源对象里面不为Nu'll的对象拷贝到目标对象里面
     * @param sourceObj 源对象
     * @param targetObj 目标对象
     */
    public static <T> void copyNotEmpty(T sourceObj, T targetObj) {
        Class target = targetObj.getClass();
        Class source = sourceObj.getClass();
        if(target != source)
        {
            throw new RuntimeException("对象不一致不允许拷贝");
        }
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        for(Field sourceField : sourceFields)
        {
            sourceField.setAccessible(true);
            try {
                Object obj = sourceField.get(sourceObj);
                if(MyObjectUtils.objIsEmpty(obj))
                {//数据源属性为空跳过本次循环
                    continue;
                }
                for(Field targetField : targetFields)
                {
                    if(sourceField.getName().equalsIgnoreCase(targetField.getName()) && sourceField.getType() == targetField.getType())
                    {//如果目标对象没有数据则替换数据
                        targetField.setAccessible(true);
                        if(MyObjectUtils.objIsEmpty(targetField.get(targetObj)))
                        {
                            targetField.set(targetObj,obj);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 判断对象是否为空
     * @param obj 需要判断的对象
     * @return 如果是空则返回真,否则取反
     */
    public static boolean objIsEmpty(Object obj)
    {
        if(obj == null)
        {
            return true;
        }else if(obj instanceof String)
        {
            String str = (String) obj;
            if(str.trim().equals(""))
            {
                return true;
            }
        }else if(obj instanceof Collection)
        {
            return ((Collection) obj).isEmpty();
        }else if(obj instanceof Map)
        {
            return ((Map) obj).isEmpty();
        }
        return false;

    }
}