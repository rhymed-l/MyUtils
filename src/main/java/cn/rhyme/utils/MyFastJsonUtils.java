package cn.rhyme.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;

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
     * @param expression 表达式
     * @param json 原json
     * @param cls 需要转换的类型
     * @return 返回对象类
     */
    public static <T> T getTargetObj(@NotNull String expression,@NotNull JSONObject json, Class<T> cls){
        if(expression == null){
            return null;
        }
        List<T> objs = handle(expression,json,cls);
        if(MyCollectionUtils.isNotEmpty(objs)){
            return objs.get(0);
        }
        return null;
    }

    /**
     * 根据表达式获取多个对象(数组中使用)
     * @param expression 表达式
     * @param json 原json
     * @param cls 需要转换的类型
     * @return 返回对象类
     */
    public static <T> List<T> listTargetObj(@NotNull String expression,@NotNull JSONObject json, Class<T> cls){
        if(expression == null){
            return null;
        }
        List<T> objs = handle(expression,json,cls);
        return objs;
    }

    /**
     * 迭代处理多层数据字段如果跟外层相同会出问题
     */
    public static <T> List<T>  handle(String expression,JSONObject jsonObject,Class<T> cls){
        List<T> list = new ArrayList<>();
        String name = MyStringUtils.getTextLeft(expression,".");
        while(!name.equals(expression) && jsonObject != null){
            expression = MyStringUtils.getTextRight(expression,".");
            if(name.startsWith("[]")){
                name = name.substring(2);
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray(name);
                    for(int i=0;i<jsonArray.size();i++){
                        list.addAll(handle(expression,jsonArray.getJSONObject(i),cls));
                    }
                }catch (Exception e){}
            }else {
                jsonObject = jsonObject.getJSONObject(name);
            }
            name = MyStringUtils.getTextLeft(expression,".");
        }
        Optional.ofNullable(jsonObject.getObject(name,cls)).ifPresent(t->list.add(t));
        return list;
    }
}
