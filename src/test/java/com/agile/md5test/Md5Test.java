package com.agile.md5test;

import com.agile.cipher.helper.Md5Helper;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/4 15:26
 * @Modified By:
 */
public class Md5Test {

    public static void main(String[] args) {
        Md5Helper helper = new Md5Helper();
        String context = "你好我号大家好";
        String s1 = helper.encryptStr(context, "你好");
        String s2 = helper.encryptStr(context, "你好");
        System.out.println(s1.equals(s2));
    }
}
