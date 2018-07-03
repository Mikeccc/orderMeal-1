package app.cn.qtt.bm.common.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class VerifyUtil {
	
	
	
	/**
	* 方法名称:      isSMMobile  
	* 方法描述:      验证是否是移动电话
	* @param mobile
	* @return        
	* @Author:      linch
	* @Create Date: 2012-2-16 下午4:02:58
	*/ 
	 
	public static boolean isSMMobile(String mobile){
		String reg = "(086)?(134[0-8]|(135|136|137|138|139|150|151|152|157|158|159|182|187|188|147|183)[0-9])[0-9]{7}";
		if (StringUtils.isBlank(mobile)) {
			
			return false;
		}
		
		if (!Pattern.matches(reg, mobile)) {
			System.out.println("号码："+mobile+".号码不通过！");
			return false;
		}
		return true;
	}

}

