package app.cn.qtt.bm.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESC
{

	private static final String PASSWORD_CRYPT_KEY = "!qtt.cbs!"; 
	private final static String DES = "DES"; 
	/**
	 * @param args
	 */

//	public static void main(String[] arg)
//	{
//		String strEncrypted = "yp51tNoc%2fRQjx6MM%2f557%2bSH9n4CvYI24Ufu6L%2fLpPUuHYm3XOwPDd82iYrIWZzvBfSlo6UTEPKc%3d";
//		System.out.println(DESC.desDecodeCookiesValue(strEncrypted));
//	}

	@SuppressWarnings("deprecation")
	public static String desDecodeCookiesValue(String CookieValue)
	{
		String descString = java.net.URLDecoder.decode(CookieValue);
		return desCookiesValue(descString);
	}

	public static String desCookiesValue(String strEncrypted)
	{
		try
		{
			String strKey = "!soqicn!";
			Base64 objBase64 = new Base64();
			byte[] bysDecoded = objBase64.decode(strEncrypted.getBytes());
			DESKeySpec objDesKeySpec = new DESKeySpec(strKey.getBytes("ASCII"));
			SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);
			Cipher objCipher = Cipher.getInstance("DES/ECB/NoPadding");
			objCipher.init(Cipher.DECRYPT_MODE, objSecretKey);
			String strDecrypted = new String(objCipher.doFinal(bysDecoded), "utf-8").trim();
			return strDecrypted;
		} catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
		return "";

	}
	
	
	

/** 
 * 加密 
 * @param src 数据源 
 * @param key 密钥，长度必须是8的倍数 
 * @return   返回加密后的数据 
 * @throws Exception 
 */ 
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception
	{
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

   /** 
    * 解密 
    * @param src 数据源 
    * @param key 密钥，长度必须是8的倍数 
    * @return     返回解密后的原始数据 
    * @throws Exception 
    */ 
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception
	{
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	} 
    /** 
     * 密码解密 
     * @param data 
     * @return 
     * @throws Exception 
     */ 
	public final static String decrypt(String data)
	{
		try
		{
			return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/** 
	* 密码加密 
	* @param password 
	* @return 
	* @throws Exception 
	*/ 
	public final static String encrypt(String password)
	{
		try
		{
			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * 二行制转字符串 
	 * @param b 
	 * @return 
	 */ 
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	} 

	public static byte[] hex2byte(byte[] b)
	{
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2)
		{
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	public static void main(String[] args)
	{
		String pwd = "zhubinjie1";
		System.out.println("测试数据=" + pwd);
		String data = encrypt(pwd);
		System.out.println("加密后的数据data=" + data);
		pwd = decrypt(data);
		System.out.println("解密后=" + pwd);

	}
	
}
