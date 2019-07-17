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
}
