package com.agile.cipher.helper;

import com.agile.cipher.joggle.CipherMode;
import com.agile.cipher.joggle.impl.AesCipherModeEcb;
import com.agile.cipher.util.SecretKeyUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;

/**
 * @Author: WuYL
 * @Description: AES 对称加密算法操作助手类
 * @Date: Create in 2018/4/3 10:58
 * @Modified By:
 */
public class AesHelper implements Helper{

    /**
     * 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AesHelper.class);

    /**
     * 加密算法
     */
    private CipherMode cipherMode;


    /**
     * 使用默认的加密算法
     */
    public AesHelper(){
        cipherMode = new AesCipherModeEcb();
    }

    /**
     * 自己指定加密算法
     * @param //cipherAlgorithm AES 加密算法
     */
    public AesHelper(CipherMode cipherMode){
        this.cipherMode = cipherMode;
    }


    /**
     * AES 加密
     * @param content 加密的内容
     * @param password 密码
     * @param charset 编码格式
     * @return 返回 byte 数组
     */
    @Override
    public byte[] encryptByte(String content, String password, String charset) {
        password = SecretKeyUtil.initPassword(password, 16);
        try {
            byte[] byteContent = content.getBytes(charset);
            Cipher cipher = cipherMode.initEncrypt(password);
            return cipher.doFinal(byteContent);
        } catch (Exception ex) {
            LOGGER.error("aes encrypt is failed", ex);
        }
        return null;
    }

    /**
     * AES 加密（默认是UTF-8编码）
     * @param content 加密的内容
     * @param password 密码
     * @return 返回 byte 数组
     */
    @Override
    public byte[] encryptByte(String content, String password) {
        return this.encryptByte(content, password, "UTF-8");
    }

    /**
     * AES 加密(默认是 UTF-8)
     * @param content 加密的内容
     * @param password 密码
     * @return 返回 base64 字符串
     */
    @Override
    public String encryptStr(String content, String password){
        //加密
        byte[] result = encryptByte(content, password);
        if (result != null){
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        }
        return null;
    }

    /**
     * AES 加密
     * @param content 加密的内容
     * @param password 密码
     * @param charset 编码格式
     * @return 返回 base64 字符串
     */
    @Override
    public String encryptStr(String content, String password, String charset){
        //加密
        byte[] result = encryptByte(content, password, charset);
        if (result != null){
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        }
        return null;
    }

    /* ---------------------------- 解密操作部分 --------------------------------- */

    /**
     * AES 解密操作（默认 UTF-8）
     * @param content 解密的内容
     * @param password 密码 --->需要与加密密码一致
     * @return
     */
    @Override
    public String decryptStr(String content, String password) {
        byte[] bytes = Base64.decodeBase64(content);
        return this.decryptByte(bytes, password);
    }

    /**
     * AES 解密操作（默认 UTF-8）
     * @param content 解密的内容
     * @param password 密码 --->需要与加密密码一致
     * @param charset 编码格式
     * @return
     */
    @Override
    public String decryptStr(String content, String password, String charset) {
        byte[] bytes = Base64.decodeBase64(content);
        return this.decryptByte(bytes, password, charset);
    }

    /**
     * AES 解密
     * @param content 解密的内容
     * @param password 密码 --->需要与加密密码一致
     * @param charset 编码格式
     * @return 返回解密的字符串
     */
    @Override
    public String decryptByte(byte[] content, String password, String charset){
        password = SecretKeyUtil.initPassword(password, 16);
        try {
            Cipher cipher = cipherMode.initDecrypt(password);
            //解密
            byte[] result = cipher.doFinal(content);
            return new String(result, charset);
        } catch (Exception ex) {
            LOGGER.error("aes decrypt is failed", ex);
        }
        return null;
    }

    /**
     * AES 解密（默认 UTF-8）
     * @param content 解密的内容
     * @param password 密码 --->需要与加密密码一致
     * @return 返回解密的字符串
     */
    @Override
    public String decryptByte(byte[] content, String password){
        return this.decryptByte(content, password, "UTF-8");
    }

}
