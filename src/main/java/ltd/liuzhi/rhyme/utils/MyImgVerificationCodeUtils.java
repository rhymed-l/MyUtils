package ltd.liuzhi.rhyme.utils;

import ltd.liuzhi.rhyme.utils.pojo.domain.ImgCodeDO;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * 图片验证码工具类
 */
public class MyImgVerificationCodeUtils
{
    // 图片的宽度。
    private static int width = 110;
    // 图片的高度。
    private static int height = 40;
    // 验证码字符个数
    private static int codeCount = 4;
    // 验证码干扰线数
    private static int lineCount = 50;

    private static Map<String,CodeDO> map = new HashMap<>();

    private static char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    // 生成随机数
    private static Random random = new Random();
    //默认5分钟过期
    private static int expirationTime = 5*60;

    /**
     * 设置过期时间(秒)
     */
    public static void setExpirationTime(int expirationTime) {
        MyImgVerificationCodeUtils.expirationTime = expirationTime;
    }
    static
    {
        Timer timer = new Timer();
        CodeTask codeTask = new CodeTask();
        //每分钟一次
        timer.schedule(codeTask,new Date(),60*1000L);
    }

    /**
     * @param imgCodeDO 需要验证的验证码对象
     * @return null 不存在或者过期或者参数丢失,true 验证成功 false 验证失败
     */
    public static Boolean verification(ImgCodeDO imgCodeDO)
    {
        if(imgCodeDO.getId()==null || imgCodeDO.getCode() == null)
        {
            return null;
        }
        CodeDO codeDO = map.get(imgCodeDO.getId());
        if(codeDO == null)
        {
            return null;
        }
        if(MyDateUtils.differentSecondByMillisecond(codeDO.getCreateTime(),new Date())> expirationTime)
        {
            map.remove(imgCodeDO.getId());
            return null;
        }
        Boolean b = codeDO.getCode().trim().equalsIgnoreCase(imgCodeDO.getCode().trim());
        //校验成功删除KEY
        if(b)
        {
            map.remove(imgCodeDO.getId());
        }
        return b;
    }

    /**
     * @param id 验证码ID
     * @param code 验证码
     * @return null 不存在或者过期或者参数丢失,true 验证成功 false 验证失败
     */
    public static Boolean verification(String id,String code)
    {
        return verification(new ImgCodeDO(id,code));
    }

    public static ImgCodeDO createImgCodeToBase64()
    {
        ImgCodeDO imgCodeDO = new ImgCodeDO();
        int codeX ;
        int fontHeight;
        fontHeight = height - 5;// 字体的高度
        codeX = width / (codeCount + 3);// 每个字符的宽度

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);

        StringBuffer randomCode = new StringBuffer();
        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
            g.drawString(strRand, (i + 1) * codeX, getRandomNumber(height / 2) + 25);
            randomCode.append(strRand);
        }
        String code = randomCode.toString();
        imgCodeDO.setCode(code);
        imgCodeDO.setId(UUID.randomUUID().toString().replaceAll("-",""));
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "JPEG", baos);//图片格式
            byte[] bytes = baos.toByteArray();
            String data = "data:image/png;base64,"+ new BASE64Encoder().encodeBuffer(bytes).trim();
            data.replaceAll("\\s","");
            imgCodeDO.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存到Map中
        CodeDO codeDO = new CodeDO(imgCodeDO.getId(),imgCodeDO.getCode());

        map.put(imgCodeDO.getId(),codeDO);
        return imgCodeDO;
    }

    /** 获取随机颜色 */
    private static Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    /** 获取随机数 */
    private static int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public static void overdueCleared()
    {
        Set<Map.Entry<String,CodeDO>> set = map.entrySet();
        Iterator<Map.Entry<String,CodeDO>> iterator = set.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String,CodeDO> entry = iterator.next();
            Date createTime = entry.getValue().getCreateTime();
            if(MyDateUtils.differentSecondByMillisecond(createTime,new Date())> expirationTime)
            {//删除键
                map.remove(entry.getKey());
            }
        }
    }
}


class ImgFontByte {
    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.getFont("TimesRoman");
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }
}

class CodeTask extends TimerTask
{
    public void run()
    {
        MyImgVerificationCodeUtils.overdueCleared();
    }
}

class CodeDO{
    private String id;
    private String code;
    private Date createTime;

    public CodeDO(String id, String code) {
        this.id = id;
        this.code = code;
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}


