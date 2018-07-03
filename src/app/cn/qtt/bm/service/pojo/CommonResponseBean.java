package app.cn.qtt.bm.service.pojo;

import java.util.ArrayList;
import java.util.List;

import app.cn.qtt.bm.model.TSysUserSeq;



/**
 * @title 系统公共模块请求结果（响应）对象
 * @descriptor
 * @author 纪竣锋
 * @since 2013-3-28
 * @version
 */
public class CommonResponseBean extends ResponseBean {
	private List list = new ArrayList();//返回系统代码
	
	private boolean Bl_blockedValidate;
	
	private TSysUserSeq userSeq;
	
	
	
	public TSysUserSeq getUserSeq() {
	
		return userSeq;
	}

	public void setUserSeq(TSysUserSeq userSeq) {
	
		this.userSeq = userSeq;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public boolean isBl_blockedValidate() {
		return Bl_blockedValidate;
	}

	public void setBl_blockedValidate(boolean bl_blockedValidate) {
		Bl_blockedValidate = bl_blockedValidate;
	}




}
