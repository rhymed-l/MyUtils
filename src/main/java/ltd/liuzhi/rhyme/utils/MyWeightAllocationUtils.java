package ltd.liuzhi.rhyme.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 订单权重分配工具类
 * @author LiuZhi
 */
public class MyWeightAllocationUtils {
    /**
     * 权重分配
     *
     * @param number  需要分配的订单数量
     * @param weights 分配员的权重数量
     * @return 返回应该分配的数量, 按照传进来的权重排序
     */
    public static Integer[] weightAllocation(Integer number, Double[] weights) {
        //已分配订单数
        Integer[] count = new Integer[weights.length];
        for (int i = 0; i < number; i++) {
            //当前权重
            Double[] current = new Double[weights.length];
            for (int w = 0; w < weights.length; w++) {
                current[w] = weights[w] / (count[w] == null ? 1 : count[w]);
            }
            int index = 0;
            Double currentMax = current[0];
            for (int d = 1; d < current.length; d++) {
                //考虑全等的情况
                Boolean isTrue = true;
                while (isTrue) {
                    Set set = new HashSet();
                    for (Double c : current) {
                        set.add(c);
                    }
                    if (set.size() == 1) {//代表全等
                        for (int e = 0; e < current.length; e++) {
                            current[e] = current[e] * Math.random();
                        }
                    } else {
                        isTrue = false;
                    }
                }
                //比较所有的数,寻找出下标最大的哪一位
                if (currentMax < current[d]) {
                    currentMax = current[d];
                    index = d;
                }
            }
            count[index]=count[index]==null?1:count[index]+1;
        }
        return count;
    }
}
