package app.cn.qtt.bm.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.dao.TSysCodeTreeDAO;
import app.cn.qtt.bm.dao.TSysFileDAO;
import app.cn.qtt.bm.dao.TSysParameterDAO;
import app.cn.qtt.bm.dao.TSysUserSeqDAO;
import app.cn.qtt.bm.model.TSysCodeTree;
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.model.TSysParameterExample;
import app.cn.qtt.bm.model.TSysUserSeq;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

public class CommonServiceImpl implements ICommonService {
	protected CCrppLog4j log = new CCrppLog4j(CommonServiceImpl.class
			.getName());
	
	private TSysCodeTreeDAO tSysCodeTreeDao;
	private TSysParameterDAO tSysParameterDAO;
	
	private TSysUserSeqDAO tSysUserSeqDAO;
	
	/*
	 * 图片文件上传
	 */
	private TSysFileDAO sysFileDAO;
	
	 /**
	 * seq dao
	 */
	 
	

	public TSysParameterDAO getTSysParameterDAO() {
		return tSysParameterDAO;
	}

	public void setTSysParameterDAO(TSysParameterDAO sysParameterDAO) {
		tSysParameterDAO = sysParameterDAO;
	}
	

	public CommonResponseBean getSysParameter(
			CommonRequestBean requestBean) {
		TSysParameterExample example=new TSysParameterExample();
		CommonResponseBean responseBean=new CommonResponseBean();
		List<TSysParameter> list =new ArrayList<TSysParameter>();
		try{		
			if(requestBean.getSysParameter()!=null){
				TSysParameter parameter = requestBean.getSysParameter();
				TSysParameterExample.Criteria criteria = example.createCriteria();
				String code = parameter.getParamCode();
				if (StringUtils.isNotBlank(code)) {
					criteria.andParamCodeEqualTo(StringUtils.trim(code));
				}
				list = tSysParameterDAO.selectByExampleWithBLOBs(example);
				responseBean.setList(list);
			}else{
				list=tSysParameterDAO.selectByExampleWithBLOBs(example);
				responseBean.setList(list);
			}
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		return responseBean;
	}

	public CommonResponseBean getSysPromptInfo(
			CommonRequestBean requestBean) {
		// TODO Auto-generated method stub
		return null;
	}

	public CommonResponseBean getSysProvider(
			CommonRequestBean requestBean) {
		// TODO Auto-generated method stub
		return null;
	}

	public CommonResponseBean getSyscode(
			CommonRequestBean requestBean) {
		List<TSysCodeTree> list=new ArrayList<TSysCodeTree>();
		CommonResponseBean responseBean=new CommonResponseBean();
		if(requestBean.getSysCodeTree()==null){
			//获取全部系统代码			
			list=(List<TSysCodeTree>)tSysCodeTreeDao.selectByExample(null);
			responseBean.setList(list);
		}else{
			//根据需求取值
		}
		return responseBean;
	}


	/**
	* 方法名称:      getSysUserSeq  
	* 方法描述:      
	* @param requestBean
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 下午2:55:51
	*/ 
	 
	@Override
	public CommonResponseBean getSysUserSeq(
			CommonRequestBean requestBean) {
		
		CommonResponseBean response = new CommonResponseBean();
		String xFunctionName = "getSysUserSeq";
		
		try {
			log.begin(xFunctionName);
			
			//验证传参
			if (requestBean == null
					|| requestBean.gettSysUserSeq() == null
					|| StringUtils.isBlank(requestBean.gettSysUserSeq()
							.getBusinessType())) {
				response.setResponseCode(String
						.valueOf(ExceptionConstants.ILLEGAL_ARGUMENT_CODE));
				response.setErrMsg("argument is null !");
				return response;
			}
			
			Integer seqId = tSysUserSeqDAO.insertSeqByType(requestBean.gettSysUserSeq());
			
			
			TSysUserSeq userSeq = tSysUserSeqDAO.selectByPrimaryKey(seqId);
			
			
			response.setUserSeq(userSeq);
			
			
		} catch (Exception e) {
			response.setResponseCode(String
					.valueOf(ExceptionConstants.UNKNOWN_BUSINESS_CODE));
			response.setException(e);
			response.setErrMsg("exception !");
			log.exception(xFunctionName, e);
		}
		finally{
			log.end(xFunctionName);
		}
		
		// TODO Auto-generated method stub
		return response;
		
	}
	
	
	public GoodsResponseBean addSysFile(GoodsRequestBean requestBean){
		GoodsResponseBean responseBean = new GoodsResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSysFile() != null){
				int fileId = sysFileDAO.insert(requestBean.getSysFile());
				responseBean.setFileId(fileId);
			}else{
				throw new Exception("ShopGoodsTime对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}


	public GoodsResponseBean querySysFileByFileId(GoodsRequestBean requestBean){
		GoodsResponseBean responseBean = new GoodsResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSysFile() != null){
				TSysFile sysFile = sysFileDAO.selectByPrimaryKey(requestBean.getSysFile().getFileId());
				responseBean.setSysFile(sysFile);
			}else{
				throw new Exception("ShopGoodsTime对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	public TSysCodeTreeDAO gettSysCodeTreeDao() {
		return tSysCodeTreeDao;
	}

	public void settSysCodeTreeDao(TSysCodeTreeDAO tSysCodeTreeDao) {
		this.tSysCodeTreeDao = tSysCodeTreeDao;
	}

	public TSysParameterDAO gettSysParameterDAO() {
		return tSysParameterDAO;
	}

	public void settSysParameterDAO(TSysParameterDAO tSysParameterDAO) {
		this.tSysParameterDAO = tSysParameterDAO;
	}

	public TSysUserSeqDAO gettSysUserSeqDAO() {
	
		return tSysUserSeqDAO;
	}

	public void settSysUserSeqDAO(TSysUserSeqDAO tSysUserSeqDAO) {
	
		this.tSysUserSeqDAO = tSysUserSeqDAO;
	}

	public TSysFileDAO getSysFileDAO() {
		return sysFileDAO;
	}

	public void setSysFileDAO(TSysFileDAO sysFileDAO) {
		this.sysFileDAO = sysFileDAO;
	}

	
	
	

}
