package cn.rhyme.utils;


import java.util.Map;

/**
 * JSON工具类
 * @author LiuZhi
 * @Date 2020-08-31 15:03
 * @Version V1.0
 */
public class MyJsonUtils {

    private MyJsonUtils(){}

    /**
     * Map参数转为JSON参数
     * @param param 需要转换的参数
     * @return 转换之后的参数
     */
    public static String mapToJson(Map<String,String> param)
    {
        if(param == null)
        {
            return "{}";
        }
        StringBuffer sb = new StringBuffer();
        MyCollectionUtils.forEach(param.entrySet(),(i,m)->{
            if(i == 0){
                sb.append("{");
            }
            if(i > 0){
                sb.append(",");
            }
            sb.append("\"").append(m.getKey()).append("\"");
            sb.append(":");
            sb.append("\"").append(m.getValue()).append("\"");
            if(i >= param.size() -1 ){
                sb.append("}");
            }
        });
        return sb.toString();
    }
//
//    public static Map<String,Object> JsonToMap(String param)
//    {
//        if(MyStringUtils.isEmpty(param))
//        {
//        }
//    }
}
