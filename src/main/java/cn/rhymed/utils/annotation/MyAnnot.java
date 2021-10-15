package cn.rhymed.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段注释
 * @author LiuZhi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnot {

    /**
     * 是否必须
     */
    boolean require() default false;

    /**
     * 字段备注
     */
    String value() default "";


}
