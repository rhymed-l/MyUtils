package com.liuzhi.utils;

import java.util.List;

public class MyStringUtils
{
    /**
     * 私有化构造器
     */
    private MyStringUtils(){}

    /**
     * 取文本所在位置
     * @param old 原文本
     * @param index 需要取的字符串
     * @return 文本所在位置,未找到则返回-1
     */
    public static Integer getIndex(String old, String index)
    {
        return old.indexOf(index);
    }

    /**
     * 取文本右边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本右边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextRight(String old,String index)
    {
        if(old.indexOf(index)==-1)
        {
            return old;
        }
        return old.substring(old.indexOf(index)+index.length());
    }

    /**
     * 取文本左边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本左边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextLeft(String old,String index)
    {
        if(old.indexOf(index)==-1)
        {
            return old;
        }
        return old.substring(0,old.indexOf(index));
    }

    /**
     * 取出文本中间
     * @param old 原文本
     * @param left 左边的字符
     * @param right 右边的字符
     * @return 返回左边和右边中间的字符
     */
    public static String getTextMiddle(String old,String left,String right)
    {
        String temp = getTextRight(old,left);
        return getTextLeft(temp,right);
    }

    /**
     * 判断文本是否是空(对象为空,或者内容为空)
     * @param text 需要判断的文本
     * @return 空则返回真
     */
    public static Boolean isEmpty(String text)
    {
        return text==null?true:text.trim().equals("")?true:false;
    }
    /**
     * 判断List组数是否是空(对象为空,或者内容为空)
     * @param list 需要判断的LIST数组
     * @return 空则返回真
     */
    public static Boolean isEmpty(List list)
    {
        return list==null?true:list.size()>0?false:true;
    }

    /**
     * 将String字符转为Integer类型
     * @param number 需要转换的数字
     * @return 返回转化之后的数字
     */
    public static Integer strCaseInt(String number)
    {
        return new Integer(number);
    }
    /**
     * 将String字符转为Integer类型
     * @param number1 需要转换的数字
     * @param number2 需要相加的值
     * @return 返回转化之后的数字
     */
    public static Integer strAddCaseInt(String number1,Object number2)
    {
        return new Integer(number1) + new Integer(number2.toString());
    }
}
