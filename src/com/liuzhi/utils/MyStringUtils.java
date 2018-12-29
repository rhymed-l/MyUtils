package com.liuzhi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtils
{
    /**
     * 私有化构造器
     */
    private MyStringUtils(){}

    /**
     * 取文本所在位置
     * @param old 原文本
     * @param index 需要取的字符串
     * @return 文本所在位置,未找到则返回-1
     */
    public static Integer getIndex(String old, String index)
    {
        return old.indexOf(index);
    }

    /**
     * 取文本右边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本右边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextRight(String old,String index)
    {
        if(old.indexOf(index)==-1)
        {
            return old;
        }
        return old.substring(old.indexOf(index)+index.length());
    }

    /**
     * 取文本最右边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本右边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextRights(String old,String index)
    {
        if(old.lastIndexOf(index)==-1)
        {
            return old;
        }
        return old.substring(old.lastIndexOf(index)+index.length());
    }
    /**
     * 取文本左边
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本左边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextLeft(String old,String index)
    {
        if(old.indexOf(index)==-1)
        {
            return old;
        }
        return old.substring(0,old.indexOf(index));
    }

    /**
     * 取出文本中间
     * @param old 原文本
     * @param left 左边的字符
     * @param right 右边的字符
     * @return 返回左边和右边中间的字符
     */
    public static String getTextMiddle(String old,String left,String right)
    {
        String temp = getTextRight(old,left);
        return getTextLeft(temp,right);
    }
    /**
     * 取出文本中间加强(最右)
     * @param old 原文本
     * @param left 左边的字符
     * @param right 右边的字符
     * @return 返回左边和右边中间的字符
     */
    public static String getTextMiddles(String old,String left,String right)
    {
        String temp = getTextRights(old,left);
        return getTextLeft(temp,right);
    }
    /**
     * 判断文本是否是空(对象为空,或者内容为空)
     * @param text 需要判断的文本
     * @return 空则返回真
     */
    public static Boolean isEmpty(String text)
    {
        return text==null?true:text.trim().equals("")?true:false;
    }
    /**
     * 判断List组数是否是空(对象为空,或者内容为空)
     * @param list 需要判断的LIST数组
     * @return 空则返回真
     */
    public static Boolean isEmpty(List list)
    {
        return list==null?true:list.size()>0?false:true;
    }

    /**
     * 将String字符转为Integer类型
     * @param number 需要转换的数字
     * @return 返回转化之后的数字
     */
    public static Integer strCaseInt(String number)
    {
        return new Integer(number);
    }
    /**
     * 将String字符转为Long类型
     * @param number 需要转换的数字
     * @return 返回转化之后的数字
     */
    public static Long strCaseLong(String number)
    {
        return new Long(number);
    }

    /**
     * 将String字符转为Integer类型
     * @param number1 需要转换的数字
     * @param number2 需要相加的值
     * @return 返回转化之后的数字
     */
    public static Integer strAddCaseInt(String number1,Object number2)
    {
        return new Integer(number1) + new Integer(number2.toString());
    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str)
    {
        if(isEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 删除所有不可见字符
     * @param str 传入的字符串
     * @return 返回删除全部不可见字符
     */
    public static String deleteAllInvisibleCharacters(String str)
    {
        return str.replaceAll("\\s","");
    }

    /**
     * 对List数组去重(HashSet剔除)
     * @param list 传入的List
     */
    public static void listDistinct(List list)
    {
        HashSet set = new HashSet(list) ;
        list.clear()                    ;
        list.addAll(set)                ;
    }

    /**
     * String数组去重
     * @param strArray 传入的String数组
     * @return 返回去重后的String数组
     */
    public static String[] strArrayDistinct(String[] strArray)
    {
        List<String>    list        = Arrays.asList(strArray)   ;
        List<String>    arrayList   = new ArrayList(list)       ;
        listDistinct(arrayList)                                 ;
        return  arrayList.toArray(new String[arrayList.size()]) ;
    }

    /**
     * 邮箱地址验证
     * @param email 需要验证的邮箱地址
     * @return 验证成功返回true,失败返回false
     */
    public static boolean checkEmail(String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取短连接
     * @param longUrl 需要缩短的长链接
     * @return 返回缩短后的链接
     */
    public static String longUrlToShorUrl(String longUrl)
    {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "LiuZhi" ;
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"
        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (MD5Code32(key + longUrl));
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        //得到 4组短链接字符串
        for ( int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            //循环获得每组6位的字符串
            for ( int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                //(具体需要看chars数组的长度   以防下标溢出，注意起点为0)
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[( int ) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl[1];
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String MD5Code32(String str)  {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");

        md5.update((str).getBytes("UTF-8"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }


    /**
     * 给手机号中间打上*号,保留前面3位后面4位
     * @param phone 需要脱敏的手机号
     * @return 返回脱敏后的手机号码
     */
    public static String phoneMosaic(String phone)
    {
        return phoneMosaic(phone,null,null);
    }
    /**
     * 给手机号中间打上*号,指定保留前后位数
     * @param phone 需要脱敏的手机号
     * @return 返回脱敏后的手机号码
     */
    public static String phoneMosaic(String phone,Integer front,Integer back)
    {
        if(isEmpty(phone))
        {
            return null;
        }
        if(front == null || front<0)
        {
            front = 3;
        }
        if(back == null || back<0)
        {
            back = 4;
        }
        if(isInteger(phone))
        {
            int len = phone.length();
            String left = phone.substring(0,front);
            StringBuffer sb = new StringBuffer(left);
            for(int i=0;i<len-(front+back);i++)
            {
                sb.append("*");
            }
            sb.append(phone.substring(len-back));
            return sb.toString();
        }
        return phone;
    }

    /**
     * 给姓名中间打上*号,超过三位数则只显示第一位和最后一位
     * @param name 需要脱敏的姓名
     * @return 返回脱敏后的姓名
     */
    public static String nameMosaic(String name)
    {
        if(isEmpty(name))
        {
            return null;
        }
        int len = name.length();
        StringBuffer sb = new StringBuffer();
        sb.append(name.substring(0,1));//先拼第一位
        if(len>2)
        {
            for(int i=1;i<len-1;i++)
            {
                sb.append("*");
            }
            sb.append(name.substring(len-1));//拼最后一位
        }else
            {
                sb.append("*");
            }
        return sb.toString();
    }

    /**
     * 判断字符串是否是汉字
     * @param str 需要被判断的字符串
     * @return 返回真或假
     */
    public static boolean isChinese(String str)
    {
        if (str == null)
        {
            return false;
        }
        char[] cTemp = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (!isChinese(cTemp[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判定输入的是否是汉字
     * @param c 被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
