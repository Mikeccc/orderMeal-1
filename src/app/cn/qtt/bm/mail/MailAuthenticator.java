/**
 * 
 */
package app.cn.qtt.bm.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author Gabriel Ge
 * @Description 服务器邮箱登录验证
 * @date 2013-6-20 下午5:25:28
 * @type MailAuthenticator
 * @project BespeakMeal
 */
public class MailAuthenticator extends Authenticator {
	/**
	 * 用户名（登录邮箱）
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 初始化邮箱和密码
	 * 
	 * @param username
	 *            邮箱
	 * @param password
	 *            密码
	 */
	public MailAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	String getPassword() {
		return password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
