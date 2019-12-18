package ltd.liuzhi.rhyme.utils;

import main.java.ltd.liuzhi.rhyme.utils.type.FileTypeEnum;

import java.io.*;

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
}