package app.cn.qtt.bm.service.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;


/**
 * @title 菜单模块请求结果（响应）对象
 * @descriptor
 * @author 纪竣锋
 * @since 2013-3-27
 * @version
 */
public class MenuResponseBean extends ResponseBean {
	private List list = new ArrayList();
	private Map<TSysMenuInfo,List<TSysMenuFuncInfo>> resultMap=new HashMap();
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map<TSysMenuInfo, List<TSysMenuFuncInfo>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<TSysMenuInfo, List<TSysMenuFuncInfo>> resultMap) {
		this.resultMap = resultMap;
	} 



}
