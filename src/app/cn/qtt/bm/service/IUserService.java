package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

/**
 * 
 * @author GeYanmeng
 * @Description 用户管理接口
 * @date 2013-6-9 下午4:43:02
 * @type IUserService
 * @project BespeakMeal
 */
public interface IUserService {
	
	/**
	 * 精确查询用户
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean queryUserById(UserRequestBean userRequestBean);
	
	/**
	 * 模糊查询用户(分页)
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean queryFuzzyPagesUser(UserRequestBean userRequestBean);
	
	/**
	 * 修改用户信息
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean modifyUserInfo(UserRequestBean userRequestBean);
	
	/**
	 * 修改用户,角色关联信息
	 * @param userRequestBean
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean modifyLinkUserRoles(UserRequestBean userRequestBean);
	
	/**
	 * 增加用户信息
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean addUserInfo(UserRequestBean userRequestBean);
	
	/**
	 * 增加用户,角色关联信息
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean addLinkUserRoles(UserRequestBean userRequestBean);
	
	/**
	 * 删除用户
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean deleteUserInfo(UserRequestBean userRequestBean);
	
	/**
	 * 删除用户，物理删除
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author Gabriel
	 * @date 2013-7-1
	 */
	public UserResponseBean deleteUserInfoPermanently(UserRequestBean userRequestBean);
	
	/**
	 * 删除用户,角色关联信息
	 * @param userRequestBean
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public UserResponseBean deleteLinkUserRole(UserRequestBean userRequestBean);
	
    /**
	 * 增加使用方用户
	 * @param UserBean
	 * @return UserResponseBean,RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean saveSimpleUser(UserRequestBean userRequestBean,RoleRequestBean roleRequestBean);
	
	/**
	 * 根据企业编码查询企业用户
	 * @param UserRequestBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean queryForUser(UserRequestBean userRequestBean);
	/**
	 * 根据用户属性查询
	 * @param UserBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean queryForUserByUserProperties(UserRequestBean userRequestBean);
	
	/**
	 * 根据用户属性查询
	 * @param UserBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean fuzzyQueryForUserByUserProperties(UserRequestBean userRequestBean);
	
	/**
	 * 注销用户
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean forbidUser(UserRequestBean userRequestBean);
	
	/**
	 * 批量注销用户
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean forbidBatchUser(UserRequestBean userRequestBean);
	
	/**
	 * 修改用户属性
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean updateUser(UserRequestBean userRequestBean);
	/**
	 * 修改用户角色
	 * @param userRequestBean
	 * @return UserResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public UserResponseBean updateUser(UserRequestBean userRequestBean,RoleRequestBean roleRequest);

	/**
	 * 根据岗位编码查找用户
	 * @author JJF
	 * @param requestBean 
	 * @return PostInfoResponseBean
	 * @since 2013-4-7
	 */
	public UserResponseBean queryUserBySysPost(UserRequestBean requestBean);
	
	/**
	 * 查询违规用户
	 * @param requestBean
	 * @return UserResponseBean
	 * @author Gabriel
	 * @date 2013-6-28
	 */
	public UserResponseBean queryIllegalUser(UserRequestBean requestBean);

	/**
	 * 不分页查询违规用户
	 * @param dayTime 查询日期
	 * @return UserResponseBean
	 * @author huangwj
	 * @date 2013-6-28
	 */
	public UserResponseBean queryIllegalUser(String dayTime);
	
	/**
	 * 查询用户根据Example
	 *方法名称 queryForUserByExample
	 *方法描述
	 * @param requestBean
	 * @return
	 * @Date 2013-6-28
	 * @author xupj
	 */
	public UserResponseBean queryForUserByExample(UserRequestBean requestBean);

	/**
	 * 15天未修改密码进行失效
	 * @return UserResponseBean
	 * @author Gabriel
	 * @createtime 2013-8-24 下午9:32:57
	 */
	public UserResponseBean passwordOverTime();
	
	/**
	 * 根据userCode查询用户手机号码，无视user是否为生效状态
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-9-25 上午11:16:50
	 */
	public UserResponseBean queryUserPhoneNumberByUserCode(UserRequestBean requestBean);

}
