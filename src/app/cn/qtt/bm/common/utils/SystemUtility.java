package app.cn.qtt.bm.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Category;

/**
 * <p>
 * Title: 厦门市地方税务局对外网站系统
 * </p>
 * <p>
 * Description: 厦门地税对外网站系统2006年改造
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company: 北京长天科技集团（www.pansky.com.cn）
 * </p>
 * 
 * @author Sheng Youfu
 * @version 1.0
 */
public class SystemUtility {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 判断两个String是否相等,如果是字符,比较是否相同,如果是数字,比较是否相等
	 * 
	 * @param aStr
	 *            String
	 * @param bStr
	 *            String
	 * @return boolean
	 */
	public static boolean isEqualsNumberOrChar(String aStr, String bStr) {
		boolean isSame = false;
		aStr = toString(aStr);
		bStr = toString(bStr);
		if ((isEmpty(aStr)) && isEmpty(bStr)) {
			isSame = true;
		} else if ((isEmpty(aStr)) || (isEmpty(bStr))) {
			isSame = false;
		} else if (isNumber(aStr) && isNumber(bStr)) {
			double aNum = Double.parseDouble(aStr);
			double bNum = Double.parseDouble(bStr);
			if (aNum == bNum) {
				isSame = true;
			}
		} else if (aStr.equals(bStr)) {
			isSame = true;
		}
		return isSame;
	}

	/**
	 * 判断Object是否为null
	 */
	public static boolean isNull(Object oValue) {
		return oValue == null ? true : false;
	}

	/**
	 * 判断是否为空串""
	 */
	public static boolean isEmpty(String sValue) {
		if (sValue == null) {
			return true;
		}
		return sValue.trim().equals("") ? true : false;
	}

	/**
	 * 判断ArrayList 是否为空
	 * 
	 * @param list
	 *            ArrayList
	 * @return boolean
	 */
	public static boolean isEmpty(ArrayList list) {
		boolean isEmpty = false;
		if (list == null) {
			isEmpty = true;
		} else if (list.size() == 0) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	}
	
	/**
	 * 判断ArrayList 是否为空
	 * 
	 * @param list
	 *            ArrayList
	 * @return boolean
	 */
	public static boolean isEmpty(List list) {
		boolean isEmpty = false;
		if (list == null) {
			isEmpty = true;
		} else if (list.size() == 0) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	}

	/**
	 * Convert string to string after trim
	 */
	public static String toString(String sValue) {
		return isNull(sValue) ? "" : sValue.trim();
	}

	/**
	 * Return string length after trim
	 */
	public static int strLen(String sValue) {
		return isNull(sValue) ? 0 : sValue.trim().length();
	}

	/**
	 * 判断是否为整数
	 */
	public static boolean isInt(String sValue) {
		if (isEmpty(sValue)) {
			return false;
		}
		try {
			Integer.parseInt(sValue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断是否为长整数
	 */
	public static boolean isLong(String sValue) {
		if (isEmpty(sValue)) {
			return false;
		}
		try {
			Long.parseLong(sValue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断是否为数字
	 */
	public static boolean isNumber(String sValue) {
		if (isEmpty(sValue)) {
			return false;
		}
		try {
			Double.parseDouble(sValue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查是否是有效的设置位数的比例数eg:0.25
	 * 
	 * @param sValue
	 *            小数
	 * @param digits
	 *            设置的小数位数（0到1之间）
	 * @return boolean 是否是有效的设置位数的小数
	 */
	public static boolean isProportion(String sValue, int digits) {
		if (isEmpty(sValue)) {
			return false;
		}
		if (sValue.length() != 2 + digits) {
			return false;
		}
		try {

			if (sValue.equals("0")) {
				return true;
			}
			if (sValue.equals("0.0")) {
				return true;
			}
			if (sValue.equals("0.00")) {
				return true;
			}
			double dValue = Double.parseDouble(sValue);
			if ((dValue >= 0.0) && (dValue <= 1.00)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 消除字符串中空格的方法
	 * 
	 * @param oldstring
	 *            ：未消除空格的字符串
	 * @return：消除空格后的字符串
	 */
	public static String removeSpace(String oldstring) {
		String tempstring = oldstring.trim();
		int length = tempstring.length();
		String stockstring = new String("");
		String newstring = new String("");
		while (length > 1) {
			stockstring = tempstring.substring(0, 2).trim();
			newstring = newstring + stockstring;
			tempstring = tempstring.substring(2, tempstring.length());
			length = length - 2;
		}
		if (length == 1) {
			newstring = newstring + tempstring;
		}
		return newstring;
	}

	/**
	 * 检查字符串中是否有空格
	 */
	public static boolean haveSpace(String sValue) {
		if (sValue.equals(removeSpace(sValue))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断身份证的日期正确
	 * 
	 * @param identityId
	 *            String
	 * @return boolean
	 */
	public static boolean isIdentityIdCsrq(String identityId) {
		String year = null;
		String month = null;
		String day = null;

		if (identityId.length() == 18) {
			year = identityId.substring(6, 10);
			month = identityId.substring(10, 12);
			day = identityId.substring(12, 14);
		} else if (identityId.length() == 15) {
			year = identityId.substring(6, 8);
			month = identityId.substring(8, 10);
			day = identityId.substring(10, 12);
		}
		if (year.length() == 2) {
			year = "19" + year;
		}
		String csrq = year + "-" + month + "-" + day;

		return isLegalStrDate(csrq, "yyyy-MM-dd");
	}

	/**
	 * 从身份证中取性别
	 * 
	 * @param identityId
	 *            String
	 * @return String
	 */
	public static String getXbFromIdentityId(String identityId) {
		String xb = null;
		String sex = null;
		if (identityId.length() == 18) {
			sex = identityId.substring(16, 17);
		} else if (identityId.length() == 15) {
			sex = identityId.substring(14);
		}
		if (Integer.parseInt(sex) % 2 == 0) {
			xb = "2"; // 女
		} else {
			xb = "1"; // 男
		}
		return xb;
	}

	/**
	 * 从身份证中取出生日期
	 * 
	 * @param identityId
	 *            String
	 * @return String
	 */
	public static String getCsrqFromIdentityId(String identityId) {
		String year = null;
		String month = null;
		String day = null;

		if (identityId.length() == 18) {
			year = identityId.substring(6, 10);
			month = identityId.substring(10, 12);
			day = identityId.substring(12, 14);
		} else if (identityId.length() == 15) {
			year = identityId.substring(6, 8);
			month = identityId.substring(8, 10);
			day = identityId.substring(10, 12);
		}
		if (year.length() == 2) {
			year = "19" + year;
		}
		String csrq = year + "-" + month + "-" + day;

		return csrq;
	}

	/**
	 * 严格按照公安部的身份证校验规则：判断是否是有效的18位或15位个人身份证号码
	 * 
	 * @param identityId
	 *            ：18位或15位个人身份证号码
	 * @return：true： 有效的18位或15位个人身份证号码 根据征管最新的判断规则替换原来的判断规则_郑一_2007-11-16
	 */
	public static boolean isIdentityId(String identityId) {
		if (isEmpty(identityId)) {
			return false;
		}
		try {
			if (!isIdentityIdCsrq(identityId)) {
				return false;
			}

			if (identityId.length() == 18) {
				if (isLong(identityId.substring(0, 17))) {
					String verifyCharacter = null, lastCharacter = identityId
							.substring(17, 18);
					verifyCharacter = getIdentityIdVerifyCharacter(identityId); // 计算１８位身份证码最后一位
					if (isEqualsNumberOrChar(verifyCharacter, lastCharacter)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if (identityId.length() == 15) {
				try {
					Long.parseLong(identityId);
					return true;
				} catch (Exception ex) {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 计算１８位身份证号码最后一位的值
	 * 
	 * @param id
	 * @return
	 */
	private static String getIdentityIdVerifyCharacter(String id) {
		char pszSrc[] = id.toCharArray();
		int iS = 0;
		int iW[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char szVerCode[] = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5',
				'4', '3', '2' };
		int i;
		for (i = 0; i < 17; i++) {
			iS += (int) (pszSrc[i] - '0') * iW[i];
		}
		int iY = iS % 11;
		return String.valueOf(szVerCode[iY]);
	}

	/**
	 * 获取身份证号码最后一位的值_身份证由15转换成18
	 * 
	 * @param id
	 * @return
	 */
	private static String getIdentityIdLastCharceter(String id) {
		char pszSrc[] = id.toCharArray();
		int iS = 0;
		int iW[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char szVerCode[] = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5',
				'4', '3', '2' };
		int i;
		for (i = 0; i < 17; i++) {
			iS += (int) (pszSrc[i] - '0') * iW[i];
		}
		int iY = iS % 11;
		return String.valueOf(szVerCode[iY]);
	}

	// /**
	// * 严格按照公安部的身份证校验规则：判断是否是有效的18位或15位个人身份证号码
	// * @param identityId：18位或15位个人身份证号码
	// * @return：true： 有效的18位或15位个人身份证号码
	// */
	// public static boolean isIdentityId(String identityId) {
	// if (isEmpty(identityId)) {
	// return false;
	// }
	// try {
	// if (!isIdentityIdCsrq(identityId)) {
	// return false;
	// }
	//
	// if (identityId.length() == 18) {
	// try {
	// Long.parseLong(identityId.substring(0, 17));
	// return true;
	// } catch (Exception ex) {
	// return false;
	// }
	// // String identityId15 = identityId.substring(0, 6) +
	// // identityId.substring(8, 17);
	// // String newIdentityId18 = identityId.substring(0, 6) + "19" +
	// identityId.substring(8);
	// // if
	// (fixPersonIDCodeTo18(identityId15).equalsIgnoreCase(newIdentityId18)) {
	// // return true;
	// // }
	// // else {
	// // return false;
	// // }
	// } else if (identityId.length() == 15) {
	// try {
	// Long.parseLong(identityId);
	// return true;
	// } catch (Exception ex) {
	// return false;
	// }
	// } else {
	// return false;
	// }
	// } catch (Exception ex) {
	// return false;
	// }
	// }

	/**
	 * 大概判断是否是有效的18位或15位个人身份证号码：对于15位，必须为数字，对于18为前17为必须为数字，最后一位可能为字母，不在这两个位数的非法
	 * 
	 * @param identityId
	 *            ：18位或15位个人身份证号码
	 * @return：true： 有效的18位或15位个人身份证号码
	 */
	public static boolean isProbableIdentityId(String identityId) {
		if (isEmpty(identityId)) {
			return false;
		}
		try {
			if (identityId.length() == 18) {
				if (isLong(identityId.substring(0, 17))) {
					return true;
				} else {
					return false;
				}
			} else if (identityId.length() == 15) {
				return isLong(identityId);
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 根据输入年月、增加or减少的月数，得到新的年月
	 * 
	 * @param ym
	 *            要处理年月yyyy-MM
	 * @param num
	 *            增加or减少的月数
	 * @return String 新的年月
	 */
	public static String relativeYM(String ym, int num) {
		try {
			Date date1 = str2Date(ym, "yyyy-MM");
			Date date2 = dateAdd(date1, Calendar.MONTH, num);
			return date2Str(date2, "yyyy-MM");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取年月的第一天
	 * 
	 * @param ym
	 *            String
	 * @return String
	 */
	public static String getFirstDayOfMonth(String ym) {
		try {
			Date date1 = str2Date(ym, "yyyy-MM");
			return date2Str(date1, "yyyy-MM-dd");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据年月（格式：yyyy-MM）信息获取一个月的最后一天（格式:yyyy-MM-dd）
	 * 
	 * @return 格式化的日期
	 */
	public static String getLastDayOfMonth(String YM) {
		try {
			Date date1 = str2Date(YM, "yyyy-MM");
			Date date2 = dateAdd(dateAdd(date1, Calendar.MONTH, 1),
					Calendar.DATE, -1);
			return date2Str(date2, "yyyy-MM-dd");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 将当前年月加上一月数，得到新的年月(yyyyMM)
	 * 
	 * @param reduce
	 *            ：要加上的月数（负数时表示减）
	 * @return：新的年月（yyyyMM）
	 */
	public static String addMonth(int num) {
		return date2Str(dateAdd(new Date(), Calendar.MONTH, num), "yyyy-MM");
	}

	/**
	 * 将当前日期加上天数数，得到新的日期(yyyy-MM-dd)
	 * 
	 * @param reduce
	 *            ：要加上的天数（负数时表示减）
	 * @return：得到新的日期(yyyy-MM-dd)
	 */
	public static String addDate(int num) {
		return date2Str(dateAdd(new Date(), Calendar.DATE, num), "yyyy-MM-dd");
	}

	/**
	 * 获取String类型变量的字节数
	 * 
	 * @param name
	 *            ：String类型变量
	 * @return：String类型变量的字节数
	 */
	public static int GetByteSize(String name) {
		int stringSize = 0;
		if (!isEmpty(name)) {
			stringSize = getBytes_encode(name).length;
		}
		return stringSize;
	}

	public static byte[] getBytes_encode(String sourceStr) {
		byte[] newByte = null;
		try {
			newByte = sourceStr.getBytes("GBK");
		} catch (Exception e) {
			newByte = sourceStr.getBytes();
		}
		return newByte;
	}

	/**
	 * 从一个字符表达式中抽取出来的一段字符串 write by zhm
	 * 
	 * @param str
	 *            字符表达式
	 * @param offset
	 *            开始偏移量（从0开始）
	 * @param length
	 *            截取长度
	 * @return String 抽取出来的一段字符串
	 */
	public static String subStringByByte(String str, int offset, int length) {
		String newStr = "";
		int skipLen = 0;
		int newLength = 0;

		int byteLength = 0;

		// 输入校验
		if (str == null || length < 1 || offset < 0) {
			return newStr;
		}
		int strLengthByByte = getBytes_encode(str).length;
		if (strLengthByByte < offset + 1) {
			return newStr;
		}
		if (strLengthByByte - offset < length) {
			length = strLengthByByte - offset;
		}

		// 按字节取子串
		byte[] subBytes = null;
		ByteArrayInputStream bytesStream = new ByteArrayInputStream(
				getBytes_encode(str));
		bytesStream.skip(offset + skipLen);

		try {
			subBytes = new byte[strLengthByByte - offset];
			// （1）判断开始的偏移量
			byteLength = bytesStream
					.read(subBytes, 0, strLengthByByte - offset);
			if (byteLength == -1) {
				return "";
			}
			newStr = new String(subBytes);
			if (newStr == null || newStr.length() < 1
					|| getBytes_encode(newStr).length < byteLength) { // 如果开始偏移量往后的不可转变为string,则表示截取的第一位是半个汉字
				skipLen = skipLen + 1;
			}

			bytesStream.reset();
			bytesStream.skip(offset + skipLen);
			newLength = length - skipLen; // 新的截取长度
			if (newLength < 1) {
				return "";
			}
			subBytes = new byte[newLength];
			// （2）判断结束的偏移量
			byteLength = bytesStream.read(subBytes, 0, newLength);
			newStr = new String(subBytes);
			if (newStr == null || newStr.length() < 1
					|| getBytes_encode(newStr).length < byteLength) { // 如果重新截取的不可转变为string,则表示截取的最后一位是半个汉字
				newLength = newLength - 1;
			} else {
				return newStr;
			}

			bytesStream.reset();
			bytesStream.skip(offset + skipLen);
			if (newLength < 1) {
				return "";
			}
			subBytes = new byte[newLength];
			// （3）
			byteLength = bytesStream.read(subBytes, 0, newLength);
			newStr = new String(subBytes);
			if (newStr == null || newStr.length() < 1
					|| getBytes_encode(newStr).length < byteLength) {
				return "";
			}

			return newStr;
		} catch (Exception e) {
			return "";
		}

		// return newStr ;
	}

	/**
	 * 从一个字符表达式中抽取出来的一段字符串
	 * 
	 * @param str
	 *            字符表达式
	 * @param offset
	 *            从 0 开始
	 * @param length
	 *            截取的长度（按字节的）
	 * @return String 截取后字符串
	 * @throws GeneralException
	 */
	public static String leftSubStrB(String str, int length) {
		String newStr = "";
		boolean flag = true;
		int newLength = length;
		int offset = 0;
		// 输入校验
		if (str == null || length < 1 || offset < 0) {
			return newStr;
		}
		int strLengthByByte = getBytes_encode(str).length;
		if (strLengthByByte < offset + 1) {
			return newStr;
		}
		if (strLengthByByte <= length) {
			return str;
		}
		// String s3 = "abcdef".substring(2, 5); //s3 = "cde"
		while (flag) {
			try {
				newStr = str.substring(offset, offset + newLength);
				if (getBytes_encode(newStr).length > length) {
					newLength = newLength - 1;
				} else {
					break;
				}
			} catch (Exception e) {
				newLength = newLength - 1;
			}
		}
		return newStr;
	}

	/**
	 * @param num
	 * @return
	 */
	public static String Translate(double num) {
		int li_amout_flag = 1;
		if (num < 0) {
			num = Math.abs(num); // 取绝对值
			li_amout_flag = -1;
		}
		String s_num, s_char, s_num_hz;
		int i_len_of_snum, i, i_flag, i_char;
		boolean flag = false;
		i_flag = 1;
		// NumberFormatter numberFormatter = new NumberFormatter();
		// s_num = numberFormatter.formatDouble(num);
		s_num = format(num, "0.00");
		if (s_num.indexOf(".") == -1) {
			s_num = s_num + ".00";
		}
		if (s_num.equals("0.00")) {
			return "零元整";
		}
		s_num_hz = "";
		i_len_of_snum = s_num.length();
		for (int k = i_len_of_snum; k > 0; k--) {
			s_char = s_num.substring(k - 1, k);
			if (s_char.equals(".")) {
				s_char = "元";
			} else {
				i_char = Integer.parseInt(s_char);
				switch (i_char) {
				case 0:
					s_char = "零";
					break;
				case 1:
					s_char = "壹";
					break;
				case 2:
					s_char = "贰";
					break;
				case 3:
					s_char = "叁";
					break;
				case 4:
					s_char = "肆";
					break;
				case 5:
					s_char = "伍";
					break;
				case 6:
					s_char = "陆";
					break;
				case 7:
					s_char = "柒";
					break;
				case 8:
					s_char = "捌";
					break;
				case 9:
					s_char = "玖";
				}
			}

			if (!s_char.equals("零")) {
				switch (i_len_of_snum - k) {
				case 0:
					s_char = s_char + "分";
					break;
				case 1:
					s_char = s_char + "角";
					break;
				case 4:
					s_char = s_char + "拾";
					break;
				case 5:
					s_char = s_char + "佰";
					break;
				case 6:
					s_char = s_char + "仟";
					break;
				case 7:
					s_char = s_char + "万";
					flag = true;
					break;
				case 8:
					if (i_flag == 0) {
						s_char = s_char + "拾万";
						// 如果千位有数，则把万后的零去掉 2011-10-27 by ziping
						if (s_num_hz.indexOf("仟") > 0) {
							s_num_hz = s_num_hz.substring(1);
						}
					} else
						s_char = s_char + "拾";
					flag = true;
					break;
				case 9:
					if (i_flag == 0 && !flag) {
						s_char = s_char + "佰万";
						flag = true;
						// 如果千位有数，则把万后的零去掉 2011-10-27 by ziping
						if (s_num_hz.indexOf("仟") > 0) {
							s_num_hz = s_num_hz.substring(2);
						}
						// s_char = s_char + "佰";
					} else
						s_char = s_char + "佰";
					break;

				case 10:
					if (i_flag == 0 && !flag) {
						// if (s_num_hz.indexOf("万") > 0) {
						// s_char = s_char + "仟";
						// } else {
						s_char = s_char + "仟万";
						// }
						flag = true;
						// 如果千位有数，则把万后的零去掉 2011-10-27 by ziping
						if (s_num_hz.indexOf("仟") > 0) {
							s_num_hz = s_num_hz.substring(3);
						}
						// s_char = s_char + "仟";
					} else
						s_char = s_char + "仟";
					break;
				case 11:
					s_char = s_char + "亿";
				}
				i_flag = 1;
			} else {
				i_flag = 0;
			}
			s_num_hz = s_char + s_num_hz;
		}
		i = s_num_hz.indexOf("零零");
		while (i != -1) {
			s_num_hz = s_num_hz.substring(0, i) + s_num_hz.substring(i + 1);
			i = s_num_hz.indexOf("零零");
		}
		boolean flag2 = false; // 最后一位为零
		if (s_num_hz.substring(s_num_hz.length() - 1).equals("零")) {
			s_num_hz = s_num_hz.substring(0, s_num_hz.length() - 1); // +
			// "整";
			flag2 = true;
		}
		int idx = s_num_hz.indexOf("元");
		if (s_num_hz.substring(idx - 1, idx).equals("零")) {
			s_num_hz = s_num_hz.substring(0, idx - 1) + s_num_hz.substring(idx);
		}
		if (flag2) {
			s_num_hz = s_num_hz + "整";
		}

		// 修改 0.01等小于1的金额转换不对的问题
		s_num = format(num, "0.00").substring(0, 1);
		if (s_num.equals("0") && !s_num_hz.startsWith("零")) {
			s_num_hz = "零" + s_num_hz;
		}

		if (li_amout_flag == -1) {
			s_num_hz = "负" + s_num_hz;
		}
		return s_num_hz;
	}

	/**
	 * 判断firstDate是否不在lastDate之后
	 * 
	 * @param lastDate
	 * @param firstDate
	 * @param dateFormat
	 *            eg:"yyyyMM" ; "yyyy-MM-dd"
	 * @return boolean
	 */
	public static boolean notAfter(String firstDate, String lastDate,
			String dateFormat) {
		try {
			if (lastDate.equals(firstDate)) {
				return true;
			}
			Date first = str2Date(firstDate, dateFormat);
			Date last = str2Date(lastDate, dateFormat);
			return first.before(last);
		} catch (Exception ex) {
		}
		return false;
	}

	/**
	 * 将字符串按特定分隔符拆分为一个String数组
	 * 
	 * @param str
	 *            以固定符号分割的字符串
	 * @param splitChar
	 *            分隔符
	 * @return 找不到时返回一个空的数组，判断数组的size
	 */
	public static String[] splitStr(String str, String splitChar) {
		if (str == null || str.length() == 0) {
			return new String[0];
		}
		if (splitChar == null || splitChar.length() == 0) {
			return new String[0];
		}
		int count = 1, pos = 0;

		while ((pos = str.indexOf(splitChar, pos)) >= 0) {
			count++;
			if (pos + splitChar.length() >= str.length()) {
				break;
			} else {
				pos = pos + splitChar.length();
			}
		}
		if (count == 1) {
			return new String[0];
		}
		String arrSplit[] = new String[count];

		if (count == 1) {
			arrSplit[0] = str;
		} else {
			int i = 0;
			while (i < count) {
				if (str.indexOf(splitChar) >= 0) {
					arrSplit[i] = str.substring(0, str.indexOf(splitChar));
				} else {
					arrSplit[i] = str;
					break;
				}
				str = str
						.substring(str.indexOf(splitChar) + splitChar.length());
				i++;
			}
		}
		return arrSplit;
	}

	/**
	 * 如果字串1是空格串或是空串，则转化成新的字串2
	 * 
	 * @param svalue
	 *            　字串1
	 * @param newvalue
	 *            　字串2
	 * @return　
	 * @throws GeneralException
	 */
	public static String isNulltoStr(String svalue, String newvalue) {
		String retstr = "";
		try {
			if ((svalue != null && svalue.trim().equalsIgnoreCase("")) || svalue == null) {
				retstr = newvalue;
			} else {
				retstr = svalue;
			}
		} catch (Exception ex) {
		}
		return retstr;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurTime() {
		return date2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取文件当前时间
	 * 
	 * @return
	 */
	public static String getFileCurTime() {
		return date2Str(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 获取当前日期：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return date2Str(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前日期2：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getCurDate2() {
		return date2Str(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取当前年月 样式为yyyy-MM
	 * 
	 * @return
	 */
	public static String getCurYM() {
		return date2Str(new Date(), "yyyy-MM");
	}

	/**
	 * 获取当前年度
	 * 
	 * @return
	 */
	public static String getCurYear() {
		return date2Str(new Date(), "yyyy");
	}

	/**
	 * 根据显示样式获取当前时间信息
	 * 
	 * @return 格式化的当前时间信息
	 */
	public static String getCurDateByPattern(String pattern) {
		return date2Str(new Date(), pattern);
	}

	/**
	 * 根据增加or减少的时间得到新的日期
	 * 
	 * @param curDate
	 *            当前日期
	 * @param field
	 *            需操作的'年'or'月'or'日'
	 * @param addNumber
	 *            增加or减少的时间
	 * @return
	 */
	public static Date dateAdd(Date curDate, int field, int addNumber) {
		GregorianCalendar curGc = new GregorianCalendar();
		curGc.setTime(curDate);
		curGc.add(field, addNumber);
		return curGc.getTime();
	}

	/**
	 * 根据增加or减少的天数得到新的日期
	 * 
	 * @param date
	 *            String 旧的日期
	 * @param inFormt
	 *            String 旧的日期格式
	 * @param field
	 *            int 需操作的'年'or'月'or'日'
	 * @param outFormat
	 *            String 输出的时间格式
	 * @return String
	 */
	public static String dateAdd(String date, String inFormt, int addNumber,
			String outFormat) {
		try {
			return date2Str(dateAdd(str2Date(date, inFormt),
					GregorianCalendar.DATE, addNumber), outFormat);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 根据增加or减少的时间得到新的日期
	 * 
	 * @param date
	 *            String 旧的日期
	 * @param inFormt
	 *            String 旧的日期格式
	 * @param field
	 *            int 需操作的'年'or'月'or'日'
	 * @param addNumber
	 *            int 增加or减少的时间
	 * @param outFormat
	 *            String 输出的时间格式
	 * @return String
	 */
	public static String dateAdd(String date, String inFormt, int field,
			int addNumber, String outFormat) {
		try {
			return date2Str(dateAdd(str2Date(date, inFormt), field, addNumber),
					outFormat);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 是否为合法的日期字符串
	 * 
	 * @param strDate
	 *            String
	 * @param pattern
	 *            String
	 * @return boolean
	 */
	public static boolean isLegalStrDate(String strDate, String pattern) {
		boolean islegal = true;

		try {
			if (strDate == null || strDate.trim().equals("")) {
				return false;
			}
			String newStrDate = date2Str(str2Date(strDate, pattern), pattern);
			if (newStrDate.equals(strDate)) {
				islegal = true;
			} else {
				islegal = false;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			islegal = false;
		}
		return islegal;
	}

	/**
	 * 把时间转换为字串 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            待转换的时间
	 * @return
	 */
	public static String date2Str(Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		return date2Str(date, format);
	}

	/**
	 * 把时间转换为字串
	 * 
	 * @param date
	 *            待转换的时间
	 * @param format
	 *            转换格式
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 把时间格式由一种格式转换为另一种格式为字串
	 * 
	 * @param sdate
	 *            待转换的时间
	 * @param oldformat
	 *            旧格式
	 * @param newformat
	 *            新格式
	 * @return
	 */
	public static String formateDateStr(String sdate, String oldformat,
			String newformat) throws ParseException {
		Date date = str2Date(sdate, oldformat);
		return date2Str(date, newformat);
	}

	/**
	 * 把字串转换为日期
	 * 
	 * @param sdate
	 *            字串形式的日期
	 * @param format
	 *            字串格式
	 * @return 转换为日期类型
	 * @throws ParseException
	 */
	public static Date str2Date(String sdate, String format)
			throws ParseException {
		if (sdate == null || sdate != null && sdate.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(sdate);
	}

	/**
	 * 字符串转换为double,不抛出异常
	 * 
	 * @param str
	 * @return
	 */
	public static double str2DoubleNoException(String str) {
		double ret = 0;
		try {
			ret = Double.parseDouble(str);
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 给字串加上前缀
	 * 
	 * @param srcStr
	 *            需要加入前缀的字串
	 * @param length
	 *            返回字串总长度
	 * @param fixChar
	 *            前缀字符
	 * @return 加上前缀后的字串
	 */
	public static String fixPrefixStr(String srcStr, int length, String fixChar) {
		if (srcStr == null) {
			srcStr = "";
		}
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length - srcStr.length(); i++) {
			sb.append(fixChar);
		}
		return new String(sb) + srcStr;
	}

	public static String fixPrefixStrb(String srcStr, int length, String fixChar) {
		String encoding = "GBK";

		if (srcStr == null) {
			srcStr = "";
		}
		StringBuffer sb = new StringBuffer(length);

		int srcSize = 0;
		try {
			srcSize = srcStr.getBytes(encoding).length;
		} catch (UnsupportedEncodingException ue) {
		}
		for (int i = 0; i < length - srcSize; i++) {
			sb.append(fixChar);
		}
		return new String(sb) + srcStr;

	}

	/**
	 * 给字串加上后缀
	 * 
	 * @param srcStr
	 *            需要加入后缀的字串
	 * @param length
	 *            返回字串总长度
	 * @param fixChar
	 *            后缀字符
	 * @return 加上后缀后的字串
	 */
	public static String fixSuffixStr(String srcStr, int length, String fixChar) {
		if (srcStr == null) {
			srcStr = "";
		}
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length - srcStr.length(); i++) {
			sb.append(fixChar);
		}
		return srcStr + new String(sb);
	}

	public static String fixSuffixStrb(String srcStr, int length, String fixChar) {
		String encoding = "GBK";
		if (srcStr == null) {
			srcStr = "";
		}
		StringBuffer sb = new StringBuffer(length);
		int srcSize = 0;
		try {
			srcSize = srcStr.getBytes(encoding).length;
		} catch (UnsupportedEncodingException ue) {
		}
		for (int i = 0; i < length - srcSize; i++) {
			sb.append(fixChar);
		}
		return srcStr + new String(sb);
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
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
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
	 * 四舍五入
	 * 
	 * @param a
	 *            要操作的数据
	 * @param fractionPos
	 *            舍入的小数位数
	 * @return
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v + 0.0000000000001));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入到2位小数
	 * 
	 * @param a
	 *            要操作的数据
	 * @return
	 */
	public static double round2frac(double a) {
		return round(a, 2);
	}

	/**
	 * 把null或空串转换为一个空格，用于oracle的多条件不确定查询
	 * 
	 * @param str
	 * @return
	 */
	public static String null2Space(String str) {
		String space = " ";
		if (str == null || str.trim().length() == 0) {
			return space;
		}
		return str.trim();
	}

	/**
	 * 把null转换为0长度字串
	 * 
	 * @param str
	 * @return
	 */
	public static String null2Str(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		return str.trim();

	}

	public static String null2Zero(String str) {
		String zero = "0";
		if (str == null || str.trim().length() == 0) {
			return zero;
		}
		return str.trim();
	}

	/**
	 * 替换第一次出现的字串
	 * 
	 * @param oldString
	 * @param strSearch
	 * @param strReplace
	 * @return
	 * @throws RemoteException
	 */
	public static String replaceString(String oldString, String strSearch,
			String strReplace) {
		int pos = oldString.indexOf(strSearch);
		while (pos > 0) {
			oldString = oldString.substring(0, pos) + strReplace
					+ oldString.substring(pos + strSearch.length());
			pos = oldString.indexOf(strSearch);
		}
		return oldString;
	}

	/**
	 * 修补15位个人身份证号码为18位
	 * 
	 * @param personIDCode
	 *            根据征管最新的判断身份证第18位的规则替换原来的判断规则_赖曲珍2007-11-23
	 * @return
	 */
	public static String fixPersonIDCodeTo18(String personIDCode)
			throws Exception {
		if (!isProbableIdentityId(personIDCode)) {
			// 身份证格式不对：对于15位的身份证，必须全部为数字，对于18为身份证前17位必须位数字，其他位数均是不对的身份证格式
			throw new Exception(
					"error.system.SystemUtility.fixPersonIDCodeTo18.sfzVerifyError");
		}

		String retIDCode = "";
		if (personIDCode == null || personIDCode.trim().length() != 15) {
			return personIDCode;
		}
		String lastCheckBit = "";
		String id17 = personIDCode.substring(0, 6) + "19"
				+ personIDCode.substring(6, 15); // 15为身份证补'19'
		if (personIDCode.length() == 15) {
			lastCheckBit = getIdentityIdLastCharceter(id17); // 获取身份证码最后一位
		}
		return id17 + lastCheckBit;
	}

	/**
	 * 将18位个人身份证号码变为15位
	 * 
	 * @param personIDCode
	 * @return
	 */
	public static String fixPersonIDCodeTo15(String personIDCode)
			throws Exception {
		if (!isProbableIdentityId(personIDCode)) {
			// 身份证格式不对：对于15位的身份证，必须全部为数字，对于18为身份证前17位必须位数字，其他位数均是不对的身份证格式
			String errCode = "err.util.SystemUtility.personIDCodeFormate.err";
			throw new Exception(errCode);
		}

		return personIDCode.substring(0, 6) + personIDCode.substring(8, 17);
	}

	// String identityId15 = ;

	/**
	 * 计算指定年月的前后的年月
	 * 
	 * @param yearMon
	 * @param relayMons
	 * @param format
	 *            //add by Tina
	 * @return 返回新的年月
	 */
	public static String relayYM(String yearMon, int relayMons, String format) {
		String newYM = "";
		try {
			// newYM=date2Str(
			// dateAdd(str2Date(yearMon,"yyyyMM"),java.util.GregorianCalendar.MONTH,relayMons),"yyyyMM");
			newYM = date2Str(dateAdd(str2Date(yearMon, format),
					java.util.GregorianCalendar.MONTH, relayMons), format);

		} catch (Exception e) {
		}
		return newYM;
	}

	/**
	 * 返回两个年月之间的月数
	 * 
	 * @param begYM
	 *            开始年月
	 * @param endYM
	 *            结束年月
	 * @return int 月数
	 */
	public static int monthsBetween(Date startDate, Date endDate) {
		GregorianCalendar gcStart = new GregorianCalendar();
		GregorianCalendar gcEnd = new GregorianCalendar();
		gcStart.setTime(startDate);
		gcEnd.setTime(endDate);
		return (gcEnd.get(GregorianCalendar.YEAR) - gcStart
				.get(GregorianCalendar.YEAR))
				* 12
				+ gcEnd.get(GregorianCalendar.MONTH)
				- gcStart.get(GregorianCalendar.MONTH);
	}

	/**
	 * 更改文件
	 * 
	 * @param oldFile
	 * @param newFile
	 */
	public static void renameFile(File oldFile, File newFile) {
		oldFile.delete();
		if (!oldFile.renameTo(newFile)) {
			Category.getInstance("cn.com.pansky.xmds.util.SystemUtility")
					.debug(
							"Rename File " + oldFile.getPath() + " to "
									+ newFile.getPath() + " failed");
		}
	}

	public static String format2frac(double number) {
		return format(number, "0.00");
	}

	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @param pattern
	 * @return
	 * @throws GeneralException
	 */
	public static String format(double number, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(number);
	}

	public static String format(String number, String pattern) {
		double d = new Double(number).doubleValue();
		return format(d, pattern);
	}

	/**
	 * 金额数字的转换
	 * 
	 * @param sumofcash
	 *            double
	 * @return String
	 */
	public static String doubleToChinese(double sumofcash) {
		/*
		 * String[] arr = {"分","角","元","拾","百","千","万","拾万","百万",
		 * "千万","亿","拾亿","百亿","千亿"}; String sTmp = ""; String sMoney = "";
		 * String header = ""; //added by jerry.xiao 2003/03/31 if(sumofcash<0)
		 * header = "负"; sumofcash = Math.abs(sumofcash); double dTmp
		 * =sumofcash*100; sMoney = format(dTmp,"0,000.#"); StringTokenizer st =
		 * new StringTokenizer(sMoney,","); String tmp = ""; while
		 * (st.hasMoreElements()){ tmp = tmp + st.nextToken(); } sMoney = tmp;
		 * int iLen = sMoney.length(); int count = 0; for (int i=0;i<iLen;i++) {
		 * String sTemp = sMoney.substring(i,i+1); if (sTemp.equals("0")) {
		 * count++; if (count==1) { sTmp = sTmp + trans(sTemp); } }else { sTmp =
		 * sTmp + trans(sTemp); sTmp = sTmp + arr[iLen-i-1]; count = 0; }
		 * 
		 * } sTmp = sTmp.trim(); iLen = sTmp.length(); if
		 * (sTmp.substring(iLen-1,iLen).equals("零")) { sTmp =
		 * sTmp.substring(0,iLen-1); } sTmp = sTmp + "整"; sTmp =
		 * header.concat(sTmp);
		 */
		return Translate(sumofcash);
	}

	private static String trans(String args) {
		int iTemp = Integer.parseInt(args);
		String sRes = "";
		switch (iTemp) {
		case 1:
			sRes = "壹";
			break;
		case 2:
			sRes = "贰";
			break;
		case 3:
			sRes = "叁";
			break;
		case 4:
			sRes = "肆";
			break;
		case 5:
			sRes = "伍";
			break;
		case 6:
			sRes = "陆";
			break;
		case 7:
			sRes = "柒";
			break;
		case 8:
			sRes = "捌";
			break;
		case 9:
			sRes = "玖";
			break;
		case 0:
			sRes = "零";
		}
		return sRes;
	}

	/**
	 * 获得串s的字节长度
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthb(String src) {
		int ret = 0;
		if (src != null) {
			try {
				ret = src.getBytes("GBK").length;
			} catch (Exception e) {
			}
		}
		return ret;
	}

	/**
	 * 获得串s的按字节长度的子串
	 * 
	 * @param src
	 * @param startindex
	 * @param endindex
	 * @return
	 */
	public static String substringb(String src, int startindex, int endindex) {
		String ret = null;
		if (src != null) {
			try {
				byte[] srcb = src.getBytes("GBK");
				int lengthb = srcb.length;
				if (startindex > lengthb || endindex > lengthb
						|| startindex < 0 || endindex < 1) {
					return src;
				}
				int length = 0;
				while (lengthb > 0 && length == 0) {
					byte[] retb = new byte[endindex - startindex];
					for (int i = startindex; i < endindex; i++) {
						retb[i - startindex] = srcb[i];
					}
					ret = new String(retb);
					length = ret.length();
					endindex--;
				} // end while
			} catch (Exception e) {
			}
		} // end if
		return ret;
	}

	/**
	 * 将异常的堆栈信息传递出来
	 * 
	 * @param throwable
	 * @return
	 */
	public static String throwable2StackTrace(Throwable throwable) {
		if (throwable == null) {
			throwable = new Throwable(
					"[Null exception passed, creating stack trace for offending caller]");
		}
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		throwable.printStackTrace(new PrintStream(bytearrayoutputstream));
		return bytearrayoutputstream.toString();
	}

	/**
	 * 比较两个View类对象是否相等
	 * 
	 * @param object1
	 *            View类1
	 * @param object2
	 *            View类2
	 * @return 相等返回 true,不相等返回 false
	 * @throws GeneralException
	 */
	public static boolean comparandViewobject(Object object1, Object object2) {
		boolean yesorno = false;
		try {
			if (!object1.getClass().getName().equals(
					object2.getClass().getName())) {
				return false;
			}
			Field[] fieldnames1 = object1.getClass().getDeclaredFields();
			Field[] fieldnames2 = object2.getClass().getDeclaredFields();
			if (fieldnames1.length != fieldnames2.length) {
				return false;
			}
			for (int i = 0; i < fieldnames1.length; i++) {
				fieldnames1[i].setAccessible(true);
				fieldnames2[i].setAccessible(true);
				if (fieldnames1[i].get(object1) == null
						&& fieldnames2[i].get(object2) == null) {
					continue;
				}
				if ((fieldnames1[i].get(object1) == null)
						|| (fieldnames2[i].get(object2) == null)
						|| !((String) (fieldnames1[i].get(object1)))
								.equals((String) (fieldnames2[i].get(object2)))) {
					yesorno = false;
					break;
				}
			}

		} catch (Exception ex) {
		}
		return yesorno;
	}

	/**
	 * 将字符串补位使为定长的位数（是字符串补位，不是字节数补位）
	 * 
	 * @param oldString
	 *            String 需要定长的字符串
	 * @param length
	 *            int 定长的长度
	 * @param appendChar
	 *            String 需要补加的单个串
	 * @param isprefix
	 *            boolean 是否加为前缀，是为加为前缀，否则为后缀
	 * @return String
	 */
	public static String fixLenthString(String oldString, int length,
			String appendChar, boolean isprefix) {
		if (oldString == null) {
			return null;
		}
		if (length <= 0 || isEmpty(appendChar) || oldString.length() > length
				|| appendChar.length() > 1) {
			return oldString;
		}
		String newStr = "";
		String appStr = "";

		int remainBits = length - oldString.length();
		for (int bitIndex = 0; bitIndex < remainBits; bitIndex++) {
			appStr = appStr + appendChar;
		}
		if (isprefix) {
			newStr = appStr + oldString;
		} else {
			newStr = oldString + appStr;
		}
		return newStr;
	}

	/**
	 * 根据机构代码获取分局代码
	 * 
	 * @param groupId
	 *            String
	 * @return String
	 */
	public static String getFjByGroupId(String groupId) {
		return groupId.substring(0, 4) + "00";
	}

	/**
	 * trimCollection
	 * 
	 * @param list
	 *            Collection
	 * @param length
	 *            int
	 * @return Collection
	 */
	public static ArrayList trimCollection(ArrayList list, int length) {
		if (list == null) {
			return null;
		}
		ArrayList result = new ArrayList();
		if (list.size() > length) {
			Iterator it = list.iterator();
			for (int i = 0; i < length; i++) {
				Object o = it.next();
				result.add(o);
			}
		} else {
			result = list;
		}
		return result;
	}

	/**
	 * 根所总行数计算打印的页数
	 * 
	 * @param int rowCount
	 * @param int pageCount
	 * @return int
	 */
	public static int getPageCount(int rowCount, int pageCount) {
		int page = 0;
		if (rowCount % pageCount == 0) {
			page = rowCount / pageCount;
		} else {
			page = rowCount / pageCount + 1;
		}
		return page;
	}

	/**
	 * 根据税务机关代码得到区局代码，规则为： 税务机关代码的前四位+“00”
	 * 
	 * @param swjg_dm
	 *            String
	 * @return String
	 */
	public static String getQjidBySwjg_dm(String swjg_dm) {
		if (isEmpty(swjg_dm) || swjg_dm.length() != 6) {
			return null;
		} else {
			return swjg_dm.substring(0, 4) + "00";
		}
	}

	/**
	 * 将java.util.Date转化为java.sql.Date
	 * 
	 * @param date
	 *            java.util.Date
	 * @return java.sql.Dat
	 */
	public static java.sql.Date utilDateTosqlDate(java.util.Date date) {
		java.sql.Date rtnDate = new java.sql.Date(date.getTime());
		return rtnDate;
	}

	/**
	 * 将身份证15变18，18变15
	 * 
	 * @param sfzh
	 *            String
	 * @return String
	 * @throws UniversalException
	 */
	public static String getSfz15Or18(String sfzh) throws Exception {
		String newSfzh = sfzh;

		if (sfzh.length() == 18) {
			newSfzh = SystemUtility.fixPersonIDCodeTo15(sfzh);
		} else if (sfzh.length() == 15) {
			newSfzh = SystemUtility.fixPersonIDCodeTo18(sfzh);
		}
		return newSfzh;
	}

	/**
	 * 根据身份证号获取年龄_适用于单位参保 以当前日期来计算
	 * 
	 * @param identityid
	 *            String
	 * @return int
	 * @throws GeneralException
	 */
	public static int getAgeOfIdentityid(String identityid) throws Exception {
		int age = 0;
		DateUtil du = new DateUtil();
		int curyear = du.getYear(new java.util.Date());
		int curmonth = du.getMonth(new java.util.Date());
		int curday = du.getDay(new java.util.Date());
		int year = 0; // 出生年份
		int month = 0; // 出生月份
		int day = 0; // 出生日
		if (isIdentityId(identityid)) {
			if (identityid.length() == 15) {
				year = Integer.parseInt("19" + identityid.substring(6, 8));
				month = Integer.parseInt(identityid.substring(8, 10));
				day = Integer.parseInt(identityid.substring(10, 12));
			}
			if (identityid.length() == 18) {
				year = Integer.parseInt(identityid.substring(6, 10));
				month = Integer.parseInt(identityid.substring(10, 12));
				day = Integer.parseInt(identityid.substring(12, 14));
			}
			// 1出生日期月份小于当前月
			if (month < curmonth) {
				age = curyear - year;
			}

			if (month == curmonth) {
				// 2出生日期日小于等于当前日
				if (day <= curday) {
					age = curyear - year;
				}
				// 3出生日期大于当前日
				if (day > curday) {
					age = curyear - year - 1;
				}
			}
			// 4出生日期月份大于当前月
			if (curmonth < month) {
				age = curyear - year - 1;
			}
		} else {
			age = -1; // 身份较验不通过
		}
		return age;
	}

	/**
	 * 字符空格整理 去掉头尾全角和半角空格 去掉空格相邻的字符不为英文或半角数字的中间空格， 英文或半角空格间的空格只能保留一个
	 * 
	 * @param str
	 * @return
	 */
	public static String StringTrim(String str) {
		char[] val = str.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < val.length; i++) {
			if (val[i] <= ' ' || val[i] == '　') {
				if (i > 0) {
					if ((val[i - 1] >= 'A' && val[i - 1] <= 'Z')
							|| (val[i - 1] >= 'a' && val[i - 1] <= 'z')
							|| (val[i - 1] >= '0' && val[i - 1] <= '9')) {
						if (i + 1 <= val.length) {
							if ((val[i + 1] >= 'A' && val[i + 1] <= 'Z')
									|| (val[i + 1] >= 'a' && val[i + 1] <= 'z')
									|| (val[i + 1] >= '0' && val[i + 1] <= '9')) {
								sb.append(val[i]);
							}
						}
					}
				}
			} else {
				sb.append(val[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * fixLenth 字符串补位
	 * 
	 * @param i
	 *            int
	 * @return String
	 */
	private static String fixLenth(int i) {
		String s = i + "";
		int remainBits = 6 - s.length();
		for (int bitIndex = 0; bitIndex < remainBits; bitIndex++) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * 返回千分位_至少支持到亿
	 * 
	 * @param str1
	 * @return
	 */
	public static String getQfw(String str1) {
		try {
			BigDecimal bd = new BigDecimal(str1);
			bd = MathUtil.round(bd, 2);
			DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
			return df.format(bd);
		} catch (NumberFormatException e) {
			return str1;
		}
	}

	/**
	 * 返回千分位_至少支持到亿
	 * 
	 * @param str1
	 * @return
	 */
	public static String getQfw(double str1) {
		try {
			BigDecimal bd = new BigDecimal(str1);
			bd = MathUtil.round(bd, 2);
			DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
			return df.format(bd);
		} catch (NumberFormatException e) {
			return String.valueOf(str1);
		}
	}

	/**
	 * 防止跨站脚本攻击 _黑名单法
	 * 
	 * @param value
	 * @return
	 */
	public static String filterXssStr(String value) {
		if (value == null || value.length() == 0) {
			return value;
		}
		StringBuffer result = new StringBuffer();
		String filtered = null;
		for (int i = 0; i < value.length(); i++) {
			filtered = null;			
			switch (value.charAt(i)) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
//			case '"':
//				result.append("&quot;");
//				break;
			case '\'':
				result.append("&#39;");
				break;
			case '%':
				result.append("&#37;");
				break;
			case ';':
				result.append("&#59;");
				break;
			case '(':
				result.append("&#40;");
				break;
			case ')':
				result.append("&#41;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '+':
				result.append("&#43;");
				break;
			default:
				result.append(value.charAt(i));
				break;
			}
		}
		return result == null ? value : result.toString();
	}

	/**
	 * 防止跨站脚本攻击 _白名单法
	 * a
	 * @param value
	 * @return
	 */
	public static String EncodeHtml(String strInput) {
		if (strInput.length() == 0) {
			return "";
		}
		StringBuffer builder = new StringBuffer(strInput.length() * 2);
		CharacterIterator it = new StringCharacterIterator(strInput);
		for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
			if ((((ch > '`') && (ch < '{')) || ((ch > '@') && (ch < '[')))
					|| (((ch == ' ') || ((ch > '/') && (ch < ':'))) || (((ch == '.') || (ch == ',')) || ((ch == '-') || (ch == '_'))))) {
				builder.append(ch);
			} else {
				builder.append("&#" + (int) ch + ";");
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(SystemUtility.filterXssStr("iid=17784<script>S"));
	}
}
