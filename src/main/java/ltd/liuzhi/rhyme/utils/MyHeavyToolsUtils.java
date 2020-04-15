package ltd.liuzhi.rhyme.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 当日去重工具
 * @author LiuZhi
 */
public class MyHeavyToolsUtils
{
    private final static Logger logger = Logger.getGlobal();

    private static StringBuffer sbUV    = new StringBuffer()            ;//聚道IP UV(H5页面打开的UV)
    private static String       pathUV  = new String()                  ;//打开的路径
    //加载数据
    static
    {
        try {
            String dirPath = Thread.currentThread().getContextClassLoader().getResource("")+"\\h5Statistics";
            dirPath         = dirPath.replaceAll("file:","");
            pathUV          = dirPath + "\\uv_"         ;

            File dir        = new File(dirPath)         ;
            Date date       = new Date()                ;//日期
            if(!dir.isDirectory())
            {
                logger.info("记录UV文件夹不存在,创建文件夹");
                dir.mkdirs();//如果文件夹不存在则创建文件
            }
            File file1 = new File(pathUV    +   MyDateUtils.dateToStr(date, MyDateUtils.MyDateFormatterEnum.TIME_FORMAT_TIME));
            if(!file1.exists())
            {
                logger.info("用户UV数据文件不存在,创建文件");
                file1.createNewFile();//如果文件不存在则创建文件
            }
            //读取用户打开UV
            BufferedReader br1   = new BufferedReader(new FileReader(pathUV    +   MyDateUtils.dateToStr(date, MyDateUtils.MyDateFormatterEnum.TIME_FORMAT_TIME)));
            int b1 ;
            while ((b1 = br1.read())!=-1)
            {
                sbUV.append((char)b1);
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置用户打开UV,如果存在返回真,否则添加后返回假
     */
    public static boolean heavy(String key1,String key2)
    {
        //删除昨日数据
        removeFile();
        StringBuffer tempSB = new StringBuffer();
        String key =tempSB.append(key1).append(":").append(key2).append(",").toString();
        if(sbUV == null)
        {
            sbUV = new StringBuffer();
        }
        if(sbUV.toString() == null || sbUV.toString().isEmpty())
        {
            sbUV.append(key);
        }else
        {
            if(sbUV.toString().contains(key))
            {
                return true;
            }
            sbUV.append(key);//拼成闭合
        }
        byte bt[] = sbUV.toString().getBytes();
        try {
            FileOutputStream in = new FileOutputStream(pathUV   +   MyDateUtils.dateToStr(new Date(), MyDateUtils.MyDateFormatterEnum.TIME_FORMAT_TIME));
            in.write(bt, 0, bt.length);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除本地文件并清空数据
     */
    public static void removeFile()
    {
            File file1 = new File(pathUV    +   MyDateUtils.dateToStr(MyDateUtils.getAssignTime(-1), MyDateUtils.MyDateFormatterEnum.TIME_FORMAT_TIME));
            if(file1.exists())
            {
                logger.info("删除昨日的UV数据文件");
                file1.delete();
                sbUV = null;
            }
    }
}
