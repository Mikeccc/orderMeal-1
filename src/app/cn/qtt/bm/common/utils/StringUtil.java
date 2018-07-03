package app.cn.qtt.bm.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Title: 全天通彩漫系统_String工具类
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
public class StringUtil extends StringUtils {
	private static Log log = LogFactory.getLog(StringUtil.class);

	// 中文数进制
	private static final String[] chNumUnit = { "", "拾", "百", "仟", "万", "拾万",
			"百万", "仟万", "亿", "拾亿", "百亿", "仟亿", "万亿" };
	// 中文基本数字
	private static final String[] chNum = { "零", "壹", "贰", "叁", "肆", "伍", "陆",
			"柒", "捌", "玖" };
	// 中文基本数字(簡寫)
	private static final String[] chNumSimple = { "〇", "一", "二", "三", "四", "五",
			"六", "七", "八", "九" };
	
	// 国标码和区位码转换常量
	private static final int GB_SP_DIFF = 160;
	
	//存放国标一级汉字不同读音的起始区位码
	private static final int[] secPosValueList = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,4390, 4558, 4684, 4925, 5249, 5600};

	//存放国标一级汉字不同读音的起始区位码对应读音
	private static final char[] firstLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j','k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's','t', 'w', 'x', 'y', 'z'};


	/**
	 * 把字符串字符集从GBK转换成Unicode
	 * 
	 * @param string
	 *            String 源字符串
	 * @return String
	 */
	public static String GBKToUnicode(String string) {
		return convertCharset(string, "GBK", "ISO-8859-1");
	}

	/**
	 * 把字符串从指定字符集转换成另一种字符集
	 * 
	 * @param string
	 *            String 源字符串
	 * @param fromCharset
	 *            String 原编码
	 * @param toCharset
	 *            String 新编码
	 * @return String
	 */
	public static String convertCharset(String string, String fromCharset,
			String toCharset) {
		try {
			if (fromCharset != null && toCharset != null) {
				string = new String(string.getBytes(fromCharset), toCharset);
			} else if (fromCharset == null && toCharset != null) {
				string = new String(string.getBytes(), toCharset);
			} else if (fromCharset != null && toCharset == null) {
				string = new String(string.getBytes(fromCharset));
			}
		} catch (UnsupportedEncodingException ex) {
			log.error("The charset unsupported.", ex);
		}
		return string;
	}

	/**
	 * 把字符串中的指定字符用字符替换
	 * 
	 * @param string
	 *            String 源字符串
	 * @param oldStr
	 *            String 旧子字符串
	 * @param newStr
	 *            String 新子字符串
	 * @return String
	 */
	public static String replace(String string, String oldStr, String newStr) {
		if (string == null || oldStr == null || newStr == null) {
			return null;
		}
		if (string.length() == 0 || oldStr.length() == 0) {
			return string;
		}

		String result = null;
		StringBuffer buff = new StringBuffer("");
		int newIndex = 0, oldIndex = 0;
		while ((newIndex = string.indexOf(oldStr, oldIndex)) != -1) {
			buff.append(string.substring(oldIndex, newIndex));
			buff.append(newStr);
			oldIndex = newIndex + oldStr.length();
		}
		if (oldIndex != 0) {
			buff.append(string.substring(oldIndex, string.length()));
			result = buff.toString();
		} else {
			result = string;
		}
		return result;
	}

	/**
	 * 把字符串中的指定字符用字符替换
	 * 
	 * @param string
	 *            String 源字符串
	 * @param oldChar
	 *            char 旧字符
	 * @param newChar
	 *            String 新子字符串
	 * @return String
	 */
	public static String replace(String string, char oldChar, String newChar) {
		if (string == null || oldChar == '\0' || newChar == null) {
			return null;
		}
		if (string.length() == 0) {
			return string;
		}
		return StringUtil.replace(string, String.valueOf(oldChar), newChar);
	}

	/**
	 * 把字符串中的指定字符用字符替换
	 * 
	 * @param string
	 *            String 源字符串
	 * @param oldStr
	 *            String 旧子字符串
	 * @param newChar
	 *            char 新子字符串
	 * @return String
	 */
	public static String replace(String string, String oldStr, char newChar) {
		if (string == null || newChar == '\0' || oldStr == null) {
			return null;
		}
		if (string.length() == 0) {
			return string;
		}
		return StringUtil.replace(string, oldStr, newChar);
	}

	/**
	 * 把字符串中的指定字符用字符替换
	 * 
	 * @param string
	 *            String 源字符串
	 * @param oldChar
	 *            char 旧字符
	 * @param newChar
	 *            char 新字符
	 * @return String
	 */
	public static String replace(String string, char oldChar, char newChar) {
		if (string == null || oldChar == '\0' || newChar == '\0') {
			return null;
		}
		if (string.length() == 0) {
			return string;
		}
		return string.replace(oldChar, newChar);
	}

	/**
	 * 移除字符串中指定字符
	 * 
	 * @param string
	 *            String 源字符串
	 * @param oldChar
	 *            char 要移除的字符
	 * @return String
	 */
	public static String remove(String string, char oldChar) {
		return StringUtil.replace(string, oldChar, "");
	}

	/**
	 * 移除字符串中指定字符
	 * 
	 * @param string
	 *            String
	 * @param o
	 *            char
	 * @return String
	 */
	public static String remove(String string, String o) {
		return StringUtil.replace(string, o, "");
	}

	/**
	 * 把html的字符串转成纯文本 暂时未实现该方法，返回原字符串
	 * 
	 * @param str
	 *            String 要转换的字符中
	 * @return String
	 */
	public static String html2Txt(String str) {
		throw new RuntimeException("Not implement yet");
	}

	/**
	 * 把纯文本字符串转成html脚本，保留TEXT中的HTML标签
	 * 
	 * @param string
	 *            String 源字符串
	 * @return String
	 */
	public static String txt2Html(String string) {
		return txt2Html(string, true);
	}

	/**
	 * 把纯文本字符串转成html脚本
	 * 
	 * @param string
	 *            String 源字符串
	 * @return String
	 */
	public static String txt2Html(String string, boolean keepHtmlTag) {
		if (string == null || string.length() == 0) {
			return "";
		}
		string = StringUtil.replace(string, "\r\n", "<br>");
		string = StringUtil.replace(string, "\n\r", "<br>");
		string = StringUtil.replace(string, '\r', "<br>");
		string = StringUtil.replace(string, '\n', "<br>");

		string = StringUtil.replace(string, (char) 165, "&yen;");
		string = StringUtil.replace(string, (char) 169, "&copy;");
		string = StringUtil.replace(string, (char) 174, "&reg;");
		string = StringUtil.replace(string, (char) 8364, "&euro;");
		string = StringUtil.replace(string, (char) 8482, "&#153;");
		StringBuffer result = new StringBuffer();
		int j = string.length();
		// String comment_start="<!--";
		String comment_start = "<!--";
		String comment_end = "-->";

		if (keepHtmlTag) {
			for (int ii = 0; ii < j; ii++) {
				char c = string.charAt(ii);
				// 如果当前字符是"<"(60)，则认为是HTML标签开始，从当前开始直到下一个“>”为止都不对字符进行HTML转码
				if (c == '<') {
					if (string.substring(ii, ii + comment_start.length())
							.equals(comment_start)) {
						int idex_a = string.indexOf(comment_end, ii);
						if (idex_a > 0)
							ii = idex_a + comment_end.length() - 1; // 有-->结尾
						if (idex_a == 0)
							ii = ii + comment_start.length() - 1;// 没有-->结尾
						continue;
					}

					int idx = string.indexOf('>', ii);
					if (idx > 0) {
						String tmp = string.substring(ii, idx + 1);
						result.append(tmp);
						ii = idx;
						continue;
					}
				}
				switch (c) {
				case 60:
					result.append("&lt;");
					break;
				case 62:
					result.append("&gt;");
					break;
				// case 38:
				// result.append("&amp;");
				// break;
				case 34:
					result.append("&quot;");
					break;
				// case 169:
				// result.append("&copy;");
				// break;
				// case 174:
				// result.append("&reg;");
				// break;
				// case 165:
				// result.append("&yen;");
				// break;
				// case 8364:
				// result.append("&euro;");
				// break;
				// case 8482:
				// result.append("&#153;");
				// break;
				// case 10:
				// result.append("<br>");
				// break;
				// case 13:
				// result.append("<br>");
				// break;
				case 32:
					result.append("&nbsp;");
					break;
				default:
					result.append(c);
					break;
				}
			}
		} else {
			for (int ii = 0; ii < j; ii++) {
				char c = string.charAt(ii);
				switch (c) {
				case 60:
					result.append("&lt;");
					break;
				case 62:
					result.append("&gt;");
					break;
				case 38:
					result.append("&amp;");
					break;
				case 34:
					result.append("&quot;");
					break;
				// case 169:
				// result.append("&copy;");
				// break;
				// case 174:
				// result.append("&reg;");
				// break;
				// case 165:
				// result.append("&yen;");
				// break;
				// case 8364:
				// result.append("&euro;");
				// break;
				// case 8482:
				// result.append("&#153;");
				// break;
				// case 10:
				// stringbuffer.append("<br>");
				// break;
				// case 13:
				// result.append("<br>");
				// break;
				case 32:
					result.append("&nbsp;");
					break;
				default:
					result.append(c);
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 在字符串中指定位置换行
	 * 
	 * @param string
	 *            String 源字符串
	 * @param count
	 *            int 指定位置
	 * @return String
	 */
	public static String lineWrap(String string, int count) {
		if (string == null || count <= 0) {
			return string;
		}
		int l = string.length();
		String eol = System.getProperty("line.separator", "\n");
		StringBuffer sbRet = new StringBuffer("");
		int i;
		for (i = 0; i < l / count; i++) {
			sbRet.append(string.substring(i * count, i * count + count));
			sbRet.append("\n");
		}
		sbRet.append(string.substring(i * count, l));
		return sbRet.toString();
	}

	/**
	 * 查找某一字符串中str，特定子串s的出现次数
	 * 
	 * @param string
	 *            String 源字符串
	 * @param sign
	 *            String 分隔标志
	 * @return int
	 */
	public static int getCount(String string, String sign) {
		if (string == null) {
			return 0;
		}
		StringTokenizer s = new StringTokenizer(string, sign);
		return s.countTokens();
	}

	public static String htmlEncode(String s) {
		return txt2Html(s, false);
	}

	/**
	 * 纯小数转换成汉字
	 * 
	 * @param strNum
	 *            String 待转换的数值
	 * @return String
	 */
	public static String decNum2Chinese(String strNum) {
		String[] str = StringUtil.split(strNum, ".");
		if (str.length > 1) {
			str[0] = str[1];
		}
		int len = str[0].length();
		if (len < 1) {
			return "";
		}
		StringBuffer numBuffer = new StringBuffer("点");
		for (int ii = 0; ii < len; ii++) {
			String tempStr = str[0].substring(ii, ii + 1);
			String tstr = chNum[Integer.parseInt(tempStr)];
			numBuffer.append(tstr);
		}
		return numBuffer.toString();
	}

	/**
	 * 整数转换成汉字
	 * 
	 * @param strNum
	 *            String 待转换数值
	 * @return String
	 */
	public static String intNum2Chinese(String strNum) {
		int len = strNum.length();
		if (len < 1) {
			return "";
		}
		strNum = (new StringBuffer(strNum)).reverse().toString();
		StringBuffer numBuffer = new StringBuffer("");
		if (len > 13) {
			len = 13; // 限制最长整数
		}
		for (int ii = 0; ii < len; ii++) {
			String tempStr = strNum.substring(ii, ii + 1);
			String tstr = chNum[Integer.parseInt(tempStr)] + chNumUnit[ii];
			numBuffer.insert(0, tstr);
		}
		String result = numBuffer.toString();
		result = result.replaceAll("拾零", "拾");
		result = result.replaceAll("零拾", "零");
		result = result.replaceAll("零百", "零");
		result = result.replaceAll("零仟", "零");

		result = result.replaceAll("拾万", "拾");
		result = result.replaceAll("百万", "百");
		result = result.replaceAll("仟万", "仟");
		result = result.replaceAll("拾零万", "拾万");
		result = result.replaceAll("零万", "万");
		result = result.replaceAll("万++", "万"); // 注意：此处用了正则表达式

		result = result.replaceAll("拾亿", "拾");
		result = result.replaceAll("百亿", "百");
		result = result.replaceAll("仟亿", "仟");
		result = result.replaceAll("万亿", "万");
		result = result.replaceAll("拾零亿", "拾亿");
		result = result.replaceAll("零亿", "亿");
		result = result.replaceAll("亿++", "亿"); // 注意：此处用了正则表达式
		return result;
	}

	/**
	 * 把整型数转换成中文
	 * 
	 * @param num
	 *            long 待转换数
	 * @param keepSystem
	 *            boolean 是否保持进制
	 * @return String
	 */
	public static String number2Chinese(Number num) {
		String strNum = num.toString();
		String[] tempNum = StringUtil.split(strNum, ".");
		String result = intNum2Chinese(tempNum[0]);
		if (tempNum.length > 1) {
			result += decNum2Chinese(tempNum[1]);
		}

		return result;
	}

	/**
	 * 把整型数转换成中文簡寫數字
	 * 
	 * @param num
	 *            String 待转换数
	 * @return String
	 */
	public static String number2SimpleChinese(String num) {
		if (!StringUtil.isNumeric(num)) {
			return "";
		}
		return number2SimpleChinese(new Long(num));
	}

	/**
	 * 把整型数转换成中文簡寫數字
	 * 
	 * @param num
	 *            long 待转换数
	 * @return String
	 */
	public static String number2SimpleChinese(Number num) {
		String strNum = num.toString();
		StringBuffer buf = new StringBuffer();
		for (int ii = 0; ii < strNum.length(); ii++) {
			String val = strNum.substring(ii, ii + 1);
			if (!".".equals(val) && StringUtil.isNumeric(val)) {
				buf.append(chNumSimple[Integer.parseInt(val)]);
			} else if (".".equals(val)) {
				buf.append(".");
			} else {
				buf.append(val);
			}
		}
		return buf.toString();
	}

	/**
	 * 保留可见的128以前的ASCII字符和汉字
	 * 
	 * @param str
	 *            String 待处理字符串
	 * @return String
	 */
	public static String keepVisableCharAndChinese(String str) {
		String p = "[^\u0020-\u007E\u4e00-\u9fa5]+"; // ASCII 为128以前的可见字符和汉字
		if (str != null) {
			str = str.replaceAll(p, "");
		}
		return str;
	}

	/**
	 * 去除号码中无用前缀
	 * 
	 * @param cellphone
	 * @return
	 */
	public static String getPrimalPhoneNumberFrom(String cellphone) {
		if (StringUtils.isNotBlank(cellphone)) {
			if (cellphone.startsWith("+")) {
				cellphone = cellphone.substring(1, cellphone.length());
			}
			if (cellphone.startsWith("0")) {
				cellphone = cellphone.substring(1, cellphone.length());
			}
			if (cellphone.startsWith("86")) {
				cellphone = cellphone.substring(2, cellphone.length());
			}
			if (cellphone.startsWith("12593")) {
				cellphone = cellphone.substring(5, cellphone.length());
			}
			if (cellphone.startsWith("17951")) {
				cellphone = cellphone.substring(5, cellphone.length());
			}
			if (cellphone.startsWith("12520")) {
				cellphone = cellphone.substring(5, cellphone.length());
			}
		}
		return cellphone;
	}
	
	
	
	
	
	/**
	* 方法名称:      filterUnNumber  
	* 方法描述:      剔除所有非数字
	* @param str
	* @return        
	* @Author:      linch
	* @Create Date: 2013-1-23 下午2:58:14
	*/ 
	 
	public static String filterUnNumber(String str) {
        // 只允数字
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        //替换与模式匹配的所有字符（即非数字的字符将被""替换）
        return m.replaceAll("").trim();
    }

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @return
	 */
	public static String replaceBlankAnEnterAbout(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}
	
	
	/**
	 * 过去字符串长度
	 * @param str 字符串
	 * @param flag true-不区分中英文的长度 flase-区分中英文的长度
	 * @return 字符串长度
	 */
	public static int stringLength(String str,boolean flag){
		//TODO 
		int length = 0;
		if(StringUtils.isNotBlank(str)){
			length = str.length();
		}
		return length;
	}
	/**
	  * 
	  * @param sendToList
	  * @return 去重后的号码列表
	  */
	/**
	 * 去除重复
	 * @param str 需要去除重复
	 * @param splitChar 该字符串的分隔符
	 * @return 1、去重后的字符串 2、由哪些是重复 3、重复多少个
	 * TODO
	 */
	public static String removeRepeat(String str,String splitChar) {
		 String[] mdnArray = str.split(splitChar);
		 str = "";
		 for (int i=0;i<mdnArray.length;i++) {
			 if (!str.contains(mdnArray[i])){
				 str += mdnArray[i]+splitChar;
			 }else{
				 
			 }
		 }
		 return str;
	 }
	
	
	/**
	 * 获取一个字符串的拼音码
	 * @param oriStr
	 * @return
	 */
	public static String getFirstLetter(String oriStr) {
		String str = oriStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符
			ch = str.charAt(i);
			temp = new char[] { ch };
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString();
	}

	/**
	 * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	 * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	 * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
	 */
	private static char convert(byte[] bytes) {
		char result = '-';
		int secPosValue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
		}
		secPosValue = bytes[0] * 100 + bytes[1];
		for (i = 0; i < 23; i++) {
			if (secPosValue >= secPosValueList[i]
					&& secPosValue < secPosValueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		double num1 = 10542.1231013d;
		long num2 = 542552543254325L;
		long num3 = 54200054300325L;
		long num4 = 54200054000325L;
		float num5 = 12344.23f;
		String num6 = "11224.45";
		System.out.println(String.valueOf(num1) + " = "
				+ StringUtil.number2Chinese(new Double(num1)));
		System.out.println(String.valueOf(num2) + " = "
				+ StringUtil.number2Chinese(new Long(num2)));
		System.out.println(String.valueOf(num3) + " = "
				+ StringUtil.number2Chinese(new Long(num3)));
		System.out.println(String.valueOf(num4) + " = "
				+ StringUtil.number2Chinese(new Long(num4)));
		System.out.println(String.valueOf(num6) + " = "
				+ StringUtil.number2SimpleChinese(num6));
	}

}
