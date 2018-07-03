package app.cn.qtt.testbm.service;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.testbm.base.BaseTest;

public class CommonServiceImplTest extends BaseTest{
	
	private ICommonService commonService;
	
	public void testGetSysParameter(){
		commonService = (ICommonService)getContext().getBean("commonServiceImpl");
		CommonRequestBean requestBean= new CommonRequestBean();
		TSysParameter sysParameter = new TSysParameter();
		sysParameter.setParamCode("upload_txt_file_path");
		requestBean.setSysParameter(sysParameter);
		CommonResponseBean responseBean= commonService.getSysParameter(requestBean);
		List<TSysParameter>  list = null;
//		if(responseBean == null ){
//			log.error("responseBean is null");
//			return;
//		}
		Assert.assertNotNull("responseBean is null", responseBean);
//		if(!StringUtils.equals(responseBean.getResponseCode(), "00")){
//			log.error(responseBean.getErrMsg());
//			return;
//		}
		
		Assert.assertEquals(responseBean.getErrMsg(), responseBean.getResponseCode(), "00");
		list = responseBean.getList();
//		if(CollectionUtils.isEmpty(list)){
//			log.error("无参数信息");
//			return;
//		}
		Assert.assertNotNull("List<TSysParameter> is null", list);
		sysParameter = list.get(0);
		log.info("sysParameter.getParamCode():"+sysParameter.getParamCode());
		log.info("sysParameter.getParamValue():"+sysParameter.getParamValue());
		
	}
}
