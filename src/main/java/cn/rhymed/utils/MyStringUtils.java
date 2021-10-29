package cn.rhymed.utils;


import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
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
        if(str == null)
        {
            return null;
        }
        return str.replaceAll("\\s","");
    }


    /**
     * 判断两个字符串是否相等
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 如果字符串相等返回true,否则false
     */
    public static boolean compare(String s1,String s2)
    {
        if(s1 == s2){
            return true;
        }
        if(s1 == null || s2 == null){
            return false;
        }
        return s1.equalsIgnoreCase(s2);
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
     * 给手机号中间打上*号,保留前面3位后面4位
     * @param phone 需要脱敏的手机号
     * @return 返回脱敏后的手机号码
     */
    public static String phoneMosaic(String phone)
    {
        return stringMosaic(phone,null,null);
    }

    /**
     * 给字符串中间打上*号,指定保留前后位数
     * @param str 需要脱敏的字符串
     * @param front 保留前面多少位
     * @param back 保留后面多少位
     * @return 返回脱敏后的手机号码
     */
    public static String stringMosaic(String str,Integer front,Integer back)
    {
        if(isEmpty(str))
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
        int len = str.length();
        String left = str.substring(0,front);
        StringBuffer sb = new StringBuffer(left);
        for(int i = 0; i < len- (front+back) ;i++)
        {
            sb.append("*");
        }
        sb.append(str.substring(len - back));
        return sb.toString();
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
        sb.append(name.substring(0,1));
        if(len>2)
        {
            for(int i=1;i<len-1;i++)
            {
                sb.append("*");
            }
            sb.append(name.substring(len-1));
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
     * 下划线转换驼峰命名法
     * @param str 需要转换的字符串
     * @return 返回转换的字符串
     */
    public static String lineToHump(String str){
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰命名转下划线
     * @param str 需要转换的字符串
     * @return 返回转换的字符串
     */
    public static String humpToLine(String str){
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 给身份证脱敏
     * @param idCard 身份证号
     * @return 返回脱敏后的身份证号
     */
    public static String idCardMosaic(String idCard) {
        return stringMosaic(idCard,6,4);
    }

    /**
     * 给邮箱脱敏
     * @param email 邮箱号
     * @return 返回脱敏后的邮箱号
     */
    public static String emailMosaic(String email) {
        int index = email.indexOf("@");
        if(index == -1){
            return email;
        }
        int back = email.length()-index;
        return stringMosaic(email,index/2,back);
    }

    /**
     * 判断字符串是否是英文
     * @param str 需要被判断的字符串
     * @return 返回真或假
     */
    public static boolean isEnglish(String str)
    {
        if (str == null)
        {
            return false;
        }
        char[] cTemp = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (!isEnglish(cTemp[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判定输入的是否是英文
     * @param c 被校验的字符
     * @return true代表是英文
     */
    public static boolean isEnglish(char c) {
        //A-Z 65-90
        //a-z 97-122
        if(isLowerCase(c) || isUpperCase(c)){
            return true;
        }
        return false;
    }

    /**
     * 判定输入的是否是小写
     * @param c 被校验的字符
     * @return true代表是英文
     */
    public static boolean isLowerCase(char c) {
        if(c >= 97 && c <= 122){
            return true;
        }
        return false;
    }

    /**
     * 判定输入的是否是大写
     * @param c 被校验的字符
     * @return true代表是英文
     */
    public static boolean isUpperCase(char c) {
        if(c >= 65 && c <= 90){
            return true;
        }
        return false;
    }


    /**
     * 首字母小写
     * @param str 字符串
     * @return 如果是英文则将首字母小写
     */
    public static String firstLowerCase(String str){
        char[] chars = str.toCharArray();
        //首字母小写方法，大写会变成小写，如果小写首字母会消失
        if(isUpperCase(chars[0])){
            chars[0] +=32;
        }
        return String.valueOf(chars);
    }

    /**
     * 首字母大写
     * @param str 字符串
     * @return 如果是英文则将首字母小写
     */
    public static String firstUpperCase(String str){
        char[] chars = str.toCharArray();
        //首字母小写方法，大写会变成小写，如果小写首字母会消失
        if(isLowerCase(chars[0])){
            chars[0] -=32;
        }
        return String.valueOf(chars);
    }
}
