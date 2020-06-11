package ltd.liuzhi.rhyme.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LiuZhi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MyCSVField
{
    /**
     * 导出CSV文件的标题
     */
    String title() default "";

    /**
     * 是否导出
     */
    boolean export() default true;

    /**
     * 如果没有值的情况默认值是多少
     */
    String value() default "";
}
