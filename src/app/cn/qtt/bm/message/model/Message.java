/*************************************************************
 * Title: Message.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 3, 2012 5:24:41 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;

import cn.qtt.core.toolkit.StringHelper;

/**
 * 
 */
public abstract class Message {

	private String oa;
	private String fa;
	private Date createTime;

	private final Set<String> addresses;

	protected Message() {
		addresses = new HashSet<String>();
	}

	public boolean addAddress(String address) {
		return addresses.add(address);
	}

	public boolean addAddresses(Collection<String> addresses) {
		return this.addresses.addAll(addresses);
	}

	public URI[] getAddresses() throws MalformedURIException {
		URI[] addresses = new URI[this.addresses.size()];
		int index = 0;
		for (String address : this.addresses) {
			addresses[index++] = new URI("86" + StringHelper.getPrimalPhoneNumberFrom(address));
		}
		return addresses;
	}

	public Date getCreateTime() {
		return createTime == null ? new Date() : createTime;
	}

	public String getFa() {
		return fa;
	}

	public String getOa() {
		return oa;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setFa(String fa) {
		this.fa = fa;
	}

	public void setOa(String oa) {
		this.oa = oa;
	}

}
