package com.park.ssm.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;
/**
 * 加盐加密工具类
 * @author ASNPHX4
 *
 */
public class Encryption {
	//生成盐(128个字符)  盐的长度应该等同于加密后字符串的长度
		public String createSalt() {//生成盐
			char[] chars="12WX3459ab0cdefghijklmnopqrwtuvz678xyzABCDEFGHIJKLMNOPQRSTUVYZ".toCharArray();
			char[] saltchars=new char[128];
			Random random = new SecureRandom();
			for(int i=0;i<saltchars.length;i++)
			{
			int n=random.nextInt(62);
			saltchars[i]=chars[n];
			}
			String salt=new String(saltchars);
			return salt;
		}
		
		//SHA512加密(128个字符)
		public String SHA512(String pwd) {
			String shaPwd = null;
			if (pwd != null && pwd.length() > 0) {
				try {
					MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
					messageDigest.update(pwd.getBytes());
					byte byteBuffer[] = messageDigest.digest();
					StringBuffer strHexString = new StringBuffer();
					for (int i = 0; i < byteBuffer.length; i++) {
						String hex = Integer.toHexString(0xff & byteBuffer[i]);
						if (hex.length() == 1) {
							strHexString.append('0');
						}
						strHexString.append(hex);
					}
					shaPwd = strHexString.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return shaPwd;
		}
}
