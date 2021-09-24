package cn.rhymed.utils.annotation.verify;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数字校验,如果是String类型则会自动转为long校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyNumberVerify {

    /**
     * 校验值必须为数字
     */
    boolean digits() default true;

    /**
     * 不是数字异常文本
     */
    String digitsMsg() default "";

    /**
     * 不能为空或者内容为0
     */
    boolean notEmpty() default false;

    /**
     * 字符串为空抛出异常文本
     */
    String emptyMsg() default "";

    /**
     * 不能为空
     * 只会校验 notEmpty 或者 notNull 中的一个
     */
    boolean notNull() default true;

    /**
     * 字符串为空抛出异常文本
     */
    String nullMsg() default "";

    /**
     * 字段最大值
     */
    long maxVal() default Long.MAX_VALUE;

    /**
     * 超过最大值报错信息
     */
    String maxMsg() default "";

    /**
     * 字段最小值
     */
    long minVal() default Long.MIN_VALUE;

    /**
     * 低于最小值报错信息
     */
    String minMsg() default "";
}
