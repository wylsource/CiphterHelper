package com.agile.cipher.joggle.impl;

import com.agile.cipher.constant.AlgorithmConstant;
import com.agile.cipher.joggle.CipherMode;
import com.agile.cipher.util.SecretKeyUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

/**
 * @Author: WuYL
 * @Description: CFB 加解密模式
 * @Date: Create in 2018/4/3 16:26
 * @Modified By:
 */
public class AesCipherModeCfb implements CipherMode{

    @Override
    public Cipher initEncrypt(String password) throws Exception {
        Cipher cipher=Cipher.getInstance(AlgorithmConstant.AES_ALGORITHM_CFB_PKCS5);
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeyUtil.secretAesKey(password), new IvParameterSpec(password.getBytes()));
        return cipher;
    }

    @Override
    public Cipher initDecrypt(String password) throws Exception {
        Cipher cipher=Cipher.getInstance(AlgorithmConstant.AES_ALGORITHM_CFB_PKCS5);
        cipher.init(Cipher.DECRYPT_MODE, SecretKeyUtil.secretAesKey(password), new IvParameterSpec(password.getBytes()));
        return cipher;
    }
}
