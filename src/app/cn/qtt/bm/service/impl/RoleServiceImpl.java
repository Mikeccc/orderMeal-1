package app.cn.qtt.bm.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.dao.TSysLinkRoleMenuDAO;
import app.cn.qtt.bm.dao.TSysRoleInfoDAO;
import app.cn.qtt.bm.model.TSysLinkRoleMenu;
import app.cn.qtt.bm.model.TSysLinkRoleMenuExample;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysRoleInfoExample;
import app.cn.qtt.bm.service.IRoleService;
import app.cn.qtt.bm.service.pojo.MenuRequestBean;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.RoleResponseBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;

public class RoleServiceImpl implements IRoleService {
	private TSysRoleInfoDAO tSysRoleInfoDAO;
	private TSysLinkRoleMenuDAO tSysLinkRoleMenuDAO;
	


	public TSysRoleInfoDAO getTSysRoleInfoDAO() {
		return tSysRoleInfoDAO;
	}


	public void setTSysRoleInfoDAO(TSysRoleInfoDAO sysRoleInfoDAO) {
		tSysRoleInfoDAO = sysRoleInfoDAO;
	}


	public RoleResponseBean addDefinedRole(
			RoleRequestBean requestBean) {
		RoleResponseBean responseBean=new RoleResponseBean();
		try{
			if(null==requestBean){
				throw new Exception("requestBean 为空");
			}else if(null==requestBean.getSysroleinfo()){
				throw new Exception("roleinfo 为空");
			}else{
				tSysRoleInfoDAO.insert(requestBean.getSysroleinfo());
				responseBean.setResponseCode(Constants.INSERT_SUCCESS_CODE);
			}
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
		}
		
		return responseBean;
	}

	
	public RoleResponseBean deleteDefinedRole(
			RoleRequestBean requestBean) {
		RoleResponseBean responseBean=new RoleResponseBean();
		
		try{
			if(null==requestBean){
				throw new Exception("requestBean 为空");
			}else if(null==requestBean.getSysroleinfo()){
				throw new Exception("roleinfo 为空");
			}else{
				TSysRoleInfoExample example=new TSysRoleInfoExample();
				example.createCriteria().andRoleIdEqualTo(requestBean.getSysroleinfo().getRoleId());
				tSysRoleInfoDAO.deleteByExample(example);
				responseBean.setResponseCode(Constants.DELETE_SUCCESS_CODE);
			}
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
		}
		
		return responseBean;
	}


	public RoleResponseBean queryForRole(
			RoleRequestBean requestBean) {
		RoleResponseBean responseBean=new RoleResponseBean();
		//构造一个岗位信息响应对象
		//PostInfoResponseBean postInforesponseBean =new PostInfoResponseBean();
		//List<TSysPostInfo> postInfoList=new ArrayList();
		//TSysPostInfo tSysPostInfo=new TSysPostInfo();
		try{
			if(null==requestBean){
				throw new NullPointerException("requestBean 请求对象为空");
			}
			if(null==requestBean.getCurrentUser()||StringUtils.isBlank(requestBean.getCurrentUser().getUserCode())){
				throw new NullPointerException("当前用户为空");
			}
			if(null==requestBean.getSysroleinfo()){
				throw new  NullPointerException("角色对象为空");
			}
			//根据当前用户找到岗位
			/*postInforesponseBean=this.SysPostService.querySysPostByCurrentUserCode(requestBean.getCurrentUser().getUserCode());
			if(Constants.SELECT_SUCCESS_CODE.equals(postInforesponseBean.getResponseCode())){
				postInfoList=postInforesponseBean.getList();
			}else{
				throw postInforesponseBean.getException();
			}
			if(postInfoList!=null&&postInfoList.size()>0){
				tSysPostInfo=postInfoList.get(0);
			}else{
				throw new  NullPointerException("未找到当前用户岗位");
			}*/
			
			
			TSysRoleInfoExample example=new TSysRoleInfoExample(); 
			TSysRoleInfoExample.Criteria criteria=example.createCriteria();
			if(requestBean.getSysroleinfo().getRoleId()!=null){
				criteria=criteria.andRoleIdEqualTo(requestBean.getSysroleinfo().getRoleId());
			}
			if(!StringUtils.isBlank(requestBean.getSysroleinfo().getRoleName())){

				criteria=criteria.andRoleNameLike("%"+requestBean.getSysroleinfo().getRoleName()+"%");
			}
			if(!StringUtils.isBlank(requestBean.getSysroleinfo().getRoleSysFlag())){
				criteria.andRoleSysFlagEqualTo(requestBean.getSysroleinfo().getRoleSysFlag());
			}
			if(StringUtils.isNotBlank(requestBean.getSysroleinfo().getRoleStatus())){
				criteria.andRoleStatusEqualTo(requestBean.getSysroleinfo().getRoleStatus());
			}
			List list=tSysRoleInfoDAO.selectByExample(example);
			//List<?> list=tSysRoleInfoDAO.queryAllRole(requestBean.getSysroleinfo(), tSysPostInfo);
			responseBean.setList(list);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}
		return responseBean;
	}

	public RoleResponseBean queryForRoleByUser(UserRequestBean requestBean) {
		RoleResponseBean responseBean = new RoleResponseBean();
		List<TSysRoleInfo> resultList = null;
		try {
			if (null == requestBean) {
				throw new NullPointerException("requestBean 请求对象为空");
			}
			if (null == requestBean.getSysuserinfo()) {
				throw new NullPointerException("当前用户为空");
			}
			
			resultList = tSysRoleInfoDAO.selectRoleByUserCode(requestBean.getSysuserinfo());
			responseBean.setResultList(resultList);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}
		
		return responseBean;
	}


	public RoleResponseBean updateRole(
			RoleRequestBean requestBean) {
		RoleResponseBean responseBean=new RoleResponseBean();		
		try{
			if(null==requestBean){
				throw new Exception("requestBean 为空");
			}else if(null==requestBean.getSysroleinfo()){
				throw new Exception("roleinfo 为空");
			}else if(requestBean.getSysroleinfo().getRoleId()==null){
				throw new Exception("roleCode 为空");
			}else{
				TSysRoleInfoExample example=new TSysRoleInfoExample();
				example.createCriteria().andRoleIdEqualTo(requestBean.getSysroleinfo().getRoleId());
				tSysRoleInfoDAO.updateByExampleSelective(requestBean.getSysroleinfo(), example);
				responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
			}
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
		}
		
		return responseBean;
	}

	/**
	 * 角色赋权实现
	 */
	public RoleResponseBean updateRole(
			RoleRequestBean requestBean,
			MenuRequestBean menuRequestBean) {
		RoleResponseBean responseBean=new RoleResponseBean();//构造返回对象
		TSysLinkRoleMenu record=new TSysLinkRoleMenu();//构造插入对象
		Date currentDate=new Date();
		try{
			if(null==requestBean){
				throw new NullPointerException("requestBean请求对象为空");
			}
			if(null==requestBean.getSysroleinfo()){
				throw new NullPointerException("角色对象为空");
			}
			if(requestBean.getSysroleinfo().getRoleId()==null){
				throw new NullPointerException("角色编码为空");
			}
			if(null==menuRequestBean){
				throw new NullPointerException("menuRequestBean请求对象为空");
			}
			if(null==menuRequestBean.getSysmenuinfoList()){
				throw new NullPointerException("菜单列表对象为空");
			}
			//插入前先删除
			TSysLinkRoleMenuExample example=new TSysLinkRoleMenuExample();
			example.createCriteria().andRoleIdEqualTo(requestBean.getSysroleinfo().getRoleId());
			tSysLinkRoleMenuDAO.deleteByExample(example);
			if(menuRequestBean.getSysmenuinfoList().size()!=0){//如果不为空
				for(TSysMenuInfo tSysMenuInfo:menuRequestBean.getSysmenuinfoList()){				
					if(null==tSysMenuInfo||tSysMenuInfo.getMenuId()==null){
						throw new NullPointerException("菜单编号为空");
					}
					//TODO
					//record.setEnterprisCode(requestBean.getSysroleinfo().getEnterprisCode());
					record.setMenuId(tSysMenuInfo.getMenuId());
					record.setRoleId(requestBean.getSysroleinfo().getRoleId());
					record.setCreateTime(currentDate);
					tSysLinkRoleMenuDAO.insert(record);
				}
				responseBean.setResponseCode(Constants.INSERT_SUCCESS_CODE);
			}
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
		}		
		return responseBean;
	}


	public TSysLinkRoleMenuDAO getTSysLinkRoleMenuDAO() {
		return tSysLinkRoleMenuDAO;
	}


	public void setTSysLinkRoleMenuDAO(TSysLinkRoleMenuDAO sysLinkRoleMenuDAO) {
		tSysLinkRoleMenuDAO = sysLinkRoleMenuDAO;
	}


	@Override
	public RoleResponseBean queryForRoleByCode(
			RoleRequestBean requestBean) {
		TSysRoleInfoExample example=new TSysRoleInfoExample(); 
		RoleResponseBean responseBean=new RoleResponseBean();//构造返回对象		
		try{
			if(requestBean.getSysroleinfo().getRoleId()!=null){
				example.createCriteria().andRoleIdEqualTo(requestBean.getSysroleinfo().getRoleId());
			}
			List<TSysRoleInfo> list=tSysRoleInfoDAO.selectByExample(example);
			responseBean.setList(list);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}catch(Exception e){
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		return responseBean;
	}
}
