package ltd.liuzhi.rhyme.utils;

import ltd.liuzhi.rhyme.utils.pojo.domain.ImgCodeDO;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图片验证码工具类
 */
public class MyImgVerificationCodeUtils
{
    // 图片的宽度。
    private static int width = 80;
    // 图片的高度。
    private static int height = 45;
    // 验证码字符个数
    private static int codeCount = 4;
    // 验证码干扰线数
    private static int lineCount = 10;

    private static Map<String,CodeDO> map = new ConcurrentHashMap<>();

    private static char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    // 生成随机数
    private static Random random = new Random();
    //默认5分钟过期
    private static int expirationTime = 5 * 60;

    /**
     * 设置过期时间(秒)
     */
    public static void setExpirationTime(int expirationTime) {
        MyImgVerificationCodeUtils.expirationTime = expirationTime;
    }
    static
    {
        //每分钟一次
        MyScheduleUtils.newTaskByMinutes(()-> MyImgVerificationCodeUtils.overdueCleared(),1,true);
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

    /**
     * 创建一个图形验证码对象
     * @return 返回验证码对象
     */
    public static ImgCodeDO createImgCodeToBase64()
    {
        Random rand = new Random();

        ImgCodeDO imgCodeDO = new ImgCodeDO();
        int codeX ;
        int fontHeight;
        fontHeight = height - 5;// 字体的高度
        codeX = width / (codeCount + 3);// 每个字符的宽度

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for(int i = 0; i < colors.length; i++){
            colors[i] = colorSpaces[getRandomNumber(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g.setColor(Color.GRAY);// 设置边框色
        g.fillRect(0, 0, width,height);

        Color c = getRandomColor(200, 250);
        g.setColor(c);// 设置背景色
        g.fillRect(0, 2, width, height-4);
        // 设置干扰线
        drawRandomLines(g,lineCount);
        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomNumber(255);
            buffImg.setRGB(x, y, rgb);
        }
        // 使图片扭曲
        shear(g, width, height, c);
        // 创建字体
        g.setColor(getRandomColor(100,160));
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight-2);
        g.setFont(font);
        StringBuffer randomCode = new StringBuffer();
        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
//            // 设置字体颜色
//            g.setColor(getRandomColor());
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

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }
    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    /**
     * 绘制干扰线
     * @param g Graphics2D对象，用来绘制图像
     * @param nums  干扰线的条数
     */
    private static void drawRandomLines(Graphics2D g, int nums) {
        Random random = new Random();
        g.setColor(getRandomColor(160,200));// 设置线条的颜色
        for (int i = 0; i < nums; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
    }

    /** 获取随机颜色 */
    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255)
        {
            fc = 255;
        }
        if (bc > 255)
        {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
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

    public static void main(String[] args) {
        System.err.println(MyImgVerificationCodeUtils.createImgCodeToBase64());
    }
}


