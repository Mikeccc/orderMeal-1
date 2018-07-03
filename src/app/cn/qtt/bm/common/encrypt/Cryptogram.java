/*************************************************************
 * Title: Cryptogram.java
 * Description: 
 * Author: 黄绍斌
 * Email: huangshaobin@qtt.cn
 * CreateTime: 2012-2-8 下午06:25:36
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.common.encrypt;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 */
public class Cryptogram {

	private static byte[] defaultIV = { 1, 2, 3, 4, 5, 6, 7, 8 };

	private static byte chr2hex(String chr) {
		if (chr.equals("0")) {
			return 0x00;
		} else if (chr.equals("1")) {
			return 0x01;
		} else if (chr.equals("2")) {
			return 0x02;
		} else if (chr.equals("3")) {
			return 0x03;
		} else if (chr.equals("4")) {
			return 0x04;
		} else if (chr.equals("5")) {
			return 0x05;
		} else if (chr.equals("6")) {
			return 0x06;
		} else if (chr.equals("7")) {
			return 0x07;
		} else if (chr.equals("8")) {
			return 0x08;
		} else if (chr.equals("9")) {
			return 0x09;
		} else if (chr.equals("A")) {
			return 0x0a;
		} else if (chr.equals("B")) {
			return 0x0b;
		} else if (chr.equals("C")) {
			return 0x0c;
		} else if (chr.equals("D")) {
			return 0x0d;
		} else if (chr.equals("E")) {
			return 0x0e;
		} else if (chr.equals("F")) {
			return 0x0f;
		}
		return 0x00;
	}

	public static byte[] HexStringToByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (chr2hex(s.substring(i * 2, i * 2 + 1)) * 0x10 + chr2hex(s.substring(i * 2 + 1, i * 2 + 2)));
		}
		return buf;
	}

	/**
	 * Encrypt the data by the key.
	 * 
	 * @param OriSource
	 * @return strResult
	 * @throws Exception
	 */
	public static String encryptByKey(String OriSource, String key) throws Exception {

		String strResult = "";
		try {

			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key), "DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);

			byte[] testSrc = OriSource.getBytes();
			byte[] encoded = c3des.doFinal(testSrc);

			strResult = Base64Encrypt.getBASE64_byte(encoded);

		} catch (Exception e) {
			strResult = "";
			System.out.println("Encrypt failure!!!");
		}

		return strResult;
	}

	/**
	 * Decrypt the encrypted data with the key.
	 * 
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 */
	public static String decryptByKey(String encryptedData, String key) throws Exception {

		String strResult = "";
		try {

			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key), "DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.DECRYPT_MODE, myKey, ivspec);

			byte[] s = Base64Encrypt.getByteArrFromBase64(encryptedData);
			byte[] encoded = c3des.doFinal(s);
			strResult = new String(encoded);

		} catch (Exception e) {
			strResult = "";
			System.out.println("Decrypt failure!!!");
		}

		return strResult;
	}

	/**
	 * Decrypt the encrypted data with the key.
	 * 
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 */
	public static String getBase64HashString(String str) throws Exception {

		byte[] testSrc = str.getBytes();
		MessageDigest alga = MessageDigest.getInstance("SHA-1");
		alga.update(testSrc);
		byte[] digesta = alga.digest();
		return Base64Encrypt.getBASE64_byte(digesta);
	}

	/**
	 * Decrypt the encrypted data with the key.
	 * 
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 */
	public static String getAuthenicator(String sourceStr, String key) throws Exception {

		String strResult = "";
		try {

			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key), "DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);

			byte[] testSrc = sourceStr.getBytes();
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			alga.update(testSrc);
			byte[] digesta = alga.digest();

			byte[] encoded = c3des.doFinal(digesta);
			strResult = Base64Encrypt.getBASE64_byte(encoded);

		} catch (Exception e) {
			strResult = "";
			System.out.println("Decrypt failure!!!" + e.getMessage());
		}

		return strResult;
	}

	public static void main(String args[]) throws Exception {
		String serviceId = "1";
		String serviceKey = "testtesttesttesttesttesttesttesttesttesttesttest";
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String digest = getBase64HashString(serviceId);
		String hashServiceId  = Base64Encrypt.getBASE64(serviceId);
		System.out.println("The Base64HashString data :" + digest);
		// Get 3DES data.
		String encryptStr = encryptByKey(timestamp+"_"+digest, serviceKey);
		System.out.println("The Encrypted data :" + URLEncoder.encode(encryptStr, "UTF-8"));
		// Decrypt
		String decryptStr = decryptByKey(encryptStr, serviceKey);
		System.out.println("The Decrypted data :" + decryptStr);
		System.out.println("The timestamp data :" + timestamp);
		System.out.println("The hashServiceId data :" + hashServiceId);

	}
}
