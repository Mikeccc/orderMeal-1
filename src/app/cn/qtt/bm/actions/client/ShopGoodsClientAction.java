package app.cn.qtt.bm.actions.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import app.cn.qtt.bm.actions.order.ShopCategoryGoodsListAction;

@ParentPackage("order-default")
@Namespace("/client")
public class ShopGoodsClientAction extends ShopCategoryGoodsListAction {

	
	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = -6588116214270340089L;

	public String execute(){
		final String xFunctionName  = "ShopGoodsClientAction.execute()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result =  new HashMap<String,Object>();
		
		try {
			
			super.execute();
			
			if(getShopInfo() != null){
				result.put("code", "000000");
				Map<String,Object> message = new HashMap<String,Object>();
				message.put("shopInfo", getShopInfo());
				message.put("curCategory", getCategory());
				message.put("allCategoryList", getCategoryList());
				message.put("goodsList", getGoodsList());
				result.put("message", message);
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

