package com.example.utils;

import java.util.Objects;

/**
 * RC4加密解密工具类
 */
public class RC4DUtil {

    private static final String DEFAULT_KEY = "123654";


    //加密
    public static String encryptRC4D(String videoUrl) {
        String str = RC4(videoUrl,DEFAULT_KEY);
        return toHexString(str).toUpperCase();
    }

    //解密
    public static String parseRC4D(String RC4D) {
        return RC4(hexToString(RC4D),DEFAULT_KEY);
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
    //将字符串转换为十六进制字符串
    private static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch & 0xFF);
            if (s4.length() == 1) {
                s4 = '0' + s4;
            }
            str = str + s4;
        }
        return str;// 0x表示十六进制
    }
    //将十六进制字符串转换为字符串
    public static String hexToString(String hexString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String hex = hexString.substring(i, i + 2);
            int decimal = Integer.parseInt(hex, 16);
            stringBuilder.append((char) decimal);
        }
        return stringBuilder.toString();
    }


}