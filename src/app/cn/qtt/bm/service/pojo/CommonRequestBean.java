package app.cn.qtt.bm.service.pojo;

import java.io.Serializable;

import app.cn.qtt.bm.model.TSysCodeMetadata;
import app.cn.qtt.bm.model.TSysCodeTree;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.model.TSysUserSeq;



	/**
	 * @title 系统公共模块请求对象
	 * @descriptor
	 * @author 纪竣锋
	 * @since 2013-3-27
	 * @version
	 */
public class CommonRequestBean extends RequestBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * @title系统代码
	 */
	private TSysCodeTree sysCodeTree;
	/**
	 * @title系统参数
	 */
	private TSysParameter sysParameter;
	/**
	 * @title系统字典参数
	 */
	private TSysCodeMetadata sysCodeMetadata;
	
	
	private TSysUserSeq tSysUserSeq;
	
	public TSysCodeTree getSysCodeTree() {
		return sysCodeTree;
	}
	public void setSysCodeTree(TSysCodeTree sysCodeTree) {
		this.sysCodeTree = sysCodeTree;
	}
	public TSysParameter getSysParameter() {
		return sysParameter;
	}
	public void setSysParameter(TSysParameter sysParameter) {
		this.sysParameter = sysParameter;
	}
	public TSysCodeMetadata getSysCodeMetadata() {
		return sysCodeMetadata;
	}
	public void setSysCodeMetadata(TSysCodeMetadata sysCodeMetadata) {
		this.sysCodeMetadata = sysCodeMetadata;
	}
	public TSysUserSeq gettSysUserSeq() {
	
		return tSysUserSeq;
	}
	public void settSysUserSeq(TSysUserSeq tSysUserSeq) {
	
		this.tSysUserSeq = tSysUserSeq;
	}
	
	

	
}
