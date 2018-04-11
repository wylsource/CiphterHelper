package com.agile.cipher.util;

import com.agile.cipher.constant.AlgorithmConstant;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/3 11:52
 * @Modified By:
 */
public class SecretKeyUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(SecretKeyUtil.class);

    /**
     * 生成秘钥
     * @param password 密码
     * @return
     */
    private static SecretKey getSecretKey(Integer keySize, String password, String cipherAlgorithm) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(cipherAlgorithm);
            //AES 要求密钥长度为 128
            kg.init(keySize, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("generate SecreteKey failed, reason={}", ex);
        }
        return null;
    }

    /**
     * 生成 AES 专用秘钥
     * @param password 密码
     * @return
     */
    public static Key secretAesKey(final String password){
        // 初始化
//        SecretKey secretKey = getSecretKey(128, password, AlgorithmConstant.AES_ALGORITHM);
//        if (secretKey != null){
//            // 转换为AES专用密钥
//            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), AlgorithmConstant.AES_ALGORITHM);
//            return secretKeySpec;
//        }
        return new SecretKeySpec(password.getBytes(), AlgorithmConstant.AES_ALGORITHM);
    }

    /**
     * 生成 DES 专用秘钥
     * @param password 密码
     * @return
     */
    public static Key secretDesKey(String password){
        return new SecretKeySpec(password.getBytes(), AlgorithmConstant.DES_ALGORITHM);
//        SecretKey secretKey = getSecretKey(56, password, AlgorithmConstant.DES_ALGORITHM);
//        return secretKey;
    }

    private static SecretKey secretKeyFactory(final String password, String cipherAlgorithm){
        SecretKeyFactory keyFactory = null;
        try {
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            keyFactory = SecretKeyFactory.getInstance(cipherAlgorithm);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            return securekey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化密码
     * @param password
     * @return
     */
    public static String initPassword(String password, int size){
        int length = password.length();
        AtomicInteger atomicInteger = new AtomicInteger(length);
        StringBuffer sb = new StringBuffer(password);
        while ((atomicInteger.getAndIncrement() % size) != 0){
            sb.append("0");
        }
        return sb.toString();
    }
}
