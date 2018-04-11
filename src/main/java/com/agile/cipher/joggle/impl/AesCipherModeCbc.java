package com.agile.cipher.joggle.impl;

import com.agile.cipher.constant.AlgorithmConstant;
import com.agile.cipher.joggle.CipherMode;
import com.agile.cipher.util.SecretKeyUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Security;

/**
 * @Author: WuYL
 * @Description: CBC 加解密模式
 * @Date: Create in 2018/4/3 16:26
 * @Modified By:
 */
public class AesCipherModeCbc implements CipherMode{

    static {
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
    }
    private String vipara;

    public AesCipherModeCbc(String vipara){
        this.vipara = vipara;
    }

    @Override
    public Cipher initEncrypt(String password) throws Exception{
        Cipher cipher=Cipher.getInstance(AlgorithmConstant.AES_ALGORITHM_CBC_PKCS7);
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeyUtil.secretAesKey(password), new IvParameterSpec(vipara.getBytes()));
        return cipher;
    }

    @Override
    public Cipher initDecrypt(String password) throws Exception {
        Cipher cipher=Cipher.getInstance(AlgorithmConstant.AES_ALGORITHM_CBC_PKCS7);
        cipher.init(Cipher.DECRYPT_MODE, SecretKeyUtil.secretAesKey(password), new IvParameterSpec(vipara.getBytes()));
        return cipher;
    }
}
