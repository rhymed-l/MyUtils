package cn.rhyme.utils;

/**
 * 装饰类.用来解决jdk8函数式编程外部对象为final问题
 */
public class MyWrap<T> {
    public T t;

    public MyWrap(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
