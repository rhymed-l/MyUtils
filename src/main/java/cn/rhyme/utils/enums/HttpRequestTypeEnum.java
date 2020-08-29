package cn.rhyme.utils.enums;

/**
 * 请求类型枚举类
 * @author LiuZhi
 * @Date 2020-08-26 上午10:30
 * @Version V1.0
 */
public enum HttpRequestTypeEnum {
    PARAMS(""),
    FORM_DATA("multipart/form-data;"),
    X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded;"),
    RAW_TEXT("text/plain"),
    RAW_JAVASCRIPT("application/javascript"),
    RAW_JSON("application/json"),
    RAW_XML("application/xml"),
    binary("");

    private String type;

    HttpRequestTypeEnum(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
