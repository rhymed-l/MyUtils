package com.liuzhi.utils;

import java.math.BigDecimal;

public class MyNumberUtils
{
    /**
     * 强制获取非空的数字
     * @param number 需要判断的数字
     * @return 如果是空则返回默认值0,否则返回原数字
     */
    public static Integer getIntegerForSkipBlanks(Integer number)
    {
        return number==null?0:number;
    }
    /**
     * 强制获取非空的BigDecimal
     * @param bigDecimal 需要判断的数字
     * @return 如果是空则返回默认值0,否则返回原数字
     */
    public static BigDecimal getBigDecimalForSkipBlanks(BigDecimal bigDecimal)
    {
        return bigDecimal==null?new BigDecimal("0.00"):bigDecimal;
    }

    /**
     * 获取这个值自增后的值
     * @param number 需要判断的数字
     * @return 返回这个值自增后的值
     */
    public static Integer getIntegerIncrement(Integer number)
    {
        return number==null?1:++number;
    }
    /**
     * 获取这个值自减后的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerDecrement(Integer number)
    {
        return number==null?-1:--number;
    }
    /**
     * 获取这个值自减后的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerAddNumer(Integer integer,Integer number)
    {
        integer = (integer == null ? 0 : integer);
        number = (number == null ? 0 : number);
        return integer+number;
    }
}
