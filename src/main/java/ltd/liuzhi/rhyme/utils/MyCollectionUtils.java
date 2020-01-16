package ltd.liuzhi.rhyme.utils;

import java.util.Collection;

/**
 * 集合工具类
 */
public class MyCollectionUtils
{
    private MyCollectionUtils(){}

    public static boolean CollectionIsEmpty(Collection collection)
    {
        if(collection == null || collection.size()== 0)
        {
            return true;
        }
        return false;
    }
    public static int CollectionSize(Collection collection)
    {
        if(collection == null)
        {
            return 0;
        }
        return collection.size();
    }
}
