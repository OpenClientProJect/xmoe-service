package com.example.controller;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		String inputStr = "https://cloud.xmoe.app/02";
		String key = "123654";
		String video = "";
		String str = RC4(inputStr,key);
//        System.out.println(str);
		//打印加密后的字符串
		System.out.println(toHexString(str).toUpperCase());

		String upperCase = toHexString(str).toUpperCase();
		//打印解密后的字符串
		System.out.println(RC4(hexToString(upperCase),key));
//		System.out.println(RC4("解析加密后数据："+hexToString(str),key));
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
	
