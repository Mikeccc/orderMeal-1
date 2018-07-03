package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.model.TSysFileExample;
import java.util.List;

public interface TSysFileDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int insert(TSysFile record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int updateByPrimaryKey(TSysFile record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int updateByPrimaryKeySelective(TSysFile record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	List selectByExample(TSysFileExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	TSysFile selectByPrimaryKey(Integer fileId);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int deleteByExample(TSysFileExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int deleteByPrimaryKey(Integer fileId);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int countByExample(TSysFileExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int updateByExampleSelective(TSysFile record, TSysFileExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_file
	 * @abatorgenerated  Wed Jun 26 10:57:59 CST 2013
	 */
	int updateByExample(TSysFile record, TSysFileExample example);
}