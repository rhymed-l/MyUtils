package cn.rhymed.utils.pojo.domain;

/**
 * 图片打码对象
 * @author LiuZhi
 * @Date 2020-08-05 11:11
 * @Version V1.0
 */
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

    @Override
    public String toString() {
        return "ImgCodeDO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
