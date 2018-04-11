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
}
