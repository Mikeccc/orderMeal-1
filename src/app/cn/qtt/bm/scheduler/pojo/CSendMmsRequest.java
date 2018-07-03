package app.cn.qtt.bm.scheduler.pojo;

import java.util.Date;

public class CSendMmsRequest {

	private int id;
	// 外部主键
	private String mmsFeginCode;
	private java.lang.String accountId;

	private java.lang.String accountPwd;

	private java.lang.String authenticator;

	private java.lang.String businessId;

	private java.lang.String extension;
	private java.lang.String mmsId;

	private java.lang.String mojiImageId;

	private java.lang.String mmsSubject;
	//接收方号码
	private java.lang.String recipient;

	private java.lang.String sender;

	private java.lang.String timeStamp;

	private java.lang.String transactionType;

	private java.lang.String userId;
	/**
	 * 彩信文本内容
	 */
	private java.lang.String mmsText;
	/**
	 * 附件内容
	 */
	private java.lang.String source;

	private int send_count;

	private java.lang.String content;

	private String tableName;

	private String batchId;
	
	//add private
	private String spID;
	
	private String spServiceID;
	
	//尾帧对应路径
	private String lastFrameFilePath;
	//透传类型
	private String mdnAddressFlag;
	//透传号码 即 发送地址
	private String senderAddress;
	//收费类型
	private String feeType;
	//cpCode 分成的情况下才有CPCODE
	private String cpCode;
	//发送类型 01-二维码发送
	private String sendType;
	private Date beforeSendTime;
	private Date afterSendTimeSendTime;
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public java.lang.String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(java.lang.String authenticator) {
		this.authenticator = authenticator;
	}

	public java.lang.String getExtension() {
		return extension;
	}

	public void setExtension(java.lang.String extension) {
		this.extension = extension;
	}

	public java.lang.String getMmsId() {
		return mmsId;
	}

	public void setMmsId(java.lang.String mmsId) {
		this.mmsId = mmsId;
	}

	public java.lang.String getMmsSubject() {
		return mmsSubject;
	}

	public void setMmsSubject(java.lang.String mmsSubject) {
		this.mmsSubject = mmsSubject;
	}

	public java.lang.String getRecipient() {
		return recipient;
	}

	public void setRecipient(java.lang.String recipient) {
		this.recipient = recipient;
	}

	public java.lang.String getSender() {
		return sender;
	}

	public void setSender(java.lang.String sender) {
		this.sender = sender;
	}

	public java.lang.String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(java.lang.String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public java.lang.String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(java.lang.String transactionType) {
		this.transactionType = transactionType;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.lang.String getMojiImageId() {
		return mojiImageId;
	}

	public void setMojiImageId(java.lang.String mojiImageId) {
		this.mojiImageId = mojiImageId;
	}

	public int getSend_count() {
		return send_count;
	}

	public void setSend_count(int send_count) {
		this.send_count = send_count;
	}

	public java.lang.String getSource() {
		return source;
	}

	public void setSource(java.lang.String source) {
		this.source = source;
	}

	public java.lang.String getMmsText() {
		return mmsText;
	}

	public void setMmsText(java.lang.String mmsText) {
		this.mmsText = mmsText;
	}

	public java.lang.String getAccountId() {
		return accountId;
	}

	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}

	public java.lang.String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(java.lang.String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public java.lang.String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSpID() {
		return spID;
	}

	public void setSpID(String spID) {
		this.spID = spID;
	}

	public String getSpServiceID() {
		return spServiceID;
	}

	public void setSpServiceID(String spServiceID) {
		this.spServiceID = spServiceID;
	}

	public String getLastFrameFilePath() {
		return lastFrameFilePath;
	}

	public void setLastFrameFilePath(String lastFrameFilePath) {
		this.lastFrameFilePath = lastFrameFilePath;
	}

	public String getMdnAddressFlag() {
		return mdnAddressFlag;
	}

	public void setMdnAddressFlag(String mdnAddressFlag) {
		this.mdnAddressFlag = mdnAddressFlag;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	/**
	 * @return the mmsFeginCode
	 */
	public String getMmsFeginCode() {
		return mmsFeginCode;
	}

	/**
	 * @param mmsFeginCode the mmsFeginCode to set
	 */
	public void setMmsFeginCode(String mmsFeginCode) {
		this.mmsFeginCode = mmsFeginCode;
	}

	/**
	 * @return the beforeSendTime
	 */
	public Date getBeforeSendTime() {
		return beforeSendTime;
	}

	/**
	 * @param beforeSendTime the beforeSendTime to set
	 */
	public void setBeforeSendTime(Date beforeSendTime) {
		this.beforeSendTime = beforeSendTime;
	}

	/**
	 * @return the afterSendTimeSendTime
	 */
	public Date getAfterSendTimeSendTime() {
		return afterSendTimeSendTime;
	}

	/**
	 * @param afterSendTimeSendTime the afterSendTimeSendTime to set
	 */
	public void setAfterSendTimeSendTime(Date afterSendTimeSendTime) {
		this.afterSendTimeSendTime = afterSendTimeSendTime;
	}

	/**
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

}
