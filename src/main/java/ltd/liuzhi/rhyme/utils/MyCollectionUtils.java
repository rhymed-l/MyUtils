package ltd.liuzhi.rhyme.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
}
