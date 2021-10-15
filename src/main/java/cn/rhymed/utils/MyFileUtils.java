package cn.rhymed.utils;

import cn.rhymed.utils.enums.FileTypeEnum;

import java.io.*;

/**
 * 文件工具类
 * @author LiuZhi
 */
public class MyFileUtils {
    private MyFileUtils() {
    }


    /**
     * 根据文件路径获取文件真实类型后缀
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getRealFilePostfix(String filePath) {
        if (filePath == null) {
            return "";
        }
        File file = new File(filePath);
        return getRealFilePostfix(file);
    }

    /**
     * 根据文件获取文件真实类型后缀
     * @param inputStream 文件输入流
     * @return 文件头信息
     */
    public static String getRealFilePostfix(InputStream inputStream) {
        if(inputStream == null){
            throw new RuntimeException("文件输入流不存在资源");
        }
        String value = "";
        try {
            byte[] b = new byte[20];
            inputStream.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return FileTypeEnum.getPostfixByHex(value);
    }

    /**
     * 根据文件获取文件真实类型后缀
     *
     * @param file 文件
     * @return 文件头信息
     */
    public static String getRealFilePostfix(File file) {
        MyFileUtils.checkFileExists(file);
        FileInputStream is = null;
        String value = "";
        try {
            is = new FileInputStream(file);
            byte[] b = new byte[20];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return FileTypeEnum.getPostfixByHex(value);
    }

    /**
     * 根据文件路径获取文件类型后缀
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFilePostfix(String filePath) {
        if (filePath == null) {
            return "";
        }
        return MyStringUtils.getTextRights(filePath, ".");
    }

    /**
     * 根据文件获取文件类型后缀
     *
     * @param file 文件
     * @return 文件头信息
     */
    public static String getFilePostfix(File file) {
        if (file == null) {
            return "";
        }
        return MyStringUtils.getTextRights(file.getName(), ".");
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头十六进制信息
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        System.out.println("HexString: " + builder.toString());
        return builder.toString();
    }

    public static void checkFileExists(File file) {
        if (!file.exists()) {
            throw new RuntimeException("被读取的文件不存在");
        }
    }

    /**
     * 文件名修改
     * @param file    需要修改的文件
     * @param newName 修改文件名
     * @return
     */
    public static File fileRename(File file, String newName) {
        checkFileExists(file);
        String postfix = "." + getRealFilePostfix(file);
        String oldPath = file.getParent();
        oldPath = MyStringUtils.isEmpty(oldPath) == true ? "" : oldPath + File.separator;
        File newFile = new File(oldPath + MyStringUtils.getTextLefts(newName, ".") + postfix);
        file.renameTo(newFile);
        return newFile;
    }

    /**
     * 文件转字节数组
     * @param file 需要转换的文件
     * @return 返回字节数组
     */
    public static byte[] getBytesByFile(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
        byte[] b = new byte[1000];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        byte[] data = bos.toByteArray();
        bos.close();
        return data;
    }

    /**
     * 创建文件夹,如果文件夹存在则不创建
     * @param path 文件路径
     * @return 创建成功则返回真
     */
    public static boolean createFileFolder(String path)
    {
        File fileFolder = new File(path);
        //如果文件夹存在则直接返回
        if(fileFolder.isDirectory())
        {
            return true;
        }
            return fileFolder.mkdirs();
    }

    /**
     * 将输入流转为字节数组
     * @param inStream 输入流
     * @return 返回转换后的字节数组
     */
    public static byte[] inputToByte(InputStream inStream)
    {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while (true) {
            try {
                if (!((rc = inStream.read(buff, 0, 100)) > 0)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 将字节数组转为输入流
     * @param bytes 字节数组
     * @return 返回转换后的输入流
     */
    public static InputStream ByteToInputStream(byte[] bytes)
    {
        return new ByteArrayInputStream(bytes);
    }


}