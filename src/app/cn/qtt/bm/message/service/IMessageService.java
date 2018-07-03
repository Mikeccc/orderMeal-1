/*************************************************************
 * Title: IMessageService.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 3, 2012 5:17:35 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.service;

import app.cn.qtt.bm.message.model.Mms;
import app.cn.qtt.bm.message.model.Result;
import app.cn.qtt.bm.message.model.Sms;

/**
 * 
 */
public interface IMessageService {

	Result sendSms(Sms sms);
	
	Result sendMms(Mms mms);

	void setConfigureService(IConfigureService configureService);
}
