package app.cn.qtt.bm.service.pojo;

import app.cn.qtt.bm.model.TLogUserLogin;



public class LogResponseBean extends ResponseBean {

	private TLogUserLogin logUserLogin;

	public TLogUserLogin getLogUserLogin() {
	
		return logUserLogin;
	}

	public void setLogUserLogin(TLogUserLogin logUserLogin) {
	
		this.logUserLogin = logUserLogin;
	}
	
	
}

