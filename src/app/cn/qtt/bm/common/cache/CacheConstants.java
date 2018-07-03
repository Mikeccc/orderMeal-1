package app.cn.qtt.bm.common.cache;

import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.utils.SystemConfig;


public class CacheConstants {
	
	public static CachedData cache = new CachedData();
	
	public static String getParamValueByName(String paramName){
		if(!cache.isEmpty()){
			return cache.getParameterValueByName(paramName);
		}
		else{
			return null;
		}
	}
	
	
	
	
	/**
	* 方法名称:      getCodeValueByCodeTypeAndCodeName  
	* 方法描述:      根据type和name获取value
	* @param codeType
	* @param codeName
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-8 上午11:34:14
	*/ 
	 
	public static String getCodeValueByCodeTypeAndCodeName(String codeType,
			String codeName) {
		if(!cache.isEmpty()){
			return cache.getCodeValueByCodeTypeAndCodeName(codeType,codeName);
		}
		return null;
	}

	/**
	 * 
	* @Title: getCodeShowValueByCodeTypeAndCodeName
	* @Description:根据type和name获取showName
	* @param @param codeType
	* @param @param codeName
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String getCodeShowValueByCodeTypeAndCodeName(String codeType,
			String codeName) {
		if(!cache.isEmpty()){
			return cache.getCodeShowValueByCodeTypeAndCodeName(codeType,codeName);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: getCodeNameByCodeTypeAndCodeValue
	* @Description: 根据type和value获取name
	* @param @param codeType
	* @param @param codeValue
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String getCodeNameByCodeTypeAndCodeValue(String codeType,
			String codeValue) {
		if(!cache.isEmpty()){
			return cache.getCodeNameByCodeTypeByValue(codeType,codeValue);
		}
		return null;
	}
	
	/**
	* 方法名称:      getMapCodeValueByParentCodeTypeAndValue  
	* 方法描述:      根据父节点和类型获取CODE
	* @param parentCodeValue
	* @param subCodeType
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 上午10:36:17
	*/ 
	 
	public static List<Map<String, String>> getMapCodeValueByParentCodeTypeAndValue(
			String parentCodeValue, String subCodeType) {
		if (!cache.isEmpty()) {
			return cache.getMapCodeValueByParentCodeTypeAndValue(
					parentCodeValue, subCodeType);
		}
		return null;
	}
	
	
	/**通过节点获取节点值
	 * 
	 * @param codeChildValue
	 * @param subCodeType
	 * @return
	 */
	public static Map<String, String> getMapCodeValueByChildCodeTypeAndValue(
			String codeChildValue) {
		if (!cache.isEmpty()) {
			return cache.getParentCodeValueByCodeTypeAndValue(codeChildValue, Constants.INDUSTRY_CODE_TYPE);
		}
		return null;
	}
	
	
	
	/**
	* 方法名称:      getMapCodeValueByCodeType  
	* 方法描述:      根据codeType获取code
	* @param subCodeType
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 下午7:27:52
	*/ 
	 
	public static List<Map<String, String>> getMapCodeValueByCodeType(String subCodeType) {
		if (!cache.isEmpty()) {
			return cache.getMapCodeValueByCodeType(subCodeType);
		}
		return null;
	}
	
	
	/**
	* 方法名称:      getIndustryMapCodeByParentCodeTypeAndValue  
	* 方法描述:      根据父节点和类型获取行业CODE
	* @param parentCodeValue
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 上午10:36:19
	*/ 
	 
	public static List<Map<String, String>> getIndustryMapCodeByParentCodeTypeAndValue(
			String parentCodeValue) {
		if (!cache.isEmpty()) {
			return cache.getMapCodeValueByParentCodeTypeAndValue(
					parentCodeValue, Constants.INDUSTRY_CODE_TYPE);
		}
		return null;
	}
	
	
	
	/**
	* 方法名称:      getAreaMapCodeByParentCodeTypeAndValue  
	* 方法描述:      根据父节点和类型获取行政CODE
	* @param parentCodeValue
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 上午10:36:22
	*/ 
	 
	public static List<Map<String, String>> getAreaMapCodeByParentCodeTypeAndValue(
			String parentCodeValue) {
		if (!cache.isEmpty()) {
			return cache.getMapCodeValueByParentCodeTypeAndValue(
					parentCodeValue, Constants.AREA_CODE_TYPE);
		}
		return null;
	}
	
	
	
	/**
	* 方法名称:      getShowCodeByParentCodeTypeAndValue  
	* 方法描述:      
	* @param codeType
	* @param codeValue
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-17 下午4:00:42
	*/ 
	 
	public static String getShowCodeByParentCodeTypeAndValue(
			String codeType,String codeValue) {
		if (!cache.isEmpty()) {
			return cache.getShowNameByCodeTypeByValue(
					codeType, codeValue);
		}
		return null;
	}
	
	public static String getParentCodeByCodeTypeAndCodeName(
			String codeType,String codeName) {
		if (!cache.isEmpty()) {
			return cache.getParCodeByCodeTypeByValue(
					codeType, codeName);
		}
		return null;
	}
	
	
	
	/**
	* 方法名称:      getAreaMapRootCodeByParentCodeTypeAndValue  
	* 方法描述:      根据父节点和类型获取行政根CODE
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 上午10:36:25
	*/ 
	 
	public static List<Map<String, String>> getAreaMapRootCodeByParentCodeTypeAndValue() {
		if (!cache.isEmpty()) {
			return cache.getMapRootCodeValueByParentCodeTypeAndValue(
					Constants.AREA_CODE_TYPE);
		}
		return null;
	}
	
	
	
	
	/**
	* 方法名称:      getIndustryMapRootCodeByParentCodeTypeAndValue  
	* 方法描述:      根据父节点和类型获取行业根CODE
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-6 上午10:36:28
	*/ 
	 
	public static List<Map<String, String>> getIndustryMapRootCodeByParentCodeTypeAndValue() {
		if (!cache.isEmpty()) {
			return cache.getMapRootCodeValueByParentCodeTypeAndValue(
					Constants.INDUSTRY_CODE_TYPE);
		}
		return null;
	}
	
	 /**
	 * 权限不需要验证的
	 */
	 
	public static String NO_VERIFY_REQUEST_PERMISSION(){ return getParamValueByName("no_verify_request_permission");};
	
	/**
	 * 默认密码
	 */
	public static String DEFAULT_PASSWORD(){return getParamValueByName("defalut_password");}
	
	/**
	 * 默认角色类型
	 */
	public static String DEFAULT_ENTERPRIS_ROLE_TYPE(){return getParamValueByName("default_enterpris_role_type");}
	
	/**
	* 方法名称:      ENTERPRIS_IMAGE_LIMIT_HEIGHT_WIDTH  
	* 方法描述:      图片限制高宽
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-10 下午5:04:10
	 */
	 
	public static String ENTERPRIS_IMAGE_LIMIT_HEIGHT_WIDTH(){return getParamValueByName("enterpris_image_limit_height_width");}
	
	
	
	
	/**
	* 方法名称:      ENTERPRISE_TMP_FILE_PATH  
	* 方法描述:      临时文件存放地址
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-10 下午5:33:26
	*/ 
	 
	
	
	public static String ENTERPRISE_TMP_FILE_PATH(){return getParamValueByName("enterprise_tmp_file_path");}
	
	public static String TOP_FILE_PATH(){return getParamValueByName("top_file_path");}
	
	public static String TOP_REPLACE_FILE_PATH(){return getParamValueByName("top_replace_file_path");}
	/**
	 * 
	* @Title: UPLOAD_TXT_FILE_PATH
	* @Description: 通讯录上传TXT文件存放地址
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String UPLOAD_TXT_FILE_PATH(){return getParamValueByName("upload_txt_file_path");}
	
	/**
	* 方法名称:      SYSTEM_RESOURCE_HOME_PATH  
	* 方法描述:      系统资源文件根路径
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-10 下午5:33:41
	*/ 
	 
	public static String SYSTEM_RESOURCE_HOME_PATH(){return getParamValueByName("system_resource_home_path");}
	
	/**
	 * 
	* @Title: LINSHI_UPLOAD_IMAGE_FILE_PATH
	* @Description: 上传图片文件的临时路径
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String LINSHI_UPLOAD_IMAGE_FILE_PATH(){return getParamValueByName("linshi_upload_image_file_path");}
	
	/**
	 * 
	* @Title: ZHENGSHI_UPLOAD_IMAGE_FILE_PATH
	* @Description: 上传图片文件的正式路径
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String ZHENGSHI_UPLOAD_IMAGE_FILE_PATH(){return getParamValueByName("zhengshi_upload_image_file_path");}
	
	
	
	/**
	* 方法名称:      SYSTEM_DOMAIN_PATH  
	* 方法描述:      域名访问
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-10 下午9:41:18
	*/ 
	 
	public static String SYSTEM_DOMAIN_PATH(){return getParamValueByName("system_domain_path");}
	
	
	
	
	/**
	* 方法名称:      ENTERPRISE_RESOURCE_FILE_PATH  
	* 方法描述:      系统资源保存地址
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-11 下午2:18:38
	*/ 
	 
	public static String ENTERPRISE_RESOURCE_FILE_PATH(){return getParamValueByName("enterprise_resource_file_path");}
	
	
	
	/**
	* 方法名称:      ENTERPRISE_ORDER_FILE_PATH  
	* 方法描述:      企业订单发送保存地址
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-15 下午3:39:42
	*/ 
	 
	public static String ENTERPRISE_ORDER_FILE_PATH(){return getParamValueByName("enterprise_order_file_path");}
	
	
	/**
	* 方法名称:      ENTERPRISE_CATEGORY_LIMIT_SIZE  
	* 方法描述:      企业限制数量
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-11 下午8:47:24
	*/ 
	 
	public static String ENTERPRISE_CATEGORY_LIMIT_SIZE(){return getParamValueByName("enterprise_category_limit_size");}
	
	
	/**
	 * 默认企业管理员名称
	 */
	public static String DEFAULT_ENTERPRIS_USER_NAME(){return getParamValueByName("default_enterprise_user_name");}
	/**
	 * 默认角色管理员名称
	 */
	public static String DEFAULT_ENTERPRIS_ROLE_NAME(){return getParamValueByName("default_enterprise_role_name");}
	
	
	
	/**
	* 方法名称:      ORDER_MMS_PENDING_AUDIT_STATUS  
	* 方法描述:      订单待审核状态
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-15 下午4:56:47
	*/ 
	 
	public static String ORDER_MMS_PENDING_AUDIT_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_mms_run_status","pending_audit");}
	
	
	/**
	* 方法名称:      ORDER_MMS_PENDING_NOT_THROUGH_THE_AUDIT_STATUS  
	* 方法描述:      订单审核不通过状态
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-22 下午7:58:02
	*/ 
	 
	public static String ORDER_MMS_PENDING_NOT_THROUGH_THE_AUDIT_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_mms_run_status","not_through_the_audit");}
	
	
	/**
	* 方法名称:      ENTERPRIS_TRANSPARENT_TYPE  
	* 方法描述:      企业透传方式
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-16 下午2:15:20
	*/ 
	 
	public static String ENTERPRIS_TRANSPARENT_TYPE(){return getParamValueByName("enterpris_transparent_type");}
	
	
	
	
	/**
	* 方法名称:      ENTERPRIS_TRANSPARENT_PORT  
	* 方法描述:      企业透传端口
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-16 下午2:16:21
	*/ 
	 
	public static String ENTERPRIS_TRANSPARENT_PORT(){return getParamValueByName("enterpris_transparent_port");}
	
	
	/**
	* 方法名称:      ORDER_MMS_DETAI_ATTACH_IMAGE_TYPE  
	* 方法描述:      订单明细图片类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-15 下午5:31:37
	*/ 
	 
	public static String ORDER_MMS_DETAI_ATTACH_IMAGE_TYPE(){return getCodeValueByCodeTypeAndCodeName("order_mms_detai_attach_type","image");}
	
	
	
	
	
	/**
	* 方法名称:      ORDER_MMS_DETAI_ATTACH_TAIL_FRAME_TYPE  
	* 方法描述:      订单明细尾帧类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-18 下午11:03:15
	*/ 
	 
	public static String ORDER_MMS_DETAI_ATTACH_TAIL_FRAME_TYPE(){return getCodeValueByCodeTypeAndCodeName("order_mms_detai_attach_type","tailFrame");}
	
	
	
	/**
	* 方法名称:      ORDER_MMS_DETAI_ATTACH_TEXT_TYPE  
	* 方法描述:      订单明细文本类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-15 下午5:31:50
	*/ 
	 
	public static String ORDER_MMS_DETAI_ATTACH_TEXT_TYPE(){return getCodeValueByCodeTypeAndCodeName("order_mms_detai_attach_type","text");}
	
	/**
	 * 订单审核通过状态
	 */
	//public static String ORDER_APPROVAL_SUCCESS_STATUS(){return getParamValueByName("order_approval_success_status");}  
	public static String ORDER_APPROVAL_SUCCESS_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_approval_status","success");}  
	/**
	 * 订单审核不通过状态
	 */
	public static String ORDER_APPROVAL_FAIL_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_approval_status","fail");}  
	
	/**
	 * smil文件路径
	 */
	
	public static String ENTERPRISE_SMIL_FILE_PATH(){return getParamValueByName("enterprise_smil_file_path");}
	
	/**
	 * 订单有效状态
	 */
	//public static String ORDER_MMS_SUCCESS_STATUS(){return getParamValueByName("order_mms_success_status");}   
	public static String ORDER_MMS_SUCCESS_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_mms_status","efficient");}   //01
	/**
	 * 订单失效状态
	 */
	//public static String ORDER_MMS_FAIL_STATUS(){return getParamValueByName("order_mms_fail_status");}
	public static String ORDER_MMS_FAIL_STATUS(){return getCodeValueByCodeTypeAndCodeName("order_mms_status","disabled");}   //02
	/**
	 * 系統默认创建角色状态
	 */
	public static String ROLE_SYS_FLAG(){return getCodeValueByCodeTypeAndCodeName("role_sys_status","sys_user");}
	/**
	 * 用户默认创建角色状态
	 */
	public static String ROLE_USER_FLAG(){return getCodeValueByCodeTypeAndCodeName("role_sys_status","enterpris_user");}
	/**
	* 方法名称:      APPROVAL_CONTENT_MODULE  
	* 方法描述:      审核内容模块
	* @return        
	* @Author:      xupj
	* @Create Date: 2013-4-22 下午8:01:24
	*/ 
	 
	public static String ORDER_NOT_THROUTH_AUDIT(){return getCodeValueByCodeTypeAndCodeName("order_mms_run_status","not_through_the_audit");}//08
	
	/**
	* 方法名称:      APPROVAL_CONTENT_MODULE  
	* 方法描述:      审核内容模块
	* @return        
	* @Author:      xupj
	* @Create Date: 2013-4-22 下午8:01:24
	*/ 
	 
	public static String ORDER_THROUTH_AUDIT(){return getCodeValueByCodeTypeAndCodeName("order_mms_run_status","through_the_audit");}//02
	
	/**
	* 方法名称:      MATERIAL_STORE_DIMENSION_SMALL  
	* 方法描述:      小图尺寸
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-18 下午9:40:23
	*/ 
	 
	public static String MATERIAL_STORE_DIMENSION_SMALL(){return getCodeValueByCodeTypeAndCodeName("material_store_dimension","small");}
	
	
	
	/**
	* 方法名称:      MATERIAL_STORE_DIMENSION_MIDDLE  
	* 方法描述:      中图尺寸
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-18 下午9:40:43
	*/ 
	 
	public static String MATERIAL_STORE_DIMENSION_MIDDLE(){return getCodeValueByCodeTypeAndCodeName("material_store_dimension","middle");}
	
	
	
	/**
	* 方法名称:      MATERIAL_STORE_DIMENSION_BIG  
	* 方法描述:      大图尺寸
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-18 下午9:40:52
	*/ 
	 
	public static String MATERIAL_STORE_DIMENSION_BIG(){return getCodeValueByCodeTypeAndCodeName("material_store_dimension","big");}
	
	
	
	/**
	* 方法名称:      MATERIAL_STORE_IMAGE_TYPE  
	* 方法描述:      用户图片素材类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 上午10:57:26
	*/ 
	 
	public static String MATERIAL_STORE_IMAGE_TYPE(){return getCodeValueByCodeTypeAndCodeName("material_store_type","image");}
	
	
	
	
	/**
	* 方法名称:      MATERIAL_STORE_VOICE_TYPE  
	* 方法描述:      用户视频素材类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 下午3:28:54
	*/ 
	 
	public static String MATERIAL_STORE_VOICE_TYPE(){return getCodeValueByCodeTypeAndCodeName("material_store_type","voice");}
	
			
	
	
	/**
	* 方法名称:      MATERIAL_STORE_VIDEO_TYPE  
	* 方法描述:      用户音频素材类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 下午3:28:52
	*/ 
	 
	public static String MATERIAL_STORE_VIDEO_TYPE(){return getCodeValueByCodeTypeAndCodeName("material_store_type","video");}
					
					
	
	/**
	* 方法名称:      MATERIAL_STORE_DEFAULT_IMAGE_TYPE  
	* 方法描述:      资源来源-推荐
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 上午10:57:26
	*/ 
	 
	public static String MATERIAL_STORE_SOURCE_RECOMMEND(){return getCodeValueByCodeTypeAndCodeName("material_store_source","recommend");}
	
	
	/**
	* 方法名称:      MATERIAL_STORE_SOURCE_SYSTEM  
	* 方法描述:       资源来源-系统
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 上午10:57:26
	*/ 
	 
	public static String MATERIAL_STORE_SOURCE_SYSTEM(){return getCodeValueByCodeTypeAndCodeName("material_store_source","system");}
	
	
	/**
	* 方法名称:      MATERIAL_STORE_SOURCE_BIZ  
	* 方法描述:      资源来源-企业
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 上午10:57:26
	*/ 
	 
	public static String MATERIAL_STORE_SOURCE_BIZ(){return getCodeValueByCodeTypeAndCodeName("material_store_source","biz");}
	
	
	
	
	/**
	* 方法名称:      IMAGE_TYPE_CAN_DEAL  
	* 方法描述:      能处理图片类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 下午5:48:30
	*/ 
	 
	public static String IMAGE_TYPE_CAN_DEAL(){return getCodeValueByCodeTypeAndCodeName("image_type","not_deal");}
	
	
	
	/**
	* 方法名称:      IMAGE_TYPE_NOT_DEAL  
	* 方法描述:      不需要处理的图片类型
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-20 下午5:48:45
	*/ 
	 
	public static String IMAGE_TYPE_NOT_DEAL(){return getCodeValueByCodeTypeAndCodeName("image_type","not_deal");}
	
	
	
	
	/**
	* 方法名称:      APPROVAL_CONTENT_MODULE  
	* 方法描述:      审核内容模块
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-22 下午8:01:24
	*/ 
	 
	public static String APPROVAL_CONTENT_MODULE(){return getCodeValueByCodeTypeAndCodeName("approval_module","content_module");}
	

	
	
	
	
	/**
	* 方法名称:      SHOULD_NOT_FILTER_URL  
	* 方法描述:      不进行sql注入验证的
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-23 下午3:44:11
	*/ 
	 
	public static String SHOULD_NOT_FILTER_URL(){return getParamValueByName("should_not_filter_url");}
	
	
	
	/**
	* 方法名称:      LOGS_BEGINSAVENO_SLEEP  
	* 方法描述:      非工作时间日志记录数据库入库频次
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-23 下午5:28:06
	*/ 
	 
	public static String LOGS_BEGINSAVENO_SLEEP(){return getParamValueByName("logs_beginsaveno_sleep");}
	
	
	
	/**
	* 方法名称:      LOGS_BEGINSAVENO_WORKS  
	* 方法描述:      工作时间日志记录数据库入库频次
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-23 下午5:28:08
	*/ 
	 
	public static String LOGS_BEGINSAVENO_WORKS(){return getParamValueByName("logs_beginsaveno_works");}
	
	
	
	
	/**
	* 方法名称:      SYSTEM_MAX_ORDER_DATE_COUNT_NUM  
	* 方法描述:      系统最大当日发送数
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-25 下午8:56:36
	*/ 
	 
	public static String SYSTEM_MAX_ORDER_DATE_COUNT_NUM(){return getParamValueByName("system_max_order_date_count_num");}
	
	/**
	 * 彩漫-文件路径-分隔符 ";"
	 * @return 
	 * @author GeYanmeng
	 * @date 2013-6-19
	 */
	public static final String MMS_SEPARATE_CHAR_FILEPATH() {
		return getCodeValueByCodeTypeAndCodeName("MMS_SEPARATE_CHAR", "mmsFilePath");
	}
	
	
	
	/**
	* 方法名称:      CONSUMER_LOGIN_PASSWORD_TEMPLATE  
	* 方法描述:      用户登录密码模版
	* @return        
	* @Author:      linch
	* @Create Date: 2013-6-27 下午5:22:23
	*/ 
	 
	public static final String CONSUMER_LOGIN_PASSWORD_TEMPLATE() {
		return getParamValueByName("consumer_login_password_template");
	}
	
	/**
	* 方法名称:      QRCODE_IMG_PATH  
	* 方法描述:      二维码图片存放路径
	* @return        
	* @Author:      hongjm
	* @Create Date: 2013-6-28 16:22:05
	*/ 
	public static final String QRCODE_IMG_EXIST_PATH() {
		return getParamValueByName("qrcode_img_exist_path");
	}
	public static final String BESPEAKMEAL_SYSTEM_PATH() {
		return getParamValueByName("bespeakmeal_system_path");
	}
	/**
	 * 
	* @Title: BM_START_TIME
	* @Description: 订餐开始时间
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static final String BM_START_TIME() {
		return getParamValueByName("bm_start_time");
	}
	/**
	 * 
	* @Title: BM_END_TIME
	* @Description: 订餐结束时间
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static final String BM_END_TIME() {
		return getParamValueByName("over_time");
	}
	/**
	 * 
	* @Title: ORDER_COUNT_END_TIME
	* @Description: 订餐统计结束时间
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static final String ORDER_COUNT_END_TIME() {
		return getParamValueByName("order_count_end_time");
	}
	/**
	 * 
	* @Title: CATEGORY_MAX_NUM
	* @Description: 分组的最多个数
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static final String CATEGORY_MAX_NUM() {
		return getParamValueByName("category_max_num");
	}
	/**
	 * 从配置文件根据Key取得Value
	 * 
	 * @param keyName
	 * @return
	 */
	public static String getPropertiesValueByName(String keyName) {
		String ret = "";
		SystemConfig conf = new SystemConfig();
		ret = conf.parseParam(keyName, false);
		return ret;
	}
	/**
	 * 原图访问路径(前缀)
	 */
	public static final String ORIGINAL_IMAGE_HTTP_PATH_PREFIX = getPropertiesValueByName("original.image.http.path.prefex");
	/**
	 * 原图访问路径(前缀)
	 */
	public static final String LOGIN_IDENTIFIER = getPropertiesValueByName("login.identifier");
	
	/**
	* 方法名称:      MAX_FAIL_LOGIN_PASSWORD_RESET_NUM  
	* 方法描述:      用户最大登录失败密码重置次数
	* @return        
	* @Author:      linch
	* @Create Date: 2013-7-19 上午11:51:53
	*/ 
	 
	public static final String MAX_FAIL_LOGIN_PASSWORD_RESET_NUM() {
		return getParamValueByName("max_fail_login_password_reset_num");
	}
}

