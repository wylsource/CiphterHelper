package com.agile.cipher.helper;

import com.agile.cipher.constant.AlgorithmConstant;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: WuYL
 * @Description: MD5 加密算法
 * @Date: Create in 2018/4/4 15:12
 * @Modified By:
 */
public class Md5Helper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Helper.class);

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private MessageDigest messageDigest;

    public Md5Helper(){
        try {
            this.messageDigest = MessageDigest.getInstance(AlgorithmConstant.MD5_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Init md5 messageDigest is failed", e);
        }
    }

    /**
     * MD5 加密
     * @param content 加密的内容
     * @return 返回加密后的串
     */
    public String encryptStr(String content, String salt, String charset){
        try {
            byte[] bytes = content.getBytes(charset);
            byte[] bytes2 = salt.getBytes(charset);
            return encryptByte(bytes, bytes2);
        }catch (Exception e){
            LOGGER.error("string to byte[] is failed", e);
        }
        return null;
    }

    /**
     * MD5 加密 （默认是 UTF-8 编码）
     * @param content 加密的内容
     * @return 返回加密后的串
     */
    public String encryptStr(String content, String salt){
        return this.encryptStr(content, salt, "UTF-8");
    }
    /**
     * MD5 加密 （默认是 UTF-8 编码）
     * @param content 加密的内容
     * @return 返回加密后的串
     */
    public String encryptStr(String content){
        return this.encryptStr(content, null);
    }

    /**
     * MD5 加密
     * @param content 加密的内容
     * @return 返回加密后的串
     */
    public String encryptByte(byte[] content){
        return this.encryptByte(content, null);
    }

    /**
     * MD5 加密
     * @param content 加密的内容
     * @return 返回加密后的串
     */
    public String encryptByte(byte[] content, byte[] salt){
        try {
            if (salt != null && salt.length > 0) {
                content = addBytes(content, salt);
            }
            byte[] digest = messageDigest.digest(content);
            return bufferToHex(digest, 0, digest.length);
        }catch (Exception e){
            LOGGER.error("md5 encrypt is failed", e);
        }
        return null;
    }


    /**
     * 对文件进行加密
     * @param file
     * @return
     */
    public String encryptFile(File file){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
            return encryptByte(content);
        }catch (Exception e){
            LOGGER.error("encrypt file is failed", e);
        }
        return null;
    }

    /**
     * 对文件进行加密
     * @param file
     * @param salt
     * @return
     */
    public String encryptFile(File file, String salt){
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
            byte[] bytes2 = salt.getBytes();
            return encryptByte(content, bytes2);
        }catch (Exception e){
            LOGGER.error("encrypt file is failed", e);
        }
        return null;
    }

    /**
     * 对 byte 数组 加盐处理
     * @param byte1 原数组
     * @param byte2 盐数组
     * @return 返回加盐后的数组
     */
    public static byte[] addBytes(byte[] byte1, byte[] byte2){
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        return byte3;
    }

    /**
     * byte 数组转 16 进制字符串
     * @param bytes 原始数组
     * @param m 从哪个位置开始转换
     * @param n 数组长度
     * @return 返回 16 进制的字符串
     */
    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * byte 转换为对应的 16 进制字符
     * @param bt byte 字节
     * @param stringbuffer 用于存储字符
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        // 取字节中高 4 位的数字转换
        // >>>为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        // 取字节中低 4 位的数字转换
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
