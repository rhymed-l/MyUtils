package cn.rhyme.utils;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片工具类
 * @author LiuZhi
 */
public class MyImageUtils
{
    private MyImageUtils(){}

    /**
     * 将图片BASE64字符串转为二进制数组
     *
     * @param base64 new Image();img.src或canvas.toDataURL("image/png")
     * @return
     * @throws IOException
     */
    public static byte[] imgBase64ToBytes(String base64) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        //因为参数base64来源不一样，需要将附件数据替换清空掉。如果此入参来自canvas.toDataURL("image/png");
        base64 = base64.replaceAll("data:image/png;base64,", "");
        //base64解码并转为二进制数组
        byte[] bytes = null;
        try {
            bytes = decoder.decodeBuffer(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据  
                    bytes[i] += 256;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 图片字节数组转base64
     *
     * @return
     */
    public static String bytesToImgBase64(byte[] bytes) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * 图片/文件转二进制数组，这个方法有很多，只写一个
     *
     * @param imgPath 图片路径
     * @return
     */
    public static byte[] imgToBytes(String imgPath) {
        File file = new File(imgPath);
        BufferedImage bi = null;
        ByteArrayOutputStream baos = null;
        try {
            //文件使用其他工具
            bi = ImageIO.read(file);
            baos = new ByteArrayOutputStream();
            int index = imgPath.lastIndexOf(".");
            String format = imgPath.substring(index + 1);
            ImageIO.write(bi, format, baos);
            byte[] bytes = baos.toByteArray();
            baos.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换为图片
     *
     * @param savePath 将图片输出到哪里
     */
    public static void bytesToImg(byte[] bytes, String savePath) {
        ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
        try {
            BufferedImage bi = ImageIO.read(baos);
            File file = new File(savePath);
            ImageIO.write(bi, "png", file);
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 图片格式转换
     */
    public static File imgFormatConvert(String imgPath, String formatName) {
        return imgFormatConvert(new File(imgPath), formatName);
    }

    /**
     * 图片格式转换
     */
    public static File imgFormatConvert(File file, String formatName) {
        MyFileUtils.checkFileExists(file);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(bi, formatName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 去除图片背景,转透明图
     *
     * @param is
     * @return
     */
    public static byte[] toTransparency(InputStream is) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage bi = ImageIO.read(is);
            java.awt.Image image = (java.awt.Image) bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);

                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);// 直接输出文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 检查是否为图片
     *
     * @return 如果是则返回真, 如果不是则返回假
     */
    public static Boolean imageCheck(File file) {
        MyFileUtils.checkFileExists(file);
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static boolean changeSize(File file, int newWidth, int newHeight) {
        MyFileUtils.checkFileExists(file);
        try {
            //读取图片
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 改变图片的尺寸
     *
     * @param path      文件路径
     * @param newWidth  需要设置的宽度
     * @param newHeight 需要设置的高度
     * @return boolean
     */
    public static boolean changeSize(String path, int newWidth, int newHeight) {
        return changeSize(new File(path), newWidth, newHeight);
    }
    /**
     * 改变图片的尺寸
     * @param path      文件路径
     * @param newWidth  需要设置的宽度
     * @param newHeight 需要设置的高度
     * @return boolean
     */
    public static File changeSizeToNewFile(String path, int newWidth, int newHeight) {
        return changeSizeToNewFile(new File(path), newWidth, newHeight);
    }
    /**
     * 改变图片的尺寸
     *
     * @param newWidth, newHeight, path
     * @return boolean
     */
    public static File changeSizeToNewFile(File file, int newWidth, int newHeight) {
        MyFileUtils.checkFileExists(file);
        File newFile = new File(MyStringUtils.getRandom(6)+"."+MyFileUtils.getFilePostfix(file));
        try {
            //读取图片
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
            ImageIO.write(tag, "PNG", out);
            in.close();
            out.close();
            return newFile;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
