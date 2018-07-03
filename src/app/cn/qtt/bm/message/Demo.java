/*************************************************************
 * Title: Demo.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 10, 2012 4:24:13 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import app.cn.qtt.bm.message.model.Result;
import app.cn.qtt.bm.message.model.Sms;
import app.cn.qtt.bm.message.service.IConfigureService;
import app.cn.qtt.bm.message.service.impl.MessageServiceImpl;

/**
 * 
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final Properties properties = new Properties();
		InputStream inputStream = Demo.class.getResourceAsStream("/deploy.properties");
		properties.load(inputStream);
		inputStream.close();
		System.out.println(properties);
		MessageServiceImpl messageService = new MessageServiceImpl();
		IConfigureService configureService = new IConfigureService() {

			public String getValueFrom(String key) {
				return properties.getProperty(key);
			}
		};
		messageService.setConfigureService(configureService);
		Sms sms = new Sms();
		sms.setFa("8613599542221");
		sms.setOa("8613599542221");
		sms.addAddress("13599542221");
		sms.setCreateTime(new Date());
		sms.setSenderName("1065809902");
		sms.setMessage("测试");
		Result result = messageService.sendSms(sms);
		System.out.println(result);
	}

}
