/**
 * 
 */
package app.cn.qtt.bm.common.constant;

import app.cn.qtt.bm.common.utils.MD5Encrypt;

/**
 * @author GeYanmeng
 * @Description 常量类
 * @date 2013-6-13 上午9:46:12
 * @type Constants
 * @project BespeakMeal
 */
public class Constants {
	
	//数据库操作返回字段 start
	public static final String INSERT_SUCCESS_CODE="00";
	public static final String UPDATE_SUCCESS_CODE="00";
	public static final String DELETE_SUCCESS_CODE="00";
	public static final String SELECT_SUCCESS_CODE="00";
	
	public static final String INSERT_FAIL_CODE="INSERT_FAIL";
	public static final String UPDATE_FAIL_CODE="UPDATE_FAIL";
	public static final String DELETE_FAIL_CODE="DELETE_FAIL";
	public static final String SELECT_FAIL_CODE="SELECT_FAIL";
	//数据库操作返回字段 end
	/**
	 * 彩信发送状态,未完成
	 */
	public static final String MMS_RUN_STATUS_UNFINISH = "00";
	/**
	 * 彩信发送状态,待发送
	 */
	public static final String MMS_RUN_STATUS_TO_BE_SENT = "01";
	/**
	 * 彩信发送状态,发送中
	 */
	public static final String MMS_RUN_STATUS_SENDING = "02";
	/**
	 * 彩信发送状态,暂停
	 */
	public static final String MMS_RUN_STATUS_PAUSE = "03";
	/**
	 * 彩信发送状态,发送完成
	 */
	public static final String MMS_RUN_STATUS_FINISH = "04";

	/**
	 * 批量发送文件存储前缀
	 */
	public static final String BATCH_FILE_PATH = "batch_file_path";
	
	

	 /**
	 * 登录
	 */
	 
	public static final String LOGIN = "login";
	
	
	 /**
	 * 首页
	 */
	 
	public static final String HOME = "home";
	
	
	
	
	 /**
	 * 登录页面重定向
	 */
	 
	public static final String LOGINLOCATION = "loginLocation";
	
	
	 /**
	 * 用户菜单类别
	 */
	 
	public static final String USER_MENU_TYPE = "00";
	
	
	 /**
	 * 开通系统菜单类别
	 */
	 
	public static final String SYSTEM_MENU_TYPE = "03";
	
	
	 /**
	 * 发送系统菜单类别
	 */
	 
	public static final String BIZ_MENU_TYPE = "02";
	
	
	 /**
	 * 系统模块
	 */
	 
	public static final String MODULE_TYPE_SYSTEM = "01";
	
	
	 /**
	 * 订单模块
	 */
	 
	public static final String MODULE_TYPE_ORDER = "02";
	
	
	
	
	 /**
	 * 企业CODE长度
	 */
	 
	public static final Integer ENTERPRISE_CODE_LENGTH = 12;
	
	
	 /**
	 * 用户CODE长度
	 */
	 
	public static final Integer USER_CODE_LENGTH = 11;
	
	
	/**
	 * 角色CODE长度
	 */
	 
	public static final Integer ROLE_CODE_LENGTH = 11;
	
	
	
	 /**
	 * 企业主题长度
	 */
	 
	public static final Integer ENTERPRISE_CATEGORY_CODE_LENGTH = 18;
	
	
	 /**
	 * 订单CODE长度
	 */
	 
	public static final Integer ENTERPRISE_ORDER_CODE_LENGTH = 17;
	
	
	
	 /**
	 * 素材长度
	 */
	 
	public static final Integer ENTERPRISE_MATERIAL_STROE_CODE_LENGTH = 13;
	
	 /**
	 * 有效状态
	 */
	 
	public static final String EFFECTIVE_STATUS = "01";
	
	public static final String NO_EFFECTIVE_STATUS = "02";
	
	
	public static final String SEQ_ROLE_TYPE = "_role";
	
	
	public static final String SEQ_CATEGORY_TYPE = "_category";
	
	public static final String SEQ_ORDER_TYPE = "_order";
	
	public static final String SEQ_MATERIAL_STROE_TYPE = "_material_stroe";
	
	public static final String ORDER_MMS_TYPE_02 = "02";
	
	
	 /**
	 * 验证码长度
	 */
	 
	public static final Integer CAPTCHA_CODE_LENGTH = 5;
	
	 /**
	 * biz用户userbean保存session名字
	 */
	 
	public static final String SESSION_ORDER_USER_NAME = "user_order_bean";
	
	
	 /**
	 * system用户userbean保存session名字
	 */
	 
	public static final String SESSION_SYSTEM_USER_NAME = "user_system_bean";
	
	
	 /**
	 * order用户登录日志
	 */
	 
	public static final String SESSION_ORDER_USER_LOGIN_LOG_NAME = "user_order_login_log";
	
	
	 /**
	 * system用户登录日志
	 */
	 
	public static final String SESSION_SYSTEM_USER_LOGIN_LOG_NAME = "user_system_login_log";
	
	
	/**
	 * login 令牌名
	 */
	public static final String SESSION_LOGIN_TOKEN_NAME = "login_token";
	
	/**
	 * 发送 令牌名
	 */
	public static final String SESSION_SEND_TOKEN_NAME = "send_token";
	
	/**
	 * 下单  令牌名
	 */
	public static final String ADD_ORDDER_TOKEN_NAME = "add_order_token";
	
	 /**
	 * biz命名空间
	 */
	 
	public static final String NAMESPACE_ORDER_NAME = "order";
	
	
	
	 /**
	 * 默认企业分类
	 */
	 
	public static final String DEFAUL_ENTERPRIS_CATEGORY_CODE = "-1";
	
	
	
	 /**
	 * 企业图片每页显示图片
	 */
	 
	public static final Integer ENTERPRIS_IMAGE_PAGE_SIZE = 10;
	
	
	
	 /**
	 * 企业历史每页显示图片数
	 */
	 
	public static final Integer ENTERPRIS_ORDER_PAGE_SIZE = 10;
	
	
	
	 /**
	 * 素材图片类型
	 */
	 
	public static final String MATERIAL_STORE_IMAGE_TYPE = "01";
	
	
	public static final String SERVICE_SUCCESS_CODE="00";
	
	
	 /**
	 * system命名空间
	 */
	 
	public static final String NAMESPACE_SYSTEM_NAME = "system";
	
	
	public static final String  ORDER_MMS_RUN_STATUS = "order_mms_run_status";
	
	public static final String INDUSTRY_CODE_TYPE = "CODE_INDUSTRY";
	
	public static final String USER_LEVEL_CODE = "user_level_code"; 
	public static final String USER_LEVEL_SYSTEM = "system";	////用户管理水平名称系统管理员
	public static final String USER_LEVEL_BASE = "base";		//用户管理水平名称基地
	public static final String USER_LEVEL_PROVICE = "province";	//用户管理水平名称省级	
	public static final String USER_LEVEL_CITY = "city";		//用户管理水平名称市级
	public static final String USER_LEVEL_ROLE_LINK = "user_level_role_link";  
	
	public static final String ENTERPRISE_ADMIN_ROLE_NAME = "企业管理员"; 
	
	public static final String AREA_CODE_TYPE = "CODE_AREA";
	
	public static final String AREA_CODE_CHINE = "000000";
	
	public static final String USER_LEVLE_CODE_TYPE="user_level_code";
	public static final String COME_FROM_LOGIN = "login";
	

	public static final String CAPTCHA_LOGIN_MESSAGE = "您的集团贺卡登录验证码为：${1}，该条验证码有效时间为10分钟。";
	
	
	public static final Integer  CAPTCHA_LOGIN_TYPE = 1;
	
	public static final Integer  CAPTCHA_MAX_SEND_COUNT = 3;
	

	/**
	 * ,默认透传类型
	 */
	public static final String ENTERPRISTRANSPARENTTYPE="01";


	/**
	 * 企业管理员 默认权限
	 */
	public static final String ENTERPRISE_ADMIN_MENU_TYPE = "02";

	/**
	 * 企业用户默认开启状态
	 */
	public static final String ENTERPRISE_ADMIN_ONLINE_STATUS ="01";
	
	/**
	 *  企业用户默认注销状态
	 */
	public static final String ENTERPRISE_ADMIN_CANEL_STATUS ="02";
	/**
	 * 企业角色默认有效状态
	 */
	public static final String ENTERPRISE_ADMIN_ROLE_STATUS = "01";
	/**
	 * 企业角色默认开通类型
	 */
	public static final String ENTERPRISE_ADMIN_ROLE_TYPE="02";
	/**
	 * 用户默认开启状态
	 */
	public static final String USER_ADMIN_ONLINE_STATUS ="01";
	/**
	 * 用户默认注销状态
	 */
	public static final String USER_ADMIN_CANEL_STATUS ="02";
	/**
	 * 一些常用SQL语句
	 */
	public static final String QUERY_FOR_TABLE="select TABLE_NAME from INFORMATION_SCHEMA.TABLES";//查询表格
	
	
	/**
	 * 订单运行状态00-预订购 01-已订购 02-发送中 03-发送完成 04-已领取 05-取消 06-过期
	 * 订单状态00预定
	 */
	public static final String ORDER_RUN_STATUS_0="order_run_status_0";
	
	/**
	 * 订单状态01已订购
	 */
	public static final String ORDER_RUN_STATUS_1="order_run_status_1";
	/**
	 * 订单状态02发送中
	 */
	public static final String ORDER_RUN_STATUS_2="order_run_status_2";
	/**
	 * 订单状态03发送完成
	 */
	public static final String ORDER_RUN_STATUS_3="order_run_status_3";
	/**
	 * 订单状态04已领取
	 */
	public static final String ORDER_RUN_STATUS_4="order_run_status_4";
	/**
	 * 订单状态05-取消
	 */
	public static final String ORDER_RUN_STATUS_5="order_run_status_5";
	/**
	 * 订单状态06-过期
	 */
	public static final String ORDER_RUN_STATUS_6="order_run_status_6";
	/**
	 * 彩信buffer状态生效
	 */
	public static final String MMS_STATUS_01="01";
	/**
	 * 彩信buffer状态失效
	 */
	public static final String MMS_STATUS_02="02";
	/**
	 * 彩信buffer表运行状态 00-未完成  
	 */
	public static final String BUFFER_RUN_STATUS_00="00";
	/**
	 * 彩信buffer表运行状态01-待发送 
	 */
	public static final String BUFFER_RUN_STATUS_01="01";
	/**
	 * 彩信buffer表运行状态02-发送中 
	 */
	public static final String BUFFER_RUN_STATUS_02="02";
	/**
	 * 彩信buffer表运行状态03-暂停
	 */
	public static final String BUFFER_RUN_STATUS_03="03";
	/**
	 * 彩信buffer表运行状态04-发送完成
	 */
	public static final String BUFFER_RUN_STATUS_04="04";
	/**
	 * 彩信发送状态-发送成功
	 */
	public static final String MMS_HISTORY_SEND_STATUS_00="00";
	/**
	 * 彩信发送状态-发送失败
	 */
	public static final String MMS_HISTORY_SEND_STATUS_01="01";
	
	/**
	 * 彩信发送类型 01为二维码
	 */
	public static final String MMS_TYPE_01 = "01";
	/**
	 * 彩信SpID
	 */
	public static final String MMS_SP_ID = "mms_sp_id";
	/**
	 * 彩信SpServiceID
	 */
	public static final String MMS_SP_SERVICE_ID = "mms_sp_service_id";
	/**
	 * 彩信SP密码
	 */
	public static final String MMS_SP_PASSWORD = "mms_sp_password";
	/**
	 * 彩信发送URL
	 */
	public static final String MMS_SEND_URL = "mms_send_url";
	/**
	 * 彩信接收URL
	 */
	public static final String MMS_RECEIVE_URL = "mms_receive_url";
	/**
	 * 彩信发送地址
	 */
	public static final String MMS_SEND_ADDRESS = "mms_send_address";
	/**
	 * 彩信主题
	 */
	public static final String MMS_SUBJECT = "mms_subject";
	
	/**
	 * 短信发送状态-未完成00
	 */
	public static final String SMS_STATUS_00 = "00";
	/**
	 * 短信发送状态-可发送01
	 */
	public static final String SMS_STATUS_01 = "01";
	/**
	 * 短信发送状态-发送中02
	 */
	public static final String SMS_STATUS_02 = "02";
	/**
	 * 短信发送状态-暂停03
	 */
	public static final String SMS_STATUS_03 = "03";
	/**
	 * 短信发送状态-发送完成04
	 */
	public static final String SMS_STATUS_04 = "04";
	/**
	 * 短信发送历史状态-0表示失败
	 */
	public static final int SMS_HISTORY_STATUS_0 = 0;
	/**
	 * 短信发送历史状态-1表示成功
	 */
	public static final int SMS_HISTORY_STATUS_1 = 1;
	/**
	 * 短信类型 01-密码重置
	 */
	public static final String SMS_TYPE_01 = "01";
	/**
	 * 短信类型 02-商家短信通知
	 */
	public static final String SMS_TYPE_02 = "02";
	/**
	 * 短信发送url
	 */
	public static final String SMS_SEND_URL = "sms_send_url";
	/**
	 * 短信设备密码
	 */
	public static final String DEVICE_PASSWORD = "device_password";
	/**
	 * 短信发送人号码
	 */
	public static final String SMS_SENDER_NAME = "sms_sender_name";
	/**
	 * 短信来源地址
	 */
	public static final String SMS_SOURCE_ADDR = "sms_source_addr";
	/**
	 * 短信SpID
	 */
	public static final String SMS_SP_ID = "sms_sp_id";
	/**
	 * 短信SpServiceID
	 */
	public static final String SMS_SP_SERVICE_ID = "sms_sp_service_id";
	
	/**
	 * 每日统计信息发送状态-未发送
	 */
	public static final String SHOP_DAILY_STATUS_00 = "00";
	/**
	 * 每日统计信息发送状态-发送中
	 */
	public static final String SHOP_DAILY_STATUS_01 = "01";
	/**
	 * 每日统计信息发送状态-已完成
	 */
	public static final String SHOP_DAILY_STATUS_02 = "02";
	
	/**
	 * 邮件类型（01-商家接收订餐信息）
	 */
	public static final String BUFFER_EMAIL_TYPE = "01";
	
	/**
	 * 邮件发送状态-未完成00
	 */
	public static final String EMAIL_STATUS_00 = "00";
	/**
	 * 邮件发送状态-可发送01
	 */
	public static final String EMAIL_STATUS_01 = "01";
	/**
	 * 邮件发送状态-发送中02
	 */
	public static final String EMAIL_STATUS_02 = "02";
	/**
	 * 邮件发送状态-发送完成03
	 */
	public static final String EMAIL_STATUS_03 = "03";
	/**
	 * 邮件发送历史状态-00表示成功
	 */
	public static final String EMAIL_HISTORY_STATUS_00 = "00";
	/**
	 * 邮件发送历史状态-01表示失败
	 */
	public static final String EMAIL_HISTORY_STATUS_01 = "01";
	/**
	 * 邮件是否需要身份验证-00需要
	 */
	public static final String EMAIL_VALIDATE_00 = "00";
	/**
	 * 邮件是否需要身份验证-01不需要
	 */
	public static final String EMAIL_VALIDATE_01 = "01";
	/**
	 * 邮件附件列表分隔符
	 */
	public static final String EMAIL_ATTACHFILES_SPLIT_CODE = ";";
	/**
	 * 邮件发送地址
	 */
	public static final String EMAIL_FROM_ADDRESS = "email_from_address";
	/**
	 * 邮件发送方用户名
	 */
	public static final String EMAIL_USER_NAME = "email_user_name";
	/**
	 * 邮件发送方密码
	 */
	public static final String EMAIL_USER_PASSWORD = "email_user_password";
	/**
	 * 邮件主题
	 */
	public static final String EMAIL_SUBJECT = "email_subject";
	/**
	 * 邮件发送服务器地址
	 */
	public static final String SMTP_HOST = "smtp_host";
	/**
	 * 邮件发送服务器端口
	 */
	public static final String SMTP_PORT = "smtp_port";
	
	/**
	 * 彩信列表分隔符
	 */
	public static final String MMS_ATTACHFILES_SPLIT_CODE = ";";
	
	/**
	 * 添加商品生效状态
	 */
	public static final String ORDER_GOOD_STATUS_00 = "00";
	 /**
	  * 添加商品失效状态
	  */
	public static final String ORDER_GOOD_STATUS_01 = "01";
	/**
	 * 默认管理员角色id
	 */
	public static final Integer DEFAULT_ROLE_ID_ADMIN = 1;
	/**
	 * 订餐人员默认角色id
	 */
	public static final Integer DEFAULT_ROLE_ID_CONSUMER = 2;
	/**
	 * 供应商默认角色id
	 */
	public static final Integer DEFAULT_ROLE_ID_MERCHANT = 3;
	
	/**
	 * 店铺默认开通状态
	 */
	public static final String SHOP_EFFECT_STATUS_00 ="00";
	/**
	 * 店铺失效状态
	 */
	public static final String SHOP_FAILURE_STATUS = "01";
	/**
	 * 用户类型（02-供应商）
	 */
	public static final String USER_TYPE_02 ="02";	
	/**
	 * 用户有效状态
	 */
	public static final String USER_STATUS_01 ="01";	
	/**
	 * 用户失效状态
	 */
	public static final String USER_STATUS_02 ="02";
	/**
	 * 用户违规状态
	 */
	public static final String USER_STATUS_03 ="03";
	/**
	 * 分组有效状态
	 */
	public static final String GOOD_CATEGORY_STATUS_00 ="00";	
	/**
	 * 分组失效状态
	 */
	public static final String GOOD_CATEGORY_STATUS_01 ="01";
	 /**
	 * 用户类型管理员
	 */
	 
	public static final String USER_TYPE_ADMIN ="03";
	 /**
	 * 用户类型店铺
	 */
	 
	public static final String USER_TYPE_SHOP ="02";
	 /**
	 * 用户类型客户
	 */
	 
	public static final String USER_TYPE_CUSTOMER ="01";
	/**
	 * 短信发送用
	 */
	public static final String SOAP_HEADER_PREFIX_4_SMS = "v2";

	/**
	 * smil文件存放路径
	 */
	public static final String SMIL_FILEPATH = "smil_filepath";
	/**
	 * 尾帧路径
	 */
	public static final String LAST_FRAME_FILE_PATH = "last_frame_file_path";
	/**
	 * 默认最大每页记录数
	 */
	public static final int DEFAULT_PAGERECORDS = 20;
	
	/**
	 * 时间参数，“00”表示每天
	 */
	public static final String TIME_CODE_EVERYDAY = "00";
	/**
	 * 时间参数，“01”表示周一
	 */
	public static final String TIME_CODE_MONDAY = "01";
	/**
	 * 时间参数，“02”表示每二
	 */
	public static final String TIME_CODE_TUESDAY = "02";
	/**
	 * 时间参数，“03”表示周三
	 */
	public static final String TIME_CODE_WEDNESDAY = "03";
	/**
	 * 时间参数，“04”表示周四
	 */
	public static final String TIME_CODE_THURSDAY = "04";
	/**
	 * 时间参数，“05”表示周五
	 */
	public static final String TIME_CODE_FRIDAY = "05";
	
	/**
	 * 时间参数，表示每天
	 */
	public static final String TIME_CODE_LIST= "00,01,02,03,04,05";
	/**
	 * 上传图片生效
	 */
	public static final String SYS_FILE_00= "00";
	/**
	 * 上传图片失效
	 */
	public static final String SYS_FILE_01= "01";
	
	/**
	 * 订餐的网站
	 */
	public static final String WAP_URL = "wap_url";
	
	/**
	 * 用户在线状态 在线
	 */
	public static final String IS_ONLINE_TRUE = "01";
	
	/**
	 * 用户在线状态 在线
	 */
	public static final String IS_ONLINE_FALSE = "02";
	/**
	 * 用户初始密码
	 */
	public static final String ORIGINAL_PASSWORD = MD5Encrypt.MD5Encode("123456");
	
	
	
	 /**
	 * 短信发送类型 用户登录密码重置
	 */
	 
	public static final String SMS_TYPE_CONSUMER_PASSWORD = "01";
	
	/**
	 * 默认添加购物车商品个数
	 */
	public static final String DEFAULT_SHOP_CART_NUM_1 ="1";
	

	/**
	 * 订单运行状态 00-预订购
	 */
	public static final String ORDER_RUN_STATUS_PREORDER = "00";
	/**
	 * 订单运行状态 01-已订购
	 */
	public static final String ORDER_RUN_STATUS_ORDER = "01";
	/**
	 * 订单运行状态 02-发送中
	 */
	public static final String ORDER_RUN_STATUS_SENDING = "02";
	/**
	 * 订单运行状态 03-发送完成
	 */
	public static final String ORDER_RUN_STATUS_SEND = "03";
	/**
	 * 订单运行状态 04-已领取
	 */
	public static final String ORDER_RUN_STATUS_FINISH = "04";
	/**
	 * 订单运行状态 05-取消
	 */
	public static final String ORDER_RUN_STATUS_CANCEL = "05";
	/**
	 * 订单运行状态 06-过期
	 */
	public static final String ORDER_RUN_STATUS_EXPIRED = "06";
}
