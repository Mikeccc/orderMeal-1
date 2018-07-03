package app.cn.qtt.bm.common.utils;

import java.net.InetAddress;
import java.security.SecureRandom;

import org.apache.log4j.Category;

/**
 * <p>
 * Title: 全天通彩漫系统_UUID工具类
 * </p>
 * 
 * <p>
 * Description: 全天通彩漫系统2012年改造
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * <p>
 * Company: 北京全天通（www.qtt.cn）
 * </p>
 * 
 * @author zhengyi
 * @version 1.0
 */
public class TxUUIDGenerator {
  public TxUUIDGenerator() {
    init();
  }

  // secure random to provide non-repeating seed
  private static SecureRandom seeder;
  // cached value for mid part of string
  private static String midValue;

  private static TxUUIDGenerator key;

  private Category cat = Category.getInstance(TxUUIDGenerator.class);

  static {
    key = new TxUUIDGenerator();
  }

  private void init() {
    try {
      // get the internet address
      InetAddress inet = InetAddress.getLocalHost();
      byte[] bytes = inet.getAddress();
      String hexInetAddress = hexFormat(getInt(bytes), 8);
      // get the hashcode for this object
      String thisHashCode = hexFormat(System.identityHashCode(this), 8);
      // set up mid value string
      this.midValue = hexInetAddress + thisHashCode;
      // load up the randomizer first
      seeder = new SecureRandom();
      int node = seeder.nextInt();
    } catch (Exception e) {
      cat.error("初始化序列号失败");
    }
  }

  private String hexFormat(int i, int j) {
    String s = Integer.toHexString(i);
    return padHex(s, j) + s;
  }

  private String padHex(String s, int i) {
    StringBuffer stringbuffer = new StringBuffer();
    if (s.length() < i) {
      for (int j = 0; j < i - s.length(); j++) {
        stringbuffer.append("0");
      }
    }
    return stringbuffer.toString();
  }

  private int getInt(byte abyte0[]) {
    int i = 0;
    int j = 24;
    for (int k = 0; j >= 0; k++) {
      int l = abyte0[k] & 0xff;
      i += l << j;
      j -= 8;
    }
    return i;
  }

  public static String getUUID() {
    Category cat = Category.getInstance("app.cn.qtt.cmbase.util.TxUUIDGenerator");
    String sRet = null;
    try {
      long timeNow = System.currentTimeMillis();
      // get int value as unsigned
      int timeLow = (int) timeNow & 0xFFFFFFFF;
      // get next random value
      if (seeder == null) seeder = new SecureRandom();
      int node = seeder.nextInt();
      sRet = (key.hexFormat(timeLow, 8) + midValue + key.hexFormat(node, 8));
      //System.out.println(sRet);
    } catch (Exception e) {
      e.printStackTrace();
      cat.error("生成序列号失败");
    }
    return sRet;
  }

  public static void main(String arg[]) {
    try {
      System.out.println(System.currentTimeMillis());
      for (int i = 0; i < 4; i++) {
        System.out.println(TxUUIDGenerator.getUUID() + "  " + TxUUIDGenerator.getUUID().length());
      }
      System.out.println(System.currentTimeMillis());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
