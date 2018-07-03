/**
 * 
 */
package app.cn.qtt.bm.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import app.cn.qtt.bm.model.SmsContext;

/**
 * @author GeYanmeng
 * @Description
 * @date 2013-6-13 上午10:25:42
 * @type UtilTools
 * @project BespeakMeal
 */
public class UtilTools {

	/**
	 * 得到总页数
	 * 
	 * @param pageRecords
	 * @param totalRecords
	 * @return int
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public static int getTotalPages(int pageRecords, int totalRecords) {
		int totalPages = (int) Math.ceil(MathUtil.div(totalRecords, pageRecords));
		return Math.max(totalPages, 1); //总页数至少为1 by huangwj
	}

	/**
	 * 拼接短信文本内容
	 * @param list
	 * @return String
	 * @author Gabriel
	 * @date 2013-6-26
	 */
	public static String generateSmsContext(List<SmsContext> list) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String smsText = "";
		if (!CollectionUtils.isEmpty(list)) {
			smsText += list.get(0).getShopName()
					+ "，您好，" 
					+ calendar.get(Calendar.YEAR) + "年"
					+ (calendar.get(Calendar.MONTH)+1) + "月"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "日，订单详细为“";
			for(int i=0;i<list.size();i++){
				SmsContext smsContext = list.get(i);
				if(i==(list.size()-1)){
					smsText += smsContext.getShopGoodsName()+" "+smsContext.getShopGoodsCount()+"份 ";
				}else{
					smsText += smsContext.getShopGoodsName()+" "+smsContext.getShopGoodsCount()+"份， ";
				}
			}
			
			smsText +="，请安排。";
		}
		
		return smsText;
	}

}
