package app.cn.qtt.bm.common.utils;

import java.math.BigDecimal;

/**
 * <p>
 * Title: 全天通彩漫系统_数值计算工具类
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
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 * 
 * @author zhengyi
 * @version 1.0
 */
public class MathUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化
	private MathUtil() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static BigDecimal round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 把字符串解析为double型值，若参数为无效数字则返回0
	 * 
	 * @param value
	 *            String
	 * @return double
	 */
	public static double parseDouble(String value) {
		if (StringUtil.isEmpty(value))
			return 0.0d;
		return Double.parseDouble(value);
	}

	/**
	 * 把字符串解析为long型值，若参数为无效数字则返回0
	 * 
	 * @param value
	 *            String
	 * @return double
	 */
	public static long parseLong(String value) {
		if (StringUtil.isEmpty(value))
			return 0L;
		return Long.parseLong(value);
	}

	/**
	 * 把字符串解析为float型值，若参数为无效数字则返回0
	 * 
	 * @param value
	 *            String
	 * @return double
	 */
	public static float parseFloat(String value) {
		if (StringUtil.isEmpty(value))
			return 0.0f;
		return Float.parseFloat(value);
	}

	/**
	 * 把字符串解析为int型值，若参数为无效数字则返回0
	 * 
	 * @param value
	 *            String
	 * @return double
	 */
	public static int parseInt(String value) {
		if (StringUtil.isEmpty(value))
			return 0;
		return Integer.parseInt(value);
	}

	/**
	 * 把大数值解析成非科学计数法
	 * 
	 * @param str
	 * @return
	 */
	public static String converTheValue(String str) {
		return converTheValue(str, 2);
	}

	/**
	 * 把大数值解析成非科学计数法
	 * 
	 * @param dbValue
	 * @return
	 */
	public static String converTheValue(double dbValue) {
		BigDecimal bd = new BigDecimal(dbValue);
		bd = MathUtil.round(bd, 2);
		return bd.toString();
	}

	/**
	 * 把大数值解析成非科学计数法
	 * 
	 * @param str
	 * @param scale
	 * @return
	 */
	public static String converTheValue(String str, int scale) {
		try {
			BigDecimal bd = new BigDecimal(str);
			bd = MathUtil.round(bd, scale);
			return bd.toString();
		} catch (Exception ex) {
			return str;
		}
	}

}
