package com.example;

import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SpringBootTest
class XmoeVideoServiceApplicationTests {

    @Test
    void contextLoads() {
        try {
            String encryptedHex = "MOE6F7FEC3BF2BD3DADE350C49BE9B85DB351C9CA2C4D4C841F89";
            String key = "123654";

            // 将十六进制字符串转换为字节数组
            byte[] encryptedBytes = hexToBytes("MOE6F7FEC3BF2BD3DADE350C49BE9B85DB351C9CA2C4D4C841F89");

            // 使用RC4解密
            String decryptedBytes = RC4(Arrays.toString(encryptedBytes), key);

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

    public static String RC4(String aInput,String aKey)
    {
        int[] iS = new int[256];
        byte[] iK = new byte[256];
        for (int i=0;i<256;i++)
            iS[i]=i;
        int j = 1;
        for (short i= 0;i<256;i++)
        {
            iK[i]=(byte)aKey.charAt((i % aKey.length()));
        }
        j=0;
        for (int i=0;i<255;i++)
        {
            j=(j+iS[i]+iK[i]) % 256;
            int temp = iS[i];
            iS[i]=iS[j];
            iS[j]=temp;
        }
        int i=0;
        j=0;
        char[] iInputChar = aInput.toCharArray();
        char[] iOutputChar = new char[iInputChar.length];
        for(short x = 0;x<iInputChar.length;x++)
        {
            i = (i+1) % 256;
            j = (j+iS[i]) % 256;
            int temp = iS[i];
            iS[i]=iS[j];
            iS[j]=temp;
            int t = (iS[i]+(iS[j] % 256)) % 256;
            int iY = iS[t];
            char iCY = (char)iY;
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;
        }
        return new String(iOutputChar);
    }
}
