package cn.rhyme.utils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 数字工具类
 * @author LiuZhi
 */
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
        return bigDecimal == null ? new BigDecimal("0.00") : bigDecimal;
    }

    /**
     * 获取这个值自增后的值
     * @param number 需要判断的数字
     * @return 返回这个值自增后的值
     */
    public static Integer getIntegerIncrement(Integer number)
    {
        return number == null ? 1 : ++number;
    }

    /**
     * 获取这个值自减后的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerDecrement(Integer number)
    {
        return number == null ? -1 : --number;
    }

    /**
     * 获取这个值自增后的值
     * @param number 需要判断的数字
     * @return 返回这个值自增后的值
     */
    public static Integer getIntegerIncrement(String number)
    {
        if(MyStringUtils.isInteger(number))
        {
            Integer integer = Integer.valueOf(number);
            return ++integer;
        }
        return number == null ? 1 : null;
    }

    /**
     * 获取这个值自减后的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerDecrement(String number)
    {
        if(MyStringUtils.isInteger(number))
        {
            Integer integer = Integer.valueOf(number);
            return --integer;
        }
        return number == null ? -1 : null;
    }

    /**
     * 获取这个值增加另外一个的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerAddNumer(Integer integer,Integer number)
    {
        integer = (integer == null ? 0 : integer);
        number = (number == null ? 0 : number);
        return integer+number;
    }

    /**
     * 获取这个值减去另外一个的值
     * @param number 需要判断的数字
     * @return 返回这个值自减后的值
     */
    public static Integer getIntegerSubNumber(Integer integer,Integer number)
    {
        integer = (integer == null ? 0 : integer);
        number = (number == null ? 0 : number);
        return integer - number;
    }

    /**
     * 获取随机数
     * @param minVal 最小值取值
     * @param maxVal 最大值取值
     * @return 返回最小值介于最大值之间的数并包括最小值最大值
     */
    public static Integer getRandomNumber(Integer minVal,Integer maxVal)
    {
        Random random = new Random();
        return random.nextInt(maxVal + 1 - minVal) + minVal;
    }

    /**
     * 将double转为Integer类型
     * @param d 需要转换的数值
     * @return 转换后的数值
     */
    public static Integer doubleToInt(Double d){
        return d == null ? null : d.intValue();
    }
    /**
     * 将double转为Integer类型,如果为空返回0
     * @param d 需要转换的数值
     * @return 转换后的数值
     */
    public static Integer doubleToIntForSkipBlanks(Double d){
        return d == null ? 0 : d.intValue();
    }

    /**
     * 比较两个数字是否相等
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 相等返回真,否则返回假
     */
    public static boolean compareTo(Object obj1,Object obj2){
        if(obj1 == null ||
                obj2 ==null ||
                !MyStringUtils.isDouble(obj1.toString()) ||
                !MyStringUtils.isDouble(obj2.toString())){
            return false;
        }
        BigDecimal bigDecimal1 = new BigDecimal(obj1.toString());
        BigDecimal bigDecimal2 = new BigDecimal(obj2.toString());
        return bigDecimal1.compareTo(bigDecimal2) == 0;
    }

    /**
     * 比较一个数字是否为零(null也返回真)
     * @return 如果是零则返回真,否则返回假
     */
    public static boolean isZero(Object obj){
        if(obj == null){
            return true;
        }
        return compareTo(obj,0);
    }
}
