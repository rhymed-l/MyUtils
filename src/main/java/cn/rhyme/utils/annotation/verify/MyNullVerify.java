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
public @interface MyNullVerify {

    /**
     * 不能为空
     */
    boolean notNull() default true;

    /**
     * 字符串为null抛出异常异常文本
     */
    String nullMsg() default "";
}
