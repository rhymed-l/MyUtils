package cn.rhymed.utils;

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
     * @param expression 表达式用点(.)连接,如:data.success 注意大小写敏感
     * @param jsonStr json文本
     * @param cls 需要转换的类型(类型强制绑定,比如布尔类型只能用布尔,整数只能用整数.如不确定类型可直接用String类型)
     * @return 返回对象类
     */
    public static <T> T getTargetObj(String expression, String jsonStr, Class<T> cls){
        if(MyStringUtils.isEmpty(expression) || MyStringUtils.isEmpty(jsonStr)){
            return null;
        }
        JSON json = getJsonByJsonStr(jsonStr);
        return getTargetObj(expression,json,cls);
    }

    /**
     * 返回json对象
     * @param jsonStr JSON字符串
     * @return 如果是数组则返回json数组,如果是json对象则返回json对象
     */
    public static JSON getJsonByJsonStr(String jsonStr){
        if(jsonStr == null){
            return null;
        }
        JSON json = null;
        try {
            if(jsonStr.startsWith("{") || jsonStr.startsWith("\"{")){
                json = JSON.parseObject(jsonStr);
            }else if(jsonStr.startsWith("[") || jsonStr.startsWith("\"[")){
                json = JSON.parseArray(jsonStr);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return json;
    }
    /**
     * 根据表达式获取单个对象
     * @param expression 表达式用点(.)连接,如:data.success 注意大小写敏感
     * @param json 原json
     * @param cls 需要转换的类型(类型强制绑定,比如布尔类型只能用布尔,整数只能用整数.如不确定类型可直接用String类型)
     * @return 返回对象类
     */
    public static <T> T getTargetObj(String expression, JSON json, Class<T> cls){
        if(expression == null || json == null){
            return null;
        }
        List<T> objs = listTargetObj(expression,json,cls);
        if(MyCollUtils.isNotEmpty(objs)){
            return objs.get(0);
        }
        return null;
    }
    /**
     * 根据表达式获取多个对象(数组中使用)
     * @param expression 表达式用点(.)连接,如:data.success 注意大小写敏感
     * @param jsonStr json字符串
     * @param cls 需要转换的类型(类型强制绑定,比如布尔类型只能用布尔,整数只能用整数.如不确定类型可直接用String类型)
     * @return 返回对象类
     */
    public static <T> List<T> listTargetObj(String expression,String jsonStr, Class<T> cls){
        if(MyStringUtils.isEmpty(expression) || MyStringUtils.isEmpty(jsonStr)){
            return new ArrayList<>();
        }
        JSON json = getJsonByJsonStr(jsonStr);
        return listTargetObj(expression,json,cls);
    }

    /**
     * 根据表达式获取多个对象(数组中使用)
     * @param expression 表达式用点(.)连接,如:data.success 注意大小写敏感
     * @param json 原json
     * @param cls 需要转换的类型(类型强制绑定,比如布尔类型只能用布尔,整数只能用整数.如不确定类型可直接用String类型)
     * @return 返回对象类
     */
    public static <T> List<T> listTargetObj(String expression,JSON json, Class<T> cls){
        if(MyStringUtils.isEmpty(expression) || json == null){
            return new ArrayList<>();
        }
        // 拷贝一个json出来
        List<String> expressions = MyCollUtils.splitToList(expression,"\\.");
        List<Object> pendingJsons = new ArrayList<>();
        List<Object> temporaryJsons = new ArrayList<>();
        List<T> result = new ArrayList<>();
        pendingJsons.add(json);

        MyCollUtils.forEach(expressions,(i,key)->{
            //数据交换
            temporaryJsons.clear();
            temporaryJsons.addAll(pendingJsons);
            pendingJsons.clear();

            //全部取出返回
            if(i >= expressions.size() - 1){
                temporaryJsons.forEach(t->{
                    if(t instanceof JSONObject){
                        Optional.ofNullable(((JSONObject) t).
                                get(key)).ifPresent(pendingJsons::add);
                    }else if(t instanceof JSONArray){
                        for(int y = 0;y < ((JSONArray) t).size();y++){
                            Optional.ofNullable(((JSONArray) t).getJSONObject(y).
                                    get(key)).ifPresent(pendingJsons::add);
                        }
                    }
                    pendingJsons.forEach(p->{
                        if(p == null){
                            return;
                        }
                        if(p.getClass().equals(cls)){
                            result.add((T) p);
                            return;
                        }
                        if(p instanceof String){
                           p = getJsonByJsonStr(p.toString());
                        }
                        if(p instanceof JSONObject){
                            Optional.ofNullable(((JSONObject) p).toJavaObject(cls)).ifPresent(result::add);
                        }
                        else if(p instanceof JSONArray){
                            for(int y = 0;y < ((JSONArray) p).size();y++){
                                Optional.ofNullable(((JSONArray) p).getJSONObject(y)
                                        .toJavaObject(cls)).ifPresent(result::add);
                            }
                        }else if(p!= null && p.getClass().equals(cls)){
                            result.add((T) p);
                        }
                    });
                });
            }else {
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
        });
        return result;
    }

}
