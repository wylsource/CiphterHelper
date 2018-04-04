package com.agile.AesTest;

import com.agile.cipher.constant.AlgorithmConstant;
import com.agile.cipher.helper.AesHelper;
import com.agile.cipher.joggle.impl.AesCipherModeCbc;
import com.agile.cipher.joggle.impl.AesCipherModeCfb;
import com.agile.cipher.joggle.impl.AesCipherModeEcb;
import com.agile.cipher.joggle.impl.AesCipherModeOfb;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/3 12:56
 * @Modified By:
 */
public class AesTest {

    public static void main(String[] args) {
        AesHelper aesHelper = new AesHelper(new AesCipherModeOfb());
        String str = "你好啊";
//        String password = "123456";
        String password = "*##%&$AGIlestar(";
        String encryptStr = aesHelper.encryptStr(str, password);

        System.out.println("加密后：" + encryptStr);
        String s = aesHelper.decryptStr(encryptStr, password);
        System.out.println("解密后：" + s);
    }
}
