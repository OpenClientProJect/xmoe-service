package com.example.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class RC4Decrypt {
    public static void main(String[] args) {
        try {
            String encryptedHex = "MOE6F7FEC3BF2BD3DADE350C49BE9B85DB351C9CA2C4D4C841F89";
            String key = "123654";

            // 将十六进制字符串转换为字节数组
            byte[] encryptedBytes = hexToBytes(encryptedHex);

            // 使用RC4解密
            byte[] decryptedBytes = decryptRC4(encryptedBytes, key);

            // 输出解密结果
            String decryptedText = new String(decryptedBytes);
            System.out.println("解密结果: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] decryptRC4(byte[] data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "RC4");
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static byte[] hexToBytes(String hex) {
        if (hex.startsWith("MOE")) {
            hex = hex.substring(3); // 去掉MOE前缀
        }

        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
} 