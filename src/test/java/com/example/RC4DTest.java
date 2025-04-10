package com.example;

import com.example.utils.RC4DUtil;
import org.junit.jupiter.api.Test;

public class RC4DTest {

    // 解密
    @Test
    public void decryptTest() {
        String test = "6F7FEC3BF2BD3DADE350C49BE9B85DB351C9CA2C4D4C841F89";
        String decrypt = RC4DUtil.parseRC4D(test);
        System.out.println(decrypt);
    }

    // 加密
    @Test
    public void encryptTest() {
        String video = "https://cloud.xmoe.app/01";

        String encrypt = RC4DUtil.encryptRC4D(video);
        System.out.println(encrypt);
    }
}
