package cn.rhyme.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 自动缓存的切面,标记上这个注解后能够自动缓存这个方法的返回数据,
 * 如果该对象返回数据不为空,则下次会自动从缓存拿取数据
 *
 * 注意,该方法仅支持基本对象类型的参数,暂不支持对象级别的参数(后续有需求再添加)
 * 所以请不要在有对象参数类型的方法上面使用
 *
 * @author LiuZhi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoCache {

    /**
     * 缓存的key值,如果没指定,则自动将类名+方法名+参数作为key值生成,该生成方式
     */
    String key() default "";

    /**
     * 过期时间,如果没指定则默认为600
     */
    int expired() default 600;

    /**
     * 过期单位,如果没指定则默认为秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
