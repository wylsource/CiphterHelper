package com.agile.cipher.helper;


import com.agile.cipher.joggle.CipherMode;
import com.agile.cipher.joggle.impl.DesCipherModeEcb;
import com.agile.cipher.util.SecretKeyUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.krb5.internal.crypto.Des;

import javax.crypto.Cipher;

/**
 * @Author: WuYL
 * @Description: DES 加密算法
 * @Date: Create in 2018/4/3 15:19
 * @Modified By:
 */
public class DesHelper implements Helper{

    private static final Logger LOGGER = LoggerFactory.getLogger(DesHelper.class);

    private CipherMode cipherMode;

    public DesHelper(){
        this.cipherMode = new DesCipherModeEcb();
    }

    public DesHelper(CipherMode cipherMode){
        this.cipherMode = cipherMode;
    }

    /**
     * DES 加密
     * @param content 加密的内容
     * @param password 密码
     * @param charset 编码格式
     * @return 返回 byte 数组
     */
    @Override
    public byte[] encryptByte(String content, String password, String charset){
        password = SecretKeyUtil.initPassword(password, 8);
        try {
            byte[] contentBytes = content.getBytes(charset);
            Cipher cipher = cipherMode.initEncrypt(password);
            return cipher.doFinal(contentBytes);
        }catch (Exception e){
            LOGGER.error("des encrypt is failed!", e);
        }
        return null;
    }

    /**
     * DES 加密
     * @param content 加密的内容
     * @param password 密码
     * @return 返回 byte 数组
     */
    @Override
    public byte[] encryptByte(String content, String password){
        return encryptByte(content, password, "UTF-8");
    }

    /**
     * DES 加密
     * @param content 加密的内容
     * @param password 密码
     * @param charset 编码格式
     * @return 返回的的 String
     */
    @Override
    public String encryptStr(String content, String password, String charset){
        byte[] bytes = this.encryptByte(content, password, charset);
        if (bytes != null){
            return Base64.encodeBase64String(bytes);
        }
        return null;
    }

    /**
     * DES 加密
     * @param content 加密的内容
     * @param password 密码
     * @return 返回的的 String
     */
    @Override
    public String encryptStr(String content, String password){
        return this.encryptStr(content, password, "UTF-8");
    }


    /*-------------------------------解密操作部分--------------------------------*/

    /**
     * DES 解密
     * @param content 解密的内容
     * @param password 密码 ---->需要与加密时一致
     * @param charset 编码格式
     * @return 返回解密后的字符串
     */
    @Override
    public String decryptByte(byte[] content, String password, String charset){
        password = SecretKeyUtil.initPassword(password, 8);
        try{
            Cipher cipher = cipherMode.initDecrypt(password);
            byte[] bytes = cipher.doFinal(content);
            return new String(bytes, charset);
        }catch (Exception e){
            LOGGER.error("des decrypt is failed", e);
        }
        return null;
    }

    /**
     * DES 解密(默认是 UTF-8 编码)
     * @param content 解密的内容
     * @param password 密码 ---->需要与加密时一致
     * @return 返回解密后的字符串
     */
    @Override
    public String decryptByte(byte[] content, String password){
        return this.decryptByte(content, password, "UTF-8");
    }

    /**
     * DES 解密
     * @param content 解密的内容
     * @param password 密码 ---->需要与加密时一致
     * @param charset 编码格式
     * @return 返回解密后的字符串
     */
    @Override
    public String decryptStr(String content, String password, String charset){
        try {
            byte[] bytes = Base64.decodeBase64(content);
            return this.decryptByte(bytes, password, charset);
        }catch (Exception e){
            LOGGER.error("String getBytes is failed", e);
        }
        return null;
    }

    /**
     * DES 解密
     * @param content 解密的内容
     * @param password 密码 ---->需要与加密时一致
     * @return 返回解密后的字符串
     */
    @Override
    public String decryptStr(String content, String password){
        return this.decryptStr(content, password, "UTF-8");
    }

}
