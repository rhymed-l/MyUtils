package ltd.liuzhi.rhyme.utils;

import java.io.Serializable;

/**
 * RestFull统一资源返回结果工具类
 *
 * @author LiuZhi
 * @Date 2020-04-18 19:30
 * @Version V1.0
 */
public class MyResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    public final static Integer CODE_SUCCESS    = 1 ;

    public final static Integer CODE_FAIL       = 0 ;

    public final static String  MSG_SUCCESS     = "操作成功";

    public final static String  MSG_FAIL        = "操作失败";


    /**
     * 返回编号
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 是否请求成功
     */
    public boolean isSuccess()
    {
        return CODE_SUCCESS.equals(this.code);
    }

    public static <T> MyResult<T> build(Integer status, String msg, T data) {
        return new MyResult<T>(status, msg, data);
    }

    public static <T> MyResult<T> ok(T data) {
        return new MyResult<T>(data);
    }
    public static <T> MyResult<T> ok(String message,T data) {
        return new MyResult<T>(CODE_SUCCESS,message,data);
    }

    public static <T> MyResult<T> ok() {
        return new MyResult<T>(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> MyResult<T> fail() {
        return new MyResult<T>(CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> MyResult<T> fail(String message) {
        return new MyResult<T>(CODE_FAIL, message, null);
    }
    public static <T> MyResult<T> fail(String message,T data) {
        return new MyResult<T>(CODE_FAIL, message, data);
    }

    public static <T> MyResult<T> build(Integer status, String message) {
        return new MyResult<T>(status, message, null);
    }


    public MyResult(Integer status, String msg, T data) {
        this.code = status;
        this.message = msg;
        this.data = data;
    }

    public MyResult(T data) {
        this.code = 0;
        this.message = MSG_SUCCESS;
        this.data = data;
    }

    public MyResult() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
