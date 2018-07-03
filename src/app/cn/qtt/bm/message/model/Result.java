/*************************************************************
 * Title: Result.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 3, 2012 5:20:00 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.model;

/**
 * 
 */
public class Result implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6811240132549983550L;
	
	private String code;
	private Object message;

	public Result() {
	}

	public Result(String code, Object message) {
		setCode(code);
		setMessage(message);
	}

	public String getCode() {
		return code;
	}

	public Object getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String toString() {
		//return "{code:'" + code + "', message:'" + message + "'}";
		return this.getMessage()!=null?this.getMessage().toString():"";
	}

}
