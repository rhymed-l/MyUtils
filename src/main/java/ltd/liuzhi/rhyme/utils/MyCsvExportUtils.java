package ltd.liuzhi.rhyme.utils;


import ltd.liuzhi.rhyme.utils.annotation.MyCSVField;

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
     * @param data 集合数据
     */
    public static void doExport(List data,OutputStream os)
    {
        Map<String,MyCSVField> fieldMap = new LinkedHashMap<>();
        if(MyCollectionUtils.getSize(data) == 0)
        {
            return;
        }
        //先获取第一个对象进行匹配标题
        Object object = data.get(0);
        //下个版本再考虑父类
        Field[] fields = object.getClass().getDeclaredFields();
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
            buf.append(e.getValue().title()).append(CSV_COLUMN_SEPARATOR));
        buf.append(CSV_ROW_SEPARATOR);
        // 组装数据
        data.forEach(d->{
            Class cls = d.getClass();
            fieldMap.entrySet().forEach(entry->{
                String filedName = entry.getKey();
                try {
                    Field field = cls.getDeclaredField(filedName);
                    field.setAccessible(true);
                    if(entry.getValue().export())
                    {
                        buf.append(MyStringUtils.isEmpty(String.valueOf(field.get(d))) ? entry.getValue().value() : field.get(d));
                    }
                    buf.append(CSV_COLUMN_SEPARATOR);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            buf.append(CSV_ROW_SEPARATOR);
        });
        System.err.println(buf.toString());
        // 写出响应
        try {
            os.write(buf.toString().getBytes("UTF-8"));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
