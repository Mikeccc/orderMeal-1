package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;

public class MenuRequestBean extends RequestBean {

	private int currentPage = 1;// 当前页
	
	private int pageRecorders = 10; // 每页10条数据 
	
	private TSysMenuInfo sysmenuinfo;
	private List<TSysMenuInfo> sysmenuinfoList;
	
	private TSysMenuFuncInfo sysmenuFuncinfo;
	private List<TSysMenuFuncInfo> sysmenuFuncinfoList;
	

	public Integer getCurrentPage() {
		return currentPage;
	}

	public int getPageRecorders() {
		return pageRecorders;
	}

	public TSysMenuInfo getSysmenuinfo() {
		return sysmenuinfo;
	}

	public void setSysmenuinfo(TSysMenuInfo sysmenuinfo) {
		this.sysmenuinfo = sysmenuinfo;
	}

	public List<TSysMenuInfo> getSysmenuinfoList() {
		return sysmenuinfoList;
	}

	public void setSysmenuinfoList(List<TSysMenuInfo> sysmenuinfoList) {
		this.sysmenuinfoList = sysmenuinfoList;
	}

	public TSysMenuFuncInfo getSysmenuFuncinfo() {
		return sysmenuFuncinfo;
	}

	public void setSysmenuFuncinfo(TSysMenuFuncInfo sysmenuFuncinfo) {
		this.sysmenuFuncinfo = sysmenuFuncinfo;
	}

	public List<TSysMenuFuncInfo> getSysmenuFuncinfoList() {
		return sysmenuFuncinfoList;
	}

	public void setSysmenuFuncinfoList(List<TSysMenuFuncInfo> sysmenuFuncinfoList) {
		this.sysmenuFuncinfoList = sysmenuFuncinfoList;
	}

}
