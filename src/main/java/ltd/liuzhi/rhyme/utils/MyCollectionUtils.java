package ltd.liuzhi.rhyme.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * @author LiuZhi
 */
public class MyCollectionUtils
{
    private MyCollectionUtils(){}

    /**
     * 集合是否为空 包括null
     * @param collection 判断的集合
     * @return 空则true 否则false
     */
    public static boolean isEmpty(Collection collection)
    {
        if(collection == null || collection.size()== 0)
        {
            return true;
        }
        return false;
    }
    /**
     * 集合是否为空 包括null
     * @param collection 判断的集合
     * @return 空则true 否则false
     */
    public static boolean isNotEmpty(Collection collection)
    {
       return !isEmpty(collection);
    }

    /**
     * 获取集合大小
     * @param collection 获取的集合
     * @return 返回集合的大小
     */
    public static int getSize(Collection collection)
    {
        if(collection == null)
        {
            return 0;
        }
        return collection.size();
    }

    /**
     * 将数组转为List集合
     * @param objects 需要转换的数组
     * @return 返回转换后的集合
     */
    public static <T> List<T> arrayToList(T[] objects)
    {
        List<T> list = new ArrayList<>();
        for(T t : objects)
        {
            list.add(t);
        }
        return list;
    }

    /**
     * 拷贝list
     * @param list 拷贝的原list
     * @return 返回拷贝后的集合
     */
    public static <T> List<T> copyList(List<T> list)
    {
        return list.stream().collect(Collectors.toList());
    }

    /**
     * 将list中的对象拷贝成一个新的对象(值复制)
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
}
