
 /*
 * 文 件 名:  BusinessException.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-3-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.exception;



 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：BusinessException    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-3-27 上午10:12:48    
 * 修改人：linch   
 * 修改时间：2013-3-27 上午10:12:48    
 * 修改备注：    
 * @version       
 */

public class CMException extends Exception {

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 7204668294138808766L;
	
	
	 /**
	 * code
	 */
	 
	private int code;
	
	
	 /**
	 * 注释内容
	 */
	 
	private String param;
	
	
	
	public CMException(String message) {
		super( message );
	}

	/* (non-Javadoc)
	 * @see exception.ICrppException#getCode()
	 */
	public int getCode() {
		return this.code;
	}

	/* (non-Javadoc)
	 * @see exception.ICrppException#getParam()
	 */
	public String getParam() {
		return this.param;
	}

	/* (non-Javadoc)
	 * @see exception.ICrppException#setCode(int)
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see exception.ICrppException#setParam(java.lang.String)
	 */
	public void setParam(String param) {
		this.param = param;
	}
	
	public static CMException user( String message, int code, String param ){
		CMException exception = new CMException( message );
		exception.setCode( code );
		exception.setParam( param );
		return exception;
	}

	public static CMException user( String message, int code, Object param ){
		CMException exception = new CMException( message );
		exception.setCode( code );
		exception.setParam( (param == null ? null : param.toString()) );
		return exception;
	}

	public static CMException sys( String message, int code, String param ){
		CMException exception = new CMException( message );
		exception.setCode( code );
		exception.setParam( param );
		return exception;
	}
	
	public static CMException sys( String message, int code, Object param ){
		CMException exception = new CMException( message );
		exception.setCode( code );
		exception.setParam( (param == null ? null : param.toString()) );
		return exception;
	}


}

