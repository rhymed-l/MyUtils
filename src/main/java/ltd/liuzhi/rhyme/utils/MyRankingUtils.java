package ltd.liuzhi.rhyme.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 简单的排名工具类
 */
public class MyRankingUtils
{

    private static List<RanKingDO> list = new ArrayList<>();

    /**
     * 放排名
     */
    public static boolean putRanKing(String key)
    {
        return putRanKing(key,null);
    }
    /**
     * 放排名
     */
    public static boolean putRanKing(String key,Integer val) {
        boolean contains = false;
        for (RanKingDO ranKingDO : list) {

            if (ranKingDO.getKey().equalsIgnoreCase(key)) {
                ranKingDO.setVal(MyNumberUtils.getIntegerIncrement(ranKingDO.getVal()));
                contains = true;
            }
        }
        if (!contains) {
            RanKingDO ranKingDO = new RanKingDO();
            ranKingDO.setKey(key);
            if(val==null)
            {
                ranKingDO.setVal(1);
            }else
                {
                    ranKingDO.setVal(val);
                }
            list.add(ranKingDO);
        }
        return true;
    }

    /**
     * 获取排名
     */
    public static String getRanKing() {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        //根据val排序
        list = list.stream().sorted((r1,r2)->r2.getVal().compareTo(r1.getVal())).collect(Collectors.toList());
        for(RanKingDO ranKingDO : list)
        {
            if(i>0)
            {
                sb.append("\n");
            }
            sb.append(ranKingDO.getKey()).append(":").append(ranKingDO.getVal());
            i++;
        }
        return sb.toString();
    }

    /**
     *
     */
    public static void clearRanKing()
    {
        list = new ArrayList<>();
    }
}
class RanKingDO
{
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    private String key;
    private Integer val;
}
