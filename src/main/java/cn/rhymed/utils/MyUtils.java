package cn.rhymed.utils;

/**
 * 我的工具包
 * @author LiuZhi
 */
public class MyUtils
{
    private MyUtils(){}

    public static void notNull(Object obj, String str)
    {
        if(obj == null)
        {
            throw new RuntimeException(str);
        }
    }
}
