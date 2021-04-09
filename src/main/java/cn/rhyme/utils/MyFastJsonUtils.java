package cn.rhyme.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 阿里巴巴的FastJson工具类
 */
public class MyFastJsonUtils
{
    public MyFastJsonUtils(){}

    /**
     * 根据表达式获取单个对象
     * @param expression 表达式用点(.)连接,如:data.success
     * @param json 原json
     * @param cls 需要转换的类型
     * @return 返回对象类
     */
    public static <T> T getTargetObj(String expression, JSONObject json, Class<T> cls){
        if(expression == null){
            return null;
        }
        List<T> objs = listTargetObj(expression,json,cls);
        if(MyCollectionUtils.isNotEmpty(objs)){
            return objs.get(0);
        }
        return null;
    }

    /**
     * 根据表达式获取多个对象(数组中使用)
     * @param expression 表达式用点(.)连接,如:data.success
     * @param json 原json
     * @param cls 需要转换的类型
     * @return 返回对象类
     */
    public static <T> List<T> listTargetObj(String expression,JSON json, Class<T> cls){
        if(expression == null || json == null){
            return null;
        }
        // 拷贝一个json出来
        List<String> expressions = MyStringUtils.splitToList(expression,"\\.");
        List<Object> pendingJsons = new ArrayList<>();
        List<Object> temporaryJsons = new ArrayList<>();
        List<T> result = new ArrayList<>();
        pendingJsons.add(json);

        MyCollectionUtils.forEach(expressions,(i,key)->{
            //数据交换
            temporaryJsons.clear();
            temporaryJsons.addAll(pendingJsons);
            pendingJsons.clear();

            //全部取出返回
            if(i >= expressions.size() - 1){
                temporaryJsons.forEach(t->{
                    if(t instanceof JSONObject){
                        Optional.ofNullable(((JSONObject) t).
                                getObject(key,cls)).ifPresent(result::add);
                    }
                    else if(t instanceof JSONArray){
                        for(int y = 0;y < ((JSONArray) t).size();y++){
                            Optional.ofNullable(((JSONArray) t).getJSONObject(y).
                                    getObject(key,cls)).ifPresent(result::add);
                        }
                    }
                });
            }else {
//                if(key.startsWith("[]")){
//                    temporaryJsons.forEach(t->{
//                        if(t instanceof JSONObject){
//                            Optional.ofNullable(((JSONObject) t).
//                                    getJSONArray(key.substring(2))).ifPresent(pendingJsons::add);
//                        }else if(t instanceof JSONArray){
//                            for(int y = 0;y < ((JSONArray) t).size();y++){
//                                Optional.ofNullable(((JSONArray) t).getJSONObject(y).
//                                        getJSONArray(key.substring(2))).ifPresent(pendingJsons::add);
//                            }
//                        }
//                    });
//                }else
                    {
                    temporaryJsons.forEach(t->{
                        if(t instanceof JSONObject){
                            Optional.ofNullable(((JSONObject) t).
                                    get(key)).ifPresent(pendingJsons::add);

                        }else if(t instanceof JSONArray){
                            for(int y = 0;y < ((JSONArray) t).size();y++){
                                Optional.ofNullable(((JSONArray) t).getJSONObject(y).get(key)).ifPresent(pendingJsons::add);
                            }
                        }
                    });
                }
            }
        });
        return result;
    }
}
