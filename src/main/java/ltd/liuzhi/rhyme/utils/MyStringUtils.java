package ltd.liuzhi.rhyme.utils;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
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
     * 取文本左边(倒找)
     * @param old 原文本
     * @param index 需要定位的字符串
     * @return 文本左边的文本,如找不到指定的截取的文本则返回原文本
     */
    public static String getTextLefts(String old,String index)
    {
        if(old.lastIndexOf(index)==-1)
        {
            return old;
        }
        return old.substring(0,old.lastIndexOf(index));
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
        String temp = getTextRight(old,left);
        return getTextLefts(temp,right);
    }
    /**
     * 判断文本是否是非空(对象为空,或者内容为空)
     * @param text 需要判断的文本
     * @return 非空则返回真
     */
    public static Boolean isNotEmpty(String text)
    {
        return !isEmpty(text);
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
     * 截取字符串
     * @param length 需要截取多长
     * @return 返回所截取长度的字符串
     */
    public static String subString(String str,int length)
    {
        return subString(str,0,length);
    }
    
    /**
     * 截取字符串
     * @param beginIndex 从那个位置开始
     * @param endIndex 从那个位置结束
     * @return 返回所截取长度的字符串
     */
    public static String subString(String str,int beginIndex,int endIndex)
    {
        return str.substring(beginIndex,endIndex>str.length()?str.length():endIndex);
    }

    /**
     * 判断是否为数字
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isDouble(String str)
    {
        if(isEmpty(str))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*\\.?[\\d]+$");
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
     * 判断字符串是否包含汉字
     * @param str 需要被判断的字符串
     * @return 返回真或假
     */
    public static boolean includeChinese(String str)
    {
        if (str == null)
        {
            return false;
        }
        char[] cTemp = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (isChinese(cTemp[i])) {
                return true;
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


    /**
     * 将String类型的Url参数转为Map类型的参数
     * @param url 需要转换的链接地址
     * @return 将参数用Map形式返回
     */
    public static Map<String,String> getUrlParam(String url)
    {
        Map<String,String> map = new HashMap<>();
        String[] strings = url.split("&");
        for (String s : strings)
        {
            String[] params = s.split("=");

            for ( int i = 0;i<params.length;i++)
            {
                map.put(params[i],params[++i]);
            }
        }
        return map;
    }

    /**
     * 生成随机UUID
     * @return 返回唯一的UUID
     */
    public static String getRandom(int length)
    {
        return getRandom(length,null);
    }
    /**
     * 生成随机UUID
     * @return 返回唯一的UUID
     */
    public static String getRandom(Integer length,Boolean isUpperCase)
    {
        String uuid = UUID.randomUUID().toString();
        if(length != null && length >0)
        {
            uuid = uuid.substring(0,length);
        }
        if(isUpperCase !=null && isUpperCase == false)
        {
            uuid = uuid.toLowerCase();
        }
        return uuid;
    }

    /**
     * 判断是否为身份证号码
     * @param IDNumber 身份证号
     * @return 真或假
     */
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);
        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                                "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }

    /**
     * 从字符串中提取数字
     * @param str 需要被提取的字符串
     * @return 返回提取后的数字
     */
    public static Integer strExtractNumber(String str)
    {
        String regex = "\\D";
        return Integer.valueOf(str.replaceAll(regex,""));
    }
    /**
     * 将字符串的数字填补为指定长度,若不够则向前填补0,若够,则截取为指定长度
     * @param str 需要被补充的的字符串
     * @return 返回补充后的字符串
     */
    public static String strZeroize(String str,int places)
    {
        StringBuffer sb = new StringBuffer();
        for(int i=-1;i<places-str.length();i++)
        {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString().substring(sb.length()-places);
    }

    /**
     * 获取文本有多少行
     * @param str 需要判断的文本
     * @return 返回文本行数
     */
    public static int getTextLineNum(String str)
    {
        if(MyStringUtils.isEmpty(str))
        {
            return 0;
        }
        StringReader stringReader = new StringReader(str);
        LineNumberReader lineNumberReader = new LineNumberReader(stringReader);
        try {
            lineNumberReader.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lines = lineNumberReader.getLineNumber() + 1;
        stringReader.close();
        try {
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 获取某行文本
     * @param str 被选择的的文本
     * @param line 某一行文本
     * @return 返回指定行的文本
     */
    public static String getTextByLine(String str,int line)
    {
        if(MyStringUtils.isEmpty(str))
        {
            return null;
        }
        StringReader stringReader = new StringReader(str);
        LineNumberReader lineNumberReader = new LineNumberReader(stringReader);
        String lineText = null;
        try {
            for(int i=0;i<line;i++)
            {
                lineText = lineNumberReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stringReader.close();
        try {
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineText;
    }

    /**
     * 文本缩进去除多余空格
     * @param str 需要缩进的文本
     * @return 返回缩进后的文本
     */
    public static String textRetract(String str)
    {
        if(MyStringUtils.isEmpty(str))
        {
            return null;
        }
        //先删除首尾空格
        str = str.trim();
        //先将制表符替换为空格,在循环替换两个空格为一个空格
        str = str.replaceAll("\t","  ");
        while(str.indexOf("  ") > 0)
        {
            str =  str.replaceAll("  "," ");
        }
        return str;
    }

    /**
     * 获取指定字符串出现的次数(正则表达式)
     * @param srcText 源字符串
     * @param findText 要查找的字符串
     * @return 出现的次数
     */
    public static int appearNumberRe(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 获取指定字符串出现的次数
     * @param srcText 源字符串
     * @param findText 要查找的字符串
     * @return 出现的次数
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }

    /**
     * 将字符串分隔为List集合
     * @param str 原始字符串
     * @param regex 正则表达式
     * @return 返回分割后的字符串集合
     */
    public static List<String> splitToList(@NotNull String str,@NotNull String regex){
        return Arrays.asList(str.split(regex));
    }
}
