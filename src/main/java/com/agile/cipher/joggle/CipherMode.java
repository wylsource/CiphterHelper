package com.agile.cipher.joggle;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * @Author: WuYL
 * @Description: 加解密模式接口
 * @Date: Create in 2018/4/3 16:23
 * @Modified By:
 */
public interface CipherMode {

    Cipher initEncrypt(String password)throws Exception;

    Cipher initDecrypt(String password)throws Exception;
}
