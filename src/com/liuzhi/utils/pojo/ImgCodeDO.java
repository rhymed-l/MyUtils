package com.liuzhi.utils.pojo;

public class ImgCodeDO
{
    private String id;
    private String code;
    private String data;

    public String getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ImgCodeDO(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public ImgCodeDO() {
        this.id = id;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
