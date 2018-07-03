/**
 * 
 */
package app.cn.qtt.bm.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author Gabriel Ge
 * @Description
 * @date 2013-6-20 下午5:31:52
 * @type MailSenderTest
 * @project BespeakMeal
 */
public class MailSenderTest {

	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
//		mailInfo.setValidate(true);
		mailInfo.setUserName("han2000lei@163.com");
		mailInfo.setPassword("**********");// 您的邮箱密码
		mailInfo.setFromAddress("han2000lei@163.com");
		mailInfo.setToAddress("han2000lei@163.com");
		mailInfo.setSubject("设置邮箱标题 邮箱测试测试");
		mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");
		// 这个类主要来发送邮件
		MailSender sms = new MailSender("geyanmeng@qtt.cn","gym8804211");
		try {
			sms.send("371171592@qq.com", mailInfo);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
