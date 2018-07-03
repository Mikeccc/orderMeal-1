package app.cn.qtt.bm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.MathUtil;
import app.cn.qtt.bm.common.utils.UtilTools;
import app.cn.qtt.bm.dao.TSysLinkUserRoleDAO;
import app.cn.qtt.bm.dao.TSysUserInfoDAO;
import app.cn.qtt.bm.model.TSysLinkUserRole;
import app.cn.qtt.bm.model.TSysLinkUserRoleExample;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserInfoExample;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

public class UserServiceImpl implements IUserService {
	private TSysUserInfoDAO tSysUserInfoDAO;
	private TSysLinkUserRoleDAO tSysLinkUserRoleDAO;
	protected CCrppLog4j log = new CCrppLog4j(UserServiceImpl.class.getName());

	/**
	 * 根据用户编码列表批量修改用户状态
	 * 
	 * @author JJF
	 * @since 2013-4-2
	 */
	public UserResponseBean forbidBatchUser(UserRequestBean userRequestBean) {
		TSysUserInfoExample example = new TSysUserInfoExample();
		UserResponseBean responseBean = new UserResponseBean();
		List<String> userCodeList = userRequestBean.getUserCodeList();
		try {
			example.createCriteria().andUserCodeIn(userCodeList);
			tSysUserInfoDAO.updateUserStatusByExample(userRequestBean.getSysuserinfo(), example);
			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			log.exception(".forbidBatchUser", e);
		}
		return responseBean;
	}

	/**
	 * 根据用户编码修改用户状态
	 * 
	 * @author JJF
	 * @since 2013-4-2
	 */
	public UserResponseBean forbidUser(UserRequestBean userRequestBean) {
		TSysUserInfoExample example = new TSysUserInfoExample();
		UserResponseBean responseBean = new UserResponseBean();
		try {
			example.createCriteria().andUserCodeEqualTo(userRequestBean.getSysuserinfo().getUserCode());// 根据用户编码update
			tSysUserInfoDAO.updateUserStatusByExample(userRequestBean.getSysuserinfo(), example);

			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage().toString());
			log.exception(".forbidUser", e);
		}
		return responseBean;
	}

	public UserResponseBean queryForUserByExample(UserRequestBean userRequestBean){
		UserResponseBean responseBean = new UserResponseBean();
		List<?> list = new ArrayList();
		try{
			list = tSysUserInfoDAO.selectByExample(userRequestBean.getSysUserInfoExample());
			responseBean.setResultList(list);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage().toString());
			log.exception(".queryForUserByExample", e);
		}
		return responseBean;
	}
	
	/**
	 * 根据企业编码查询企业用户
	 * 
	 * @author JJF
	 * @since 2013-4-2
	 */
	public UserResponseBean queryForUser(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysUserInfoExample sysUserInfoExample = new TSysUserInfoExample();
		TSysUserInfoExample.Criteria criteria = sysUserInfoExample.createCriteria();
		List<?> list = new ArrayList();
		int currentPage, pageRecords, totalRecods, totalPages = 0;
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean请求对象为空");
			}
			if (null == userRequestBean.getSysuserinfo()
					|| StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserCode())) {
				throw new Exception("userInfo对象为空或用户企业编码为空");
			}
			currentPage = userRequestBean.getCurrentPage();
			pageRecords = userRequestBean.getPageRecords();
			criteria.andUserCodeEqualTo(userRequestBean.getSysuserinfo().getUserCode());// 传入企业编码条件
			if (!StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserCode())) {// 如果usercode不为空,则加入模糊判断
				criteria.andUserCodeLike("%" + userRequestBean.getSysuserinfo().getUserCode() + "%");
			}
			if (!StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserName())) {// 如果userName不为空,则加入模糊判断
				criteria.andUserNameLike("%" + userRequestBean.getSysuserinfo().getUserName() + "%");
			}
			if (userRequestBean.getCurrentPage() != 0) {// 分页
				list = tSysUserInfoDAO.selectPagesByExample(sysUserInfoExample, currentPage, pageRecords);
				totalRecods = tSysUserInfoDAO.selectCountsByExample(sysUserInfoExample);
				responseBean.setTotalRecords(totalRecods);
				responseBean.setCurrentPage(userRequestBean.getCurrentPage());
				totalPages = (int) Math.ceil(MathUtil.div(totalRecods, pageRecords));
				responseBean.setTotalPages(totalPages);

			} else {
				list = tSysUserInfoDAO.selectByExample(sysUserInfoExample);
			}
			responseBean.setUserlist(list);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		responseBean.setUserlist(list);
		return responseBean;
	}

	/**
	 * 根据用户属性查询实现,实现分页
	 * 
	 * @author JJF
	 * @since 2013-4-2
	 */
	public UserResponseBean queryForUserByUserProperties(UserRequestBean userRequestBean) {

		UserResponseBean responseBean = new UserResponseBean();
		List<?> list = new ArrayList();// 返回记录
		int totalRecords = 0;// 返回总条数
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean为空");
			} else if (null == userRequestBean.getSysuserinfo()) {
				throw new Exception("sysUserInfo为空");
			} else {
				if (userRequestBean.getCurrentPage() != 0) {// 当前页面不为0,则分页
					// 获取总记录数
					totalRecords = tSysUserInfoDAO.selectCountsByUserProperties(userRequestBean.getSysuserinfo());
					
					//计算总页数
					int totalPages = (int) Math.ceil(MathUtil.div(totalRecords, userRequestBean.getPageRecords()));
					int currentPage = userRequestBean.getCurrentPage();
					if(totalPages < 0){
						totalPages = 1;
					}
					//若总页数小于当前页数（比如删除最后一页的最后一条记录），则重置当前页为最后一页
					if(totalPages < currentPage){
						currentPage = totalPages;
						userRequestBean.setCurrentPage(currentPage);
					}
					
					list = tSysUserInfoDAO.selectByUserProperties(userRequestBean.getSysuserinfo(),
							userRequestBean.getCurrentPage(), userRequestBean.getPageRecords());
					responseBean.setTotalRecords(totalRecords);// 返回总记录数
					responseBean.setTotalPages(totalPages);// 返回总页数
					responseBean.setCurrentPage(userRequestBean.getCurrentPage());
				} else {// 不分页
					list = tSysUserInfoDAO.selectByUserProperties(userRequestBean.getSysuserinfo());
				}

				responseBean.setUserlist(list);
				responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		return responseBean;
	}

	/**
	 * 模糊查询企业用户,实现分页
	 * 
	 * @author JJF
	 * @since 2013-4-2
	 */
	public UserResponseBean fuzzyQueryForUserByUserProperties(UserRequestBean userRequestBean) {

		UserResponseBean responseBean = new UserResponseBean();
		List<TSysUserInfo> list = new ArrayList<TSysUserInfo>();// 返回记录
		int totalRecords = 0;// 返回总条数
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean为空");
			} else if (null == userRequestBean.getSysuserinfo()) {
				throw new Exception("sysUserInfo为空");
			} else {
				if (userRequestBean.getCurrentPage() != 0) {// 当前页面不为0,则分页
					// 获取总记录数
					totalRecords = tSysUserInfoDAO.fuzzySelectCountsByUserProperties(userRequestBean.getSysuserinfo());
					
					//计算总页数
					int totalPages = (int) Math.ceil(MathUtil.div(totalRecords, userRequestBean.getPageRecords()));
					int currentPage = userRequestBean.getCurrentPage();
					
					//总页数至少为1
					if(totalPages < 1){
						totalPages = 1;
					}
					
					//若总页数小于当前页数（比如删除最后一页的最后一条记录），则重置当前页为最后一页
					if(totalPages < currentPage){
						currentPage = totalPages;
						userRequestBean.setCurrentPage(currentPage);
					}
					list = tSysUserInfoDAO.fuzzySelectByUserProperties(userRequestBean.getSysuserinfo(),
							userRequestBean.getCurrentPage(), userRequestBean.getPageRecords());

					responseBean.setTotalRecords(totalRecords);// 返回总记录数
					responseBean.setTotalPages(totalPages);// 返回总页数
					responseBean.setCurrentPage(userRequestBean.getCurrentPage());
				} else {// 不分页
					list = tSysUserInfoDAO.fuzzySelectByUserProperties(userRequestBean.getSysuserinfo());
				}

				responseBean.setUserlist(list);
				responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		return responseBean;
	}

	/**
	 * @title 保存普通用户实现
	 * @author JJF
	 * @since 2013-4-1
	 */
	public UserResponseBean saveSimpleUser(UserRequestBean userRequestBean, RoleRequestBean roleRequestBean) {
		UserResponseBean userResponseBean = new UserResponseBean();
		// 初始化用户角色对象
		TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();
		// 初始化用户岗位对象
		// TSysLinkUserPost tSysLinkUserPost=new TSysLinkUserPost();
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean 请求对象为空");
			}
			if (null == roleRequestBean) {
				throw new Exception("roleRequestBean 请求对象为空");
			}
			if (null == userRequestBean.getSysuserinfo()) {
				throw new Exception("SysUserInfo为空");
			} else if (null == roleRequestBean.getSysroleinfo()) {
				throw new Exception("Sysroleinfo为空");
			} else if (StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserCode())) {
				throw new Exception("SysUserCode为空");
			} else if (roleRequestBean.getSysroleinfo().getRoleId() == null) {
				throw new Exception("RoleCode为空");
			} else if (StringUtils.isBlank(userRequestBean.getCurrentUserCode())) {
				throw new Exception("当前用户code为空");
			} else {
				tSysLinkUserRole.setUserCode(userRequestBean.getSysuserinfo().getUserCode());// 设置关联对象用户code
				tSysLinkUserRole.setCreateUserCode(userRequestBean.getSysuserinfo().getCreateUserCode());// 设置关联对象创建用户code
				tSysLinkUserRole.setRoleId(roleRequestBean.getSysroleinfo().getRoleId());// 设置关联对象角色code
				tSysLinkUserRole.setCreateTime(new Date());
				// tSysLinkUserRole.setLinkUserRoleSort(linkUserRoleSort);
				tSysUserInfoDAO.insert(userRequestBean.getSysuserinfo());// 插入用户表
				tSysLinkUserRoleDAO.insert(tSysLinkUserRole);// 插入用户角色关联表

				userResponseBean.setResponseCode(Constants.INSERT_SUCCESS_CODE);
			}
		} catch (Exception e) {
			userResponseBean.setErrMsg(e.getMessage());
			userResponseBean.setException(e);
			userResponseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
		}

		return userResponseBean;
	}

	/**
	 * 用户更新实现
	 * 
	 * @author JJF
	 * @since 2013-4-1
	 */
	public UserResponseBean updateUser(UserRequestBean userRequestBean) {

		UserResponseBean userResponseBean = new UserResponseBean();
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean为空");
			} else if (null == userRequestBean.getSysuserinfo()) {
				throw new Exception("SysUserInfo为空");
			} else if (StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserCode())) {
				throw new Exception("SysUserCode为空");
			} else {
				TSysUserInfoExample example = new TSysUserInfoExample();
				example.createCriteria().andUserCodeEqualTo(userRequestBean.getSysuserinfo().getUserCode());
				tSysUserInfoDAO.updateByExampleSelective(userRequestBean.getSysuserinfo(), example);// 更新用户表
			}
			userResponseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			userResponseBean.setErrMsg(e.getMessage());
			userResponseBean.setException(e);
			userResponseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
		}

		return userResponseBean;
	}

	public TSysUserInfoDAO getTSysUserInfoDAO() {
		return tSysUserInfoDAO;
	}

	public void setTSysUserInfoDAO(TSysUserInfoDAO sysUserInfoDAO) {
		tSysUserInfoDAO = sysUserInfoDAO;
	}

	public TSysLinkUserRoleDAO getTSysLinkUserRoleDAO() {
		return tSysLinkUserRoleDAO;
	}

	public void setTSysLinkUserRoleDAO(TSysLinkUserRoleDAO sysLinkUserRoleDAO) {
		tSysLinkUserRoleDAO = sysLinkUserRoleDAO;
	}

	public UserResponseBean queryUserBySysPost(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		List<?> list = new ArrayList();// 返回记录
		int totalRecords = 0;// 返回总条数
		try {
			if (null == userRequestBean) {
				throw new Exception("userRequestBean为空");
			} else if (null == userRequestBean.getSysuserinfo()) {
				throw new Exception("sysUserInfo为空");
			} else {
				if (userRequestBean.getCurrentPage() != 0) {// 当前页面不为0,则分页
					list = tSysUserInfoDAO.selectByUserProperties(userRequestBean.getSysuserinfo(),
							userRequestBean.getCurrentPage(), userRequestBean.getPageRecords());
					// 获取总记录数
					totalRecords = tSysUserInfoDAO.selectCountsByUserProperties(userRequestBean.getSysuserinfo());
					responseBean.setTotalRecords(totalRecords);// 返回总记录数
					responseBean.setTotalPages(totalRecords / userRequestBean.getPageRecords() + 1);// 返回总页数
					responseBean.setCurrentPage(userRequestBean.getCurrentPage());
				} else {// 不分页
					list = tSysUserInfoDAO.selectByUserProperties(userRequestBean.getSysuserinfo());
				}
				responseBean.setUserlist(list);
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		return responseBean;
	}

	/**
	 * 修改用户包括角色 add by jjf 2013-4-12 15:23
	 */
	public UserResponseBean updateUser(UserRequestBean userRequestBean, RoleRequestBean roleRequest) {
		TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();

		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (userRequestBean == null || userRequestBean.getSysuserinfo() == null
					|| StringUtils.isBlank(userRequestBean.getSysuserinfo().getUserCode())) {
				throw new NullPointerException("userRequestBean请求对象异常");
			}
			if (roleRequest == null || roleRequest.getSysroleinfo() == null
					|| roleRequest.getSysroleinfo().getRoleId() != null) {
				throw new NullPointerException("roleRequestBean请求对象异常");
			}
			// 更新用户表
			TSysUserInfoExample userExample = new TSysUserInfoExample();
			userExample.createCriteria().andUserCodeEqualTo(userRequestBean.getSysuserinfo().getUserCode());
			tSysUserInfoDAO.updateByExample(userRequestBean.getSysuserinfo(), userExample);
			tSysLinkUserRole.setRoleId(roleRequest.getSysroleinfo().getRoleId());
			tSysLinkUserRole.setUserCode(userRequestBean.getSysuserinfo().getUserCode());
			tSysLinkUserRole.setModifyTime(new Date());
			tSysLinkUserRole.setModifyUserCode(userRequestBean.getCurrentUserCode());
			// 更新用户角色关联表
			TSysLinkUserRoleExample roleExample = new TSysLinkUserRoleExample();
			roleExample.createCriteria().andUserCodeEqualTo(userRequestBean.getSysuserinfo().getUserCode());
			tSysLinkUserRoleDAO.updateByExampleSelective(tSysLinkUserRole, roleExample);

			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
		}
		return responseBean;
	}

	@Override
	public UserResponseBean queryUserById(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysUserInfo sysuserinfo = null;
		TSysUserInfoExample example = new TSysUserInfoExample();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			example.createCriteria().andUserIdEqualTo(userRequestBean.getSysuserinfo().getUserId()).andUserStatusEqualTo(Constants.EFFECTIVE_STATUS);
			List<TSysUserInfo> tempList = tSysUserInfoDAO.selectByExample(example);
			if(tempList.size() != 0){
				sysuserinfo = tempList.get(0);
			}

			responseBean.setSysuserinfo(sysuserinfo);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean queryFuzzyPagesUser(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysUserInfoExample example = new TSysUserInfoExample();
		List<TSysUserInfo> list = null;
		int totalRecords = 0;// 总记录数
		int totalPages = 0;// 总页数
		int pageRecords = 0;// 每页的记录数
		int currentPage = 0;// 当前页码
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysUserInfoExample() != null) {
				example = userRequestBean.getSysUserInfoExample();
			}
			if (userRequestBean.getPageRecords() != 0) {
				pageRecords = userRequestBean.getPageRecords();// 如果传进每页记录数则使用
			} else {
				pageRecords = Constants.DEFAULT_PAGERECORDS;// 如果不传,则使用默认最大每页记录数
			}
			if (userRequestBean.getCurrentPage() != 0) {
				currentPage = userRequestBean.getCurrentPage();
			}

			list = tSysUserInfoDAO.selectPagesByExample(example, (currentPage - 1) * pageRecords, pageRecords);
			totalRecords = tSysUserInfoDAO.countByExample(example);
			totalPages = UtilTools.getTotalPages(pageRecords, totalRecords);// 总页数

			responseBean.setTotalPages(totalPages);
			responseBean.setTotalRecords(totalRecords);
			responseBean.setResultList(list);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean modifyUserInfo(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysuserinfo() != null) {
				tSysUserInfoDAO.updateByPrimaryKeySelective(userRequestBean.getSysuserinfo());
			} else {
				throw new Exception("Sysuserinfo对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean modifyLinkUserRoles(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysLinkUserRoleExample example = new TSysLinkUserRoleExample();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysLinkUserRoleExample() != null) {
				example = userRequestBean.getSysLinkUserRoleExample();
			}
			if (userRequestBean.getSysLinkUserRole() != null) {
				tSysLinkUserRoleDAO.updateByExampleSelective(userRequestBean.getSysLinkUserRole(), example);
			} else {
				throw new Exception("SysLinkUserRole对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean addUserInfo(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysuserinfo() != null) {
				tSysUserInfoDAO.insert(userRequestBean.getSysuserinfo());
			} else {
				throw new Exception("Sysuserinfo对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean addLinkUserRoles(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysLinkUserRole() != null) {
				tSysLinkUserRoleDAO.insert(userRequestBean.getSysLinkUserRole());
			} else {
				throw new Exception("SysLinkUserRole对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean deleteUserInfo(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysUserInfoExample example = new TSysUserInfoExample();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysUserInfoExample() != null) {
				example = userRequestBean.getSysUserInfoExample();
			}
			if (userRequestBean.getSysuserinfo() != null) {
				userRequestBean.getSysuserinfo().setUserStatus("02");
				example.createCriteria().andUserCodeEqualTo(Constants.USER_STATUS_02);
				tSysUserInfoDAO.updateByExampleSelective(userRequestBean.getSysuserinfo(), example);
			} else {
				throw new Exception("Sysuserinfo对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}
	
	/* 物理删除用户
	 * @see app.cn.qtt.bm.service.IUserService#deleteUserInfoPermanently(app.cn.qtt.bm.service.pojo.UserRequestBean)
	 */
	@Override
	public UserResponseBean deleteUserInfoPermanently(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysuserinfo() != null) {
				tSysUserInfoDAO.deleteByPrimaryKey(userRequestBean.getSysuserinfo().getUserId());
			} else {
				throw new Exception("Sysuserinfo对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	@Override
	public UserResponseBean deleteLinkUserRole(UserRequestBean userRequestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		TSysLinkUserRoleExample example = new TSysLinkUserRoleExample();
		try {
			if (userRequestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (userRequestBean.getSysLinkUserRoleExample() != null) {
				example = userRequestBean.getSysLinkUserRoleExample();
			}
			if (userRequestBean.getSysLinkUserRole() != null) {
				tSysLinkUserRoleDAO.deleteByExample(example);
			} else {
				throw new Exception("SysLinkUserRole对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}
	
	@Override
	public UserResponseBean queryIllegalUser(UserRequestBean requestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		List<TSysUserInfo> list = null;
		int totalRecords = 0;// 总记录数
		int totalPages = 0;// 总页数
		int pageRecords = 0;// 每页的记录数
		int currentPage = 0;// 当前页码
		try{
			if (requestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if (requestBean.getPageRecords() != 0) {
				pageRecords = requestBean.getPageRecords();// 如果传进每页记录数则使用
			} else {
				pageRecords = Constants.DEFAULT_PAGERECORDS;// 如果不传,则使用默认最大每页记录数
			}
			if (requestBean.getCurrentPage() != 0) {
				currentPage = requestBean.getCurrentPage();
			}
		
			list = tSysUserInfoDAO.selectIllegalUser(requestBean.getDateTime(),  (currentPage - 1) * pageRecords, pageRecords);
			totalRecords = tSysUserInfoDAO.selectIllegalUserCount(requestBean.getDateTime());
			totalPages = UtilTools.getTotalPages(pageRecords, totalRecords);// 总页数
		
			responseBean.setCurrentPage(currentPage);
			responseBean.setTotalPages(totalPages);
			responseBean.setTotalRecords(totalRecords);
			responseBean.setResultList(list);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	/**
	 * @see app.cn.qtt.bm.service.IUserService#passwordOverTime()
	 * @return
	 * @author Gabriel
	 * @createtime 2013-8-24 下午9:33:43
	 */
	@Override
	public UserResponseBean passwordOverTime() {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			int returnValue = tSysUserInfoDAO.passwordOverTime();
			responseBean.setDbReturnValue(returnValue);
			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}
		
		return responseBean;
	}
	
	@Override
	public UserResponseBean queryIllegalUser(String dayTime) {
		UserResponseBean responseBean = new UserResponseBean();
		List<TSysUserInfo> list = null;
		try{
			if (dayTime == null) {
				throw new Exception("查询日期为空");
			}
		
			list = tSysUserInfoDAO.selectIllegalUser(dayTime, 0, 0);

			responseBean.setResultList(list);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}

		return responseBean;
	}

	/**
	 * @see app.cn.qtt.bm.service.IUserService#queryUserPhoneNumberByUserCode(app.cn.qtt.bm.service.pojo.UserRequestBean)
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-9-25 上午11:17:35
	 */
	@Override
	public UserResponseBean queryUserPhoneNumberByUserCode(UserRequestBean requestBean) {
		UserResponseBean responseBean = new UserResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("userRequestBean为空");
			}
			if(StringUtils.isBlank(requestBean.getUserCode())){
				throw new Exception("userCode不能为空");
			}
			TSysUserInfoExample example = new TSysUserInfoExample();
			example.createCriteria().andUserCodeEqualTo(requestBean.getUserCode());
			
			List<TSysUserInfo> resultList = tSysUserInfoDAO.selectByExample(example);
			
			if(CollectionUtils.isEmpty(resultList)){
				throw new Exception("未查询到该userCode对应的手机号码");
			}
			
			responseBean.setUserPhoneNumber(resultList.get(0).getUserPhoneNumber());
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
		}
		
		return responseBean;
	}
	
}
