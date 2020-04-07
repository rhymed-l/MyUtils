package ltd.liuzhi.rhyme.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

/**
 * DES 加解密工具
 */
public class MyEncryptionUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String EMPTY_STR = "";
    private static final int AES_KEY_SIZE = 16;//256/192/128~32/24/16
    private static final String ENCRYPT = "AES";
    private static final String CIPHER = "AES/CBC/PKCS5Padding";
    private static final String DES_ALGORITHM = "DES";
    public static final String DES_CIPHER_ALGORITHM = "DES";

    /**
     * AES加密
     *
     * @param key 加密密钥
     * @param src 加密内容
     * @return 返回BASE64密文
     */
    public static final String aesEncrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] bs = getAESResult(key, src.getBytes(DEFAULT_CHARSET), Cipher.ENCRYPT_MODE);
            if (bs != null) {
                return Base64.getEncoder().encodeToString(bs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * AES解密
     *
     * @param key 解密密钥
     * @param src 解密内容
     * @return 明文
     */
    public static final String aesDecrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] bs = getAESResult(key, Base64.getDecoder().decode(src.getBytes(DEFAULT_CHARSET)), Cipher.DECRYPT_MODE);
            if (bs != null) {
                return new String(bs, DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * AES加解密结果
     *
     * @param key         密钥
     * @param textBytes   明文 密文 字节数组
     * @param encryptMode 加密 解密
     * @return
     */
    private static byte[] getAESResult(String key, byte[] textBytes, final int encryptMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Key newKey = new SecretKeySpec(buildCLenKey(key, AES_KEY_SIZE), ENCRYPT);
        Cipher cipher = Cipher.getInstance(ENCRYPT);
        cipher.init(encryptMode, newKey, new SecureRandom());
        return cipher.doFinal(textBytes);
    }


    //定义加密算法，有DES、DESede(3DES)
    private static final String ALGORITHM = "DESede";
    // 算法名称/加密模式/填充方式
    private static final String CIPHER_ALGORITHM_ECB = "DESede/ECB/PKCS5Padding";

    /**
     * TripleDES加密方法
     *
     * @param src
     * @param key
     * @return BASE64
     */
    public static final String tripleDesEncrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] des = getTripleDESResult(key, src.getBytes(), Cipher.ENCRYPT_MODE);
            if (des != null) {
                return Base64.getEncoder().encodeToString(des);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * TripleDES解密函数
     *
     * @param src 密文的字节数组
     * @param key 密钥
     * @return String 明文
     */
    public static final String tripleDesDecrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] srcb = Base64.getDecoder().decode(src);
            byte[] des = getTripleDESResult(key, srcb, Cipher.DECRYPT_MODE);
            return new String(des, DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * TripleDES加解密结果
     *
     * @param key         密钥
     * @param textBytes   明文 密文 字节数组
     * @param encryptMode 加密 解密
     * @return
     */
    private static byte[] getTripleDESResult(String key, byte[] textBytes, final int encryptMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Key newKey = new SecretKeySpec(buildCLenKey(key, 24), ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(encryptMode, newKey, new SecureRandom());
        return cipher.doFinal(textBytes);
    }

    /**
     * 根据字符串生成密钥字节数组
     *
     * @param keyStr 密钥字符串
     * @param lgn    密钥长度
     * @return 长度密钥字节数组
     * @throws UnsupportedEncodingException
     */
    private static byte[] buildCLenKey(String keyStr, int lgn) throws UnsupportedEncodingException {
        byte[] key = new byte[lgn];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组

        ///执行数组拷贝
        if (key.length > temp.length) {
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }
    /**
     * DES 加密方法
     *
     * @param src
     * @param key
     * @return BASE64
     */
    public static final String desEncrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] des = getDESResult(key, src.getBytes(), Cipher.ENCRYPT_MODE);
            if (des != null) {
                return Base64.getEncoder().encodeToString(des);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * DES解密函数
     *
     * @param src 密文的字节数组
     * @param key 密钥
     * @return String 明文
     */
    public static final String desDecrypt(String src, String key) {
        if (key == null || src == null) {
            return EMPTY_STR;
        }
        try {
            byte[] srcb = Base64.getDecoder().decode(src);
            byte[] des = getDESResult(key, srcb, Cipher.DECRYPT_MODE);
            return new String(des, DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return src;
    }

    private static byte[] getDESResult(String key, byte[] textBytes, final int encryptMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Key newKey = new SecretKeySpec(buildCLenKey(key, 8), DES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(DES_CIPHER_ALGORITHM);
        cipher.init(encryptMode, newKey, new SecureRandom());
        return cipher.doFinal(textBytes);
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
        {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * BASE64加密
     * @param data 需要加密的数据
     * @return 加密后的数据
     */
    public static String base64Encoder(String data)
    {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.getBytes());
    }

    /**
     * BASE64解密
     * @param data 需要解密的数据
     * @return 解密后的数据
     */
    public static String base64Decoder(String data)
    {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(data);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
