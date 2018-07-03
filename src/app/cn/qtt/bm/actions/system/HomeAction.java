/*
 * 文 件 名:  SystemIndexAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-3-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package app.cn.qtt.bm.actions.system;

import java.util.List;

import javax.xml.registry.infomodel.User;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.ISystemCommonMgr;
import app.cn.qtt.bm.model.TSysMenuInfo;

/**
 * 项目名称：BespeakMeal 类名称：HomeAction 类描述： 创建人：linch 创建时间：2013-6-18 上午10:34:41
 * 修改人：linch 修改时间：2013-6-18 上午10:34:41 修改备注：
 * 
 * @version
 */

@Namespace("/system")
@ParentPackage("system-default")
public class HomeAction extends BaseAction {

	/**
	 * 注释内容
	 */

	private static final long serialVersionUID = 4901642034046143269L;


	/**
	 * 请求令牌
	 */

	private String token;


	/**
	 * 系统菜单列表
	 */

	private List<Object> systemMenuList;

	protected ISystemCommonMgr systemCommonMgr;

	/**
	 * 方法名称: execute 方法描述:
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-1 下午7:31:29
	 */

	public String execute() {

		final String xFunctionName = "SystemHomeAction.execute()";
		gLogger.begin(xFunctionName);
		try {

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			return ERROR;
		} finally {
			gLogger.end(xFunctionName);
		}

		return SUCCESS;
	}

	/**
	 * 方法名称: systemLeft 方法描述:
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-3-27 下午5:49:06
	 */
	@Action(value = "left", results = {

	})
	public String systemLeft() {

		final String xFunctionName = "systemLeft";
		gLogger.begin(xFunctionName);
		UserBean userBean = super.getSystemUserInfoFromSession();
		String userType="";
		if(userBean!=null){
			userType = userBean.getUserType();
		}
		try {
			if(StringUtils.isNotBlank(userType)){
				List<TSysMenuInfo> menus = systemCommonMgr
						.queryMenuByType(userType);
				if (CollectionUtils.isNotEmpty(menus)) {
					systemMenuList = systemCommonMgr.queryMenuLocalTree(menus);
				}
			}
			

			// 迭代获取整颗树数据
			

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			return Constants.LOGIN;
		} finally {
			gLogger.end(xFunctionName);
		}

		return SUCCESS;
	}

	public List<Object> getSystemMenuList() {

		return systemMenuList;
	}

	public void setSystemMenuList(List<Object> systemMenuList) {

		this.systemMenuList = systemMenuList;
	}

	public String getToken() {

		return token;
	}

	public void setToken(String token) {

		this.token = token;
	}

	public ISystemCommonMgr getSystemCommonMgr() {
	
		return systemCommonMgr;
	}

	public void setSystemCommonMgr(ISystemCommonMgr systemCommonMgr) {
	
		this.systemCommonMgr = systemCommonMgr;
	}
	
	
}
