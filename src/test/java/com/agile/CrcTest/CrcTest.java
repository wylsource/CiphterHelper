package com.agile.CrcTest;

import com.agile.cipher.helper.CrcHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/4/11 9:47
 * @Modified By:
 */
public class CrcTest {

    private CrcHelper crcHelper;

//    @Before
    public void init(){
        crcHelper = new CrcHelper();
    }

//    @Test
    public void testEncryptFile(){

        //d47e6e05
        //992de6a


        String testFilePath = "F:\\10000233955445.pdf";
        String aLong = crcHelper.encryptFile(new File(testFilePath));
        System.out.println(aLong);
    }
}
