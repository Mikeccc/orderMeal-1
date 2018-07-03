/*************************************************************
 * Title: Sms.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 3, 2012 5:19:16 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.model;

import java.util.Date;

/**
 * 
 */
public class Sms extends Message {

	private String message;
	private String senderName;

	public Sms() {
	}

	public Sms(String message, String senderName, Date createTime, String fa, String oa) {
		setCreateTime(createTime);
		setFa(fa);
		setMessage(message);
		setOa(oa);
		setSenderName(senderName);
	}

	public String getMessage() {
		return message;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
