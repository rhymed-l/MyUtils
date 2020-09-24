package cn.rhyme.utils;


import cn.rhyme.utils.annotation.MyCSVField;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出CSV工具
 * @author LiuZhi
 * @Date 2020-06-11 14:02
 * @Version V1.0
 */
public class MyCsvExportUtils
{
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /**
     * CSV文件行分隔符
     */
    private static final String CSV_ROW_SEPARATOR = "\r\n";

    /**
     * 导出数据为输出流
     * @param data 集合数据
     */
    public static void doExport(List data,OutputStream os)
    {
        String str = doExportString(data);
        if(str == null){
            return;
        }
        // 写出响应
        try {
            os.write(str.getBytes("UTF-8"));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出数据为CSV格式的字符串
     * @param data 数据源
     * @return 返回CSV格式的字符串
     */
    public static String doExportString(List data)
    {
        Map<String, MyCSVField> fieldMap = new LinkedHashMap<>();
        if(MyCollectionUtils.getSize(data) == 0)
        {
            return null;
        }
        //先获取第一个对象进行匹配标题
        Object object = data.get(0);
        List<Field> fields = MyObjectUtils.getObjectAllField(object.getClass());
        for(Field field : fields)
        {
            field.setAccessible(true);
            MyCSVField csvField = field.getAnnotation(MyCSVField.class);
            if(csvField != null)
            {
                fieldMap.put(field.getName(),csvField);
            }
        }
        StringBuffer buf = new StringBuffer();
        // 组装表头
        fieldMap.entrySet().forEach(e->
                buf.append("\"").append(e.getValue().title()).append("\"").append(CSV_COLUMN_SEPARATOR));
        buf.append(CSV_ROW_SEPARATOR);
        // 组装数据
        data.forEach(d->{
            Class cls = d.getClass();
            fieldMap.entrySet().forEach(entry->{
                String filedName = entry.getKey();
                try {
                    Field field = MyObjectUtils.getFieldByName(cls,filedName);
                    field.setAccessible(true);
                    if(entry.getValue().export())
                    {
                        buf.append("\"").append(MyStringUtils.isEmpty(String.valueOf(field.get(d))) ?
                                entry.getValue().value() :
                                String.valueOf(field.get(d))).append("\"");
                    }
                    buf.append(CSV_COLUMN_SEPARATOR);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            buf.deleteCharAt(buf.length());
            buf.append(CSV_ROW_SEPARATOR);
        });
        return buf.toString();
    }
}
