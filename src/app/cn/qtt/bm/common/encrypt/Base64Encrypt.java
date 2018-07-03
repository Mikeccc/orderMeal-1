/*************************************************************
 * Title: Base64Encrypt.java
 * Description: 
 * Author: 黄绍斌
 * Email: huangshaobin@qtt.cn
 * CreateTime: 2012-2-8 下午06:26:06
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.common.encrypt;

import java.io.IOException;

/**
 * 
 */
public class Base64Encrypt {

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	public static String getBASE64_byte(byte[] s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s);
	}

	public static byte[] getByteArrFromBase64(String s) throws Exception {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Decoder()).decodeBuffer(s);
	}
	
	public static void main(String[] args) throws IOException{
		String a = (new sun.misc.BASE64Encoder()).encode("123".getBytes());
		System.out.println("a="+new String(a));
		byte[] b = (new sun.misc.BASE64Decoder()).decodeBuffer(a);
		System.out.println("b="+new String(b));
		
	}
	
	
}
