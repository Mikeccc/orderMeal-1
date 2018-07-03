package app.cn.qtt.bm.service.pojo;

import java.io.Serializable;

import app.cn.qtt.bm.model.TLogUserAccess;
import app.cn.qtt.bm.model.TLogUserLogin;



public class LogRequestBean extends RequestBean implements Serializable{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 6631086269609254599L;

	private TLogUserLogin logUserLogin;
	
	private TLogUserAccess tLogUserAccess;

	public TLogUserLogin getLogUserLogin() {
	
		return logUserLogin;
	}

	public void setLogUserLogin(TLogUserLogin logUserLogin) {
	
		this.logUserLogin = logUserLogin;
	}

	public TLogUserAccess gettLogUserAccess() {
	
		return tLogUserAccess;
	}

	public void settLogUserAccess(TLogUserAccess tLogUserAccess) {
	
		this.tLogUserAccess = tLogUserAccess;
	}

	
	


	
}

