package ltd.liuzhi.rhyme.utils;


import java.io.OutputStream;
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
     * @param dataList 集合数据
     * @param titles   表头部数据
     * @param keys     表内容的键值
     * @param os       输出流
     */
    public static void doExport(List<Map<String, Object>> dataList, String titles, String keys, OutputStream os) throws Exception {
        StringBuffer buf = new StringBuffer();

        String[] titleArr = null;
        String[] keyArr = null;

        titleArr = titles.split(",");
        keyArr = keys.split(",");

        // 组装表头
        for (String title : titleArr) {
            buf.append(title).append(CSV_COLUMN_SEPARATOR);
        }
        buf.append(CSV_ROW_SEPARATOR);

        // 组装数据
        if (MyCollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> data : dataList) {
                for (String key : keyArr) {
                    buf.append(data.get(key)).append(CSV_COLUMN_SEPARATOR);
                }
                buf.append(CSV_ROW_SEPARATOR);
            }
        }
        // 写出响应
        os.write(buf.toString().getBytes("UTF-8"));
        os.flush();
    }
}
