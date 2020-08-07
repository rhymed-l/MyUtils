package ltd.liuzhi.rhyme.utils.pojo.domain;



/**
 * 分页请求对象
 * @author LiuZhi
 * @Date 2020-08-05 11:11
 * @Version V1.0
 */
public class MyPageRequestDO
{
    private Integer pageNumber;

    private Integer pageSize;

    public Integer getPageNumber() {
        if(pageNumber == null)
        {
            return 0;
        }
        return pageNumber;
    }

    public Integer getPageSize() {
        if(pageSize == null)
        {
            return 10;
        }
        return pageSize;
    }

    @Override
    public String toString() {
        return "MyPageRequestDO{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
