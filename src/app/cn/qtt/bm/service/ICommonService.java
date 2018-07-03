package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

public interface ICommonService {
	/**
	 * @title 取系统代码表
	 * @param CommonRequestBean
	 * @return CommonResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public CommonResponseBean getSyscode(CommonRequestBean requestBean);
	/**
	 * @title 取系统参数表
	 * @param CommonRequestBean
	 * @return CommonResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public CommonResponseBean getSysParameter(CommonRequestBean requestBean);
	
	
	
	
	/**
	* 方法名称:      getSysUserSeq  
	* 方法描述:      获取user序列号
	* @param requestBean
	* @return        
	* @Author:      linch
	* @Create Date: 2013-6-17 下午6:24:48
	*/ 
	 
	public CommonResponseBean getSysUserSeq(
			CommonRequestBean requestBean);
	
	public GoodsResponseBean addSysFile(GoodsRequestBean requestBean);
	
	public GoodsResponseBean querySysFileByFileId(GoodsRequestBean requestBean);
	
}
