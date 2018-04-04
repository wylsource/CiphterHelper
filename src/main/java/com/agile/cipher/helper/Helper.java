package com.agile.cipher.helper;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/3 17:15
 * @Modified By:
 */
public interface Helper {

    String encryptStr(String content, String password);
    String encryptStr(String content, String password, String charset);
    byte[] encryptByte(String content, String password, String charset);
    byte[] encryptByte(String content, String password);

    String decryptStr(String content, String password);
    String decryptStr(String content, String password, String charset);
    String decryptByte(byte[] content, String password, String charset);
    String decryptByte(byte[] content, String password);

}
