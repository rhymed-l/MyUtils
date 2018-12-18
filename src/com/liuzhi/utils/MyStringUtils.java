package com.liuzhi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

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
     * 取文本最右边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本右边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextRights(String old,String index)
    {
        if(old.lastIndexOf(index)==-1)
        {
            return old;
        }
        return old.substring(old.lastIndexOf(index)+index.length());
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
     * 取出文本中间加强(最右)
     * @param old 原文本
     * @param left 左边的字符
     * @param right 右边的字符
     * @return 返回左边和右边中间的字符
     */
    public static String getTextMiddles(String old,String left,String right)
    {
        String temp = getTextRights(old,left);
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

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str)
    {
        if(isEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 删除所有不可见字符
     * @param str 传入的字符串
     * @return 返回删除全部不可见字符
     */
    public static String deleteAllInvisibleCharacters(String str)
    {
        return str.replaceAll("\\s","");
    }

    /**
     * 对List数组去重(HashSet剔除)
     * @param list 传入的List
     */
    public static void listDistinct(List list)
    {
        HashSet set = new HashSet(list) ;
        list.clear()                    ;
        list.addAll(set)                ;
    }

    /**
     * String数组去重
     * @param strArray 传入的String数组
     * @return 返回去重后的String数组
     */
    public static String[] strArrayDistinct(String[] strArray)
    {
        List<String>    list        = Arrays.asList(strArray)   ;
        List<String>    arrayList   = new ArrayList(list)       ;
        listDistinct(arrayList)                                 ;
        return  arrayList.toArray(new String[arrayList.size()]) ;
    }

    /**
     * 邮箱地址验证
     * @param email 需要验证的邮箱地址
     * @return 验证成功返回true,失败返回false
     */
    public static boolean checkEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        } else {
            return false;
        }
    }
}
