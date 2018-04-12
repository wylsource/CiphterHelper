package com.agile.cipher.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * @Author: WuYL
 * @Description: crc 算法实现
 * @Date: Create in 2018/4/11 9:39
 * @Modified By:
 */
public class CrcHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrcHelper.class);

    /**
     * 加密文件
     * @param file 要加密的文件
     * @return 返回一个 crc 加密后的串
     */
    public String encryptFile(File file){

        FileInputStream inputStream = null;
        CheckedInputStream checkedInputStream = null;
        try {
            inputStream = new FileInputStream(file);
            CRC32 crc32 = new CRC32();
            checkedInputStream = new CheckedInputStream(inputStream, crc32);
            while (checkedInputStream.read() != -1){
            }
            long value = crc32.getValue();
            return Long.toHexString(value);
        } catch (Exception e) {
            LOGGER.error("file [" + file.getName() + "] is encrypt failed.", e);
        }finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != checkedInputStream){
                try {
                    checkedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 加密 byte 数组
     * @param data
     * @return
     */
    public String encryptByte(byte[] data){
        return getCrc(data);
    }

    private String getCrc(byte[] data) {
        int high;
        int flag;
        // 16位寄存器，所有数位均为1
        int wcrc = 0xffff;
        for (int i = 0; i < data.length; i++) {
            // 16 位寄存器的高位字节
            high = wcrc >> 8;
            // 取被校验串的一个字节与 16 位寄存器的高位字节进行“异或”运算
            wcrc = high ^ data[i];

            for (int j = 0; j < 8; j++) {
                flag = wcrc & 0x0001;
                // 把这个 16 寄存器向右移一位
                wcrc = wcrc >> 1;
                // 若向右(标记位)移出的数位是 1,则生成多项式 1010 0000 0000 0001 和这个寄存器进行“异或”运算
                if (flag == 1) {
                    wcrc ^= 0xa001;
                }
            }
        }

        return Integer.toHexString(wcrc);
    }
}
