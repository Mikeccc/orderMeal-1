package app.cn.qtt.bm.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.model.TSendEmailBufferExample;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TSendEmailBufferDAOImpl extends SqlMapClientDaoSupport implements TSendEmailBufferDAO {

    /**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public TSendEmailBufferDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public void insert(TSendEmailBuffer record) {
		getSqlMapClientTemplate().insert(
				"t_send_email_buffer.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int updateByPrimaryKey(TSendEmailBuffer record) {
		int rows = getSqlMapClientTemplate().update(
				"t_send_email_buffer.abatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int updateByPrimaryKeySelective(TSendEmailBuffer record) {
		int rows = getSqlMapClientTemplate()
				.update("t_send_email_buffer.abatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public List selectByExample(TSendEmailBufferExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"t_send_email_buffer.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public TSendEmailBuffer selectByPrimaryKey(Integer bufferEmailId) {
		TSendEmailBuffer key = new TSendEmailBuffer();
		key.setBufferEmailId(bufferEmailId);
		TSendEmailBuffer record = (TSendEmailBuffer) getSqlMapClientTemplate()
				.queryForObject(
						"t_send_email_buffer.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int deleteByExample(TSendEmailBufferExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"t_send_email_buffer.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int deleteByPrimaryKey(Integer bufferEmailId) {
		TSendEmailBuffer key = new TSendEmailBuffer();
		key.setBufferEmailId(bufferEmailId);
		int rows = getSqlMapClientTemplate().delete(
				"t_send_email_buffer.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int countByExample(TSendEmailBufferExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"t_send_email_buffer.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int updateByExampleSelective(TSendEmailBuffer record,
			TSendEmailBufferExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"t_send_email_buffer.abatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	public int updateByExample(TSendEmailBuffer record,
			TSendEmailBufferExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"t_send_email_buffer.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_send_email_buffer
	 * @abatorgenerated  Fri Jun 21 11:12:52 CST 2013
	 */
	private static class UpdateByExampleParms extends TSendEmailBufferExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				TSendEmailBufferExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public void batchInsert(List<TSendEmailBuffer> sendEmailBufferList) throws SQLException {
		SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		sqlMapClient.startBatch();
		for(TSendEmailBuffer sendEmailBuffer:sendEmailBufferList){
			getSqlMapClientTemplate().insert("t_send_email_buffer.abatorgenerated_insert", sendEmailBuffer);
		}
		sqlMapClient.executeBatch();
	}
}