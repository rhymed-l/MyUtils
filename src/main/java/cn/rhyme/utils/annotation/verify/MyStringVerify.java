package cn.rhyme.utils.annotation.verify;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyStringVerify {

    /**
     * 不能为空或者内容为空
     * 优先校验不为空
     */
    boolean notEmpty() default true;

    /**
     * 字符串为空抛出异常异常文本
     */
    String emptyMsg() default "";

    /**
     * 不能为空
     * 只会校验 notEmpty 或者 notNull 中的一个
     */
    boolean notNull() default false;

    /**
     * 字符串为null抛出异常异常文本
     */
    String nullMsg() default "";

    /**
     * 长度最大值
     */
    long maxVal() default -1;

    /**
     * 超过长度报错信息
     */
    String maxMsg() default "";

    /**
     * 长度最小值
     */
    long minVal() default -1;

    /**
     * 低于最小值报错信息
     */
    String minMsg() default "";


    /**
     * 正则匹配
     */
    String regexp() default "";

    /**
     * 正则不匹配报错信息
     */
    String regexpMsg() default "";
}
