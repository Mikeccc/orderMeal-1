package app.cn.qtt.bm.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysMenuInfoExample;
import app.cn.qtt.bm.model.TSysRoleInfo;

public class TSysMenuInfoDAOImpl extends SqlMapClientDaoSupport implements TSysMenuInfoDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public TSysMenuInfoDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public void insert(TSysMenuInfo record) {
		getSqlMapClientTemplate().insert(
				"t_sys_menu_info.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int updateByPrimaryKey(TSysMenuInfo record) {
		int rows = getSqlMapClientTemplate().update(
				"t_sys_menu_info.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int updateByPrimaryKeySelective(TSysMenuInfo record) {
		int rows = getSqlMapClientTemplate().update(
				"t_sys_menu_info.abatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public List selectByExample(TSysMenuInfoExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"t_sys_menu_info.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public TSysMenuInfo selectByPrimaryKey(Integer menuId) {
		TSysMenuInfo key = new TSysMenuInfo();
		key.setMenuId(menuId);
		TSysMenuInfo record = (TSysMenuInfo) getSqlMapClientTemplate()
				.queryForObject(
						"t_sys_menu_info.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int deleteByExample(TSysMenuInfoExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"t_sys_menu_info.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int deleteByPrimaryKey(Integer menuId) {
		TSysMenuInfo key = new TSysMenuInfo();
		key.setMenuId(menuId);
		int rows = getSqlMapClientTemplate().delete(
				"t_sys_menu_info.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int countByExample(TSysMenuInfoExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"t_sys_menu_info.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int updateByExampleSelective(TSysMenuInfo record,
			TSysMenuInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"t_sys_menu_info.abatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	public int updateByExample(TSysMenuInfo record, TSysMenuInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"t_sys_menu_info.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_sys_menu_info
	 * @abatorgenerated  Mon Jun 17 17:49:55 CST 2013
	 */
	private static class UpdateByExampleParms extends TSysMenuInfoExample {
		private Object record;

		public UpdateByExampleParms(Object record, TSysMenuInfoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/**
	 * @author JJF
	 * @since 2013-4-2
	 * @title ������ɫ��
	 */
	public List<TSysMenuInfo> selectMenuLeftJoinRoleMenu(
			TSysRoleInfo tSysRoleInfo) {
		return getSqlMapClientTemplate().queryForList("t_sys_menu_info.selectMenuLeftJoinRoleMenu", tSysRoleInfo);
	}
	
	
	
}