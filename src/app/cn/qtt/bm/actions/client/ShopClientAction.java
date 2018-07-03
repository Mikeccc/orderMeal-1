
 /*
 * 文 件 名:  ShopLisstAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-10-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.actions.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import app.cn.qtt.bm.actions.order.ShopListAction;


 /**       
 * 项目名称：BespeakMeal    
 * 类名称：ShopLisstAction    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-10-9 下午3:23:03    
 * 修改人：linch   
 * 修改时间：2013-10-9 下午3:23:03    
 * 修改备注：    
 * @version       
 */
@ParentPackage("order-default")
@Namespace("/client")
public class ShopClientAction extends ShopListAction {

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 8338679680246845524L;
	
	
	public String findShopList(){
		final String xFunctionName  = "ShopClientAction.findShopList()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result =  new HashMap<String,Object>();
		
		try {
			
			super.execute();
			if(getShopResponseBean() != null){
				result.put("code", "000000");
				result.put("message", getShopResponseBean());
				
			}
			else{
				result.put("code", "999999");
			}
			
			
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			result.put("code", "999999");
		}
		finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		
		
		return null;
		
		
	}

	
	
}

