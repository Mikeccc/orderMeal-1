package app.cn.qtt.bm.scheduler.pojo;

public class CSendSmsRequest {

	private Long id;

	private java.lang.String accountId;

	private java.lang.String accountPwd;

	private java.lang.String authenticator;

	private java.lang.String businessId;

	private java.lang.String extension;

	private java.lang.String smsId;

	private java.lang.String mojiImageId;

	private java.lang.String mmsSubject;

	private java.lang.String recipient;

	private java.lang.String sender;

	private java.lang.String timeStamp;

	private java.lang.String transactionType;

	private java.lang.String userId;

	private java.lang.String mmsText;

	private java.lang.String source;

	private int send_count;

	private java.lang.String content;

	private String tableName;

	private String batchId;

	// add private
	private String spID;
	private String spServiceID;
	// 尾帧对应路径
	private String lastFrameFilePath;
	// 透传类型
	private String mdnAddressFlag;
	// 透传号码 即 发送地址
	private String senderAddress;
	// cpCode 分成的情况下才有CPCODE
	private String cpCode;
	// 短信发送内容
	private String smsSentContent;
	//短信流水号(外部主键)
	private String bufferSn;
	//短信发送类型
	private String msgSendType;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getSmsSentContent() {
		return smsSentContent;
	}

	public void setSmsSentContent(String smsSentContent) {
		this.smsSentContent = smsSentContent;
	}

	public java.lang.String getSmsId() {
		return smsId;
	}

	public void setSmsId(java.lang.String smsId) {
		this.smsId = smsId;
	}

	/**
	 * @return the bufferSn
	 */
	public String getBufferSn() {
		return bufferSn;
	}

	/**
	 * @param bufferSn the bufferSn to set
	 */
	public void setBufferSn(String bufferSn) {
		this.bufferSn = bufferSn;
	}

	/**
	 * @param senderAddress the senderAddress to set
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	/**
	 * @return the senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * @return the msgSendType
	 */
	public String getMsgSendType() {
		return msgSendType;
	}

	/**
	 * @param msgSendType the msgSendType to set
	 */
	public void setMsgSendType(String msgSendType) {
		this.msgSendType = msgSendType;
	}

}
