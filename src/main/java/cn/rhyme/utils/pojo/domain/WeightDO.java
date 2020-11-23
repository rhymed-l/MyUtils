package cn.rhyme.utils.pojo.domain;

import cn.rhyme.utils.annotation.MyCSVField;

/**
 * 权重数据
 * @author LiuZhi
 * @Date 2020-08-05 11:11
 * @Version V1.0
 */
public class WeightDO
{
    private Long id;
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "WeightDO{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
