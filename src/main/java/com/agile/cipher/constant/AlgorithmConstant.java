package com.agile.cipher.constant;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/3 11:25
 * @Modified By:
 */
public final class AlgorithmConstant {

    public static final String AES_ALGORITHM = "AES";

    public static final String DES_ALGORITHM = "DES";

    public static final String MD5_ALGORITHM = "MD5";

    /**
     * 使用 ECB 模式，填充方式为 PKCS5Padding(每个填充的字节都记录了填充的总字节数)
     */
    public static final String AES_ALGORITHM_ECB_PKCS5 = "AES/ECB/PKCS5Padding";
    public static final String AES_ALGORITHM_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    public static final String AES_ALGORITHM_CBC_PKCS7 = "AES/CBC/PKCS7Padding";
    public static final String AES_ALGORITHM_OFB_PKCS5 = "AES/OFB/PKCS5Padding";
    public static final String AES_ALGORITHM_CFB_PKCS5 = "AES/CFB/PKCS5Padding";

    /**
     * 使用 CBC 模式，填充方式为 NoPadding(不填充)
     */
//    public static final String AES_ALGORITHM_ECB_NO = "AES/CBC/NoPadding";

    /**
     * 使用 ECB 模式，填充方式为 ZeroBytePadding(全部填充为0的字节)
     */
//    public static final String AES_ALGORITHM_ECB_ZEROS = "AES/ECB/ZeroBytePadding";

    public static final String  DES_ALGORITHM_ECB_PKCS5 = "DES/ECB/PKCS5Padding";
    public static final String  DES_ALGORITHM_CBC_PKCS5 = "DES/CBC/PKCS5Padding";
    public static final String  DES_ALGORITHM_OFB_PKCS5 = "DES/OFB/PKCS5Padding";
    public static final String  DES_ALGORITHM_CFB_PKCS5 = "DES/CFB/PKCS5Padding";



}
