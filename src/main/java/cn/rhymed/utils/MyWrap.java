package cn.rhymed.utils;

/**
 * 装饰类.用来解决jdk8函数式编程外部对象为final问题
 */
public class MyWrap<T> {

    /**
     * 内部存储的数值
     */
    private T t;

    /**
     * 不建议使用new的方式构造,请使用from构造
     */
    public MyWrap(T t) {
        this.t = t;
    }

    public static MyWrap from(Object t) {
        return new MyWrap(t);
    }

    /**
     * 获取值
     */
    public T get() {
        return t;
    }

    /**
     * 重新设置值
     */
    public void set(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return t.toString();
    }
}
