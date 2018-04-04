package com.agile.destest;

import com.agile.cipher.helper.DesHelper;
import com.agile.cipher.helper.Helper;
import com.agile.cipher.joggle.impl.DesCipherModeCbc;
import com.agile.cipher.joggle.impl.DesCipherModeCfb;
import com.agile.cipher.joggle.impl.DesCipherModeEcb;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/4 9:45
 * @Modified By:
 */
public class DesTest {

    public static void main(String[] args) {
        Helper helper = new DesHelper(new DesCipherModeCfb());
        String oldStr = "你好啊";
        String password = "123456";
        String encryptStr = helper.encryptStr(oldStr, password);
        System.out.println("加密串：" + encryptStr);
        String decryptStr = helper.decryptStr(encryptStr, password);
        System.out.println("解密串：" + decryptStr);

    }
}
