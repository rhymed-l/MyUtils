package cn.rhymed.utils.annotation.verify;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLengthVerify {


    /**
     * 长度最大值
     */
    long maxVal();

    /**
     * 超过长度报错信息
     */
    String maxMsg() default "";

    /**
     * 长度最小值
     */
    long minVal();

    /**
     * 低于最小值报错信息
     */
    String minMsg() default "";

}
