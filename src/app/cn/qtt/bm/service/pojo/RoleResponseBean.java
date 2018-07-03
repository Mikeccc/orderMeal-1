package app.cn.qtt.bm.service.pojo;

import java.util.ArrayList;
import java.util.List;


/**
 * @title 资源模块请求结果（响应）对象
 * @descriptor
 * @author 纪竣锋
 * @since 2013-3-27
 * @version
 */
public class RoleResponseBean extends ResponseBean {
	private List list = new ArrayList();

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	} 



}
