package app.cn.qtt.bm.manage;

import java.util.List;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

public interface IUserMgr {
	
	
	
	/**
	* 方法名称:      queryUserLogin  
	* 方法描述:      登录逻辑
	* @param userCode
	* @param userPassword
	* @return
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-17 下午5:24:57
	*/ 
	 
	public TSysUserInfo queryUserLogin(String userCode, String userPassword)
			throws CMException,CMRollBackException;
	
	
	
	
	/**
	* 方法名称:      queryUserLogin  
	* 方法描述:      登录逻辑
	* @param userPhoneNumber手机号码
	* @param userPassword	密码
	* @param userType		用户类型
	* @return				用户信息
	* @throws CMException
	* @throws CMRollBackException
	* @Author:      linch
	* @Create Date: 2013-4-1 下午7:32:29
	*/ 
	 
	public TSysUserInfo queryUserLogin(String userPhoneNumber, String userPassword,
			String userType) throws CMException,CMRollBackException;
	
	
	/**
	* 方法名称:      saveResetConsumerPasswordToSms  
	* 方法描述:      根据用户重置密码
	* @param userBean
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-27 下午7:31:25
	*/ 
	 
	public void saveResetConsumerPasswordToSms(UserBean userBean)
			throws CMException, CMRollBackException;
	
	
	/**
	* 方法名称:      queryUserByUserProperties  
	* 方法描述:      根据用户信息查询用户列表
	* @param user
	* @return
	* @throws CMException
	* @throws CMRollBackException
	* @Author:      linch
	* @Create Date: 2013-4-4 下午9:12:54
	*/ 
	public List<TSysUserInfo> queryUserByUserProperties(TSysUserInfo user)
			throws CMException, CMRollBackException;
	
	
	/**
	* 方法名称:      saveUserLoginFailCount  
	* 方法描述:      保存用户登录失败的逻辑
	* @param userPhoneNumber
	* @param userType
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-7-19 下午2:46:47
	*/ 

	public boolean saveUserLoginFailCount(String userPhoneNumber, String userType)
			throws CMException, CMRollBackException;
	
	/**
	 * 
	* @Title: findUserByUserCode
	* @Description: 通过用户code查找用户
	* @param @param userCode
	* @param @return    设定文件
	* @return TSysUserInfo    返回类型
	* @throws
	 */
	public TSysUserInfo findUserByUserCode(String userCode)throws CMException, CMRollBackException;
	
	
	
	/**
	 * 
	* @Title: logOffUser
	* @Description: 注销用户
	* @param @param adminId
	* @param @return    设定文件
	* @return Boolean    返回类型
	* @throws
	 */
	public Boolean delUser(String currentUserCode,String adminId) throws CMException, CMRollBackException;
	
	
	/**
	 * 
	* @Title: findCheckByUserTelAndType
	* @Description: 检查用户电话是否已经存在
	* @param @param userTel
	* @param @param userType
	* @param @return    设定文件
	* @return Boolen    返回类型
	* @throws
	 */
	public Boolean findCheckByUserTelAndType(String userTel,String userType) throws CMException, CMRollBackException;
	
	/**
	 * 
	* @Title: updateUserPassword
	* @Description: 修改用户密码
	* @param @param sysUserInfo
	* @param @return
	* @param @throws CMException
	* @param @throws CMRollBackException    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	
	public boolean updateUserPassword(TSysUserInfo sysUserInfo) throws CMException, CMRollBackException;


	/**
	 * 查询有效订餐人员
	 * @param user 查询条件
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public UserResponseBean queryValidOrderer(TSysUserInfo user, int currentPage, int pageRecords) throws CMException, CMRollBackException;

	/**
	 * 查询违规订餐人员
	 * @param searchDate 查询日期
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public UserResponseBean queryInvalidOrderer(String searchDate, int currentPage, int pageRecords) throws CMException, CMRollBackException;
	
	/**
	 * 不分页查询违规订餐人员
	 * @param searchDate 查询日期
	 * @return 违规人员列表
	 * @throws CMException
	 * @author tipx
	 * @createtime 2013-8-26 上午1:22:00
	 */
	public List<TSysUserInfo> queryInvalidOrderer(String searchDate) throws CMException;

	/**
	 * 添加订餐人员
	 * @param user 用户信息
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public UserResponseBean addOrderer(TSysUserInfo user) throws CMException, CMRollBackException;

	/**
	 * 修改订餐人员
	 * @param user 用户信息
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public UserResponseBean updateOrderer(TSysUserInfo user) throws CMException, CMRollBackException;

	/**
	 * 修改订餐人员
	 * @param userId 用户id
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public UserResponseBean deleteOrderer(Integer userId) throws CMException, CMRollBackException;

	/**
	 * 设置用户角色
	 * @param userCode  待设置角色的用户编码
	 * @param roleId  角色id
	 * @param operateUser  操作用户
	 * @return UserResponseBean
	 */
	public UserResponseBean addLinkRole(String userCode, Integer roleId, String operateUserCode);

	/**
	 * 清除用户角色关联
	 * @param userCode  待设置角色的用户编码
	 * @param roleId  角色id 传入null则清除该用户编码与角色的所有关联
	 * @return UserResponseBean
	 */
	public UserResponseBean removeLinkRole(String userCode, Integer roleId);
	
	/**
	 * 根据
	 *方法名称 queryOrdererByUserCode
	 *方法描述
	 * @param user
	 * @param currentPage
	 * @param pageRecords
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-9-1
	 * @author xupj
	 */
	public OrderResponseBean queryOrdererByUserCode(String userCode, String createTime) throws CMException, CMRollBackException;
}

