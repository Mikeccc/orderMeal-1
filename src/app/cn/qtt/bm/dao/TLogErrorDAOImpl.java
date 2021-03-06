package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TLogError;
import app.cn.qtt.bm.model.TLogErrorExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TLogErrorDAOImpl extends SqlMapClientDaoSupport implements TLogErrorDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TLogErrorDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void insert(TLogError record) {
        getSqlMapClientTemplate().insert("t_log_error.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List selectByExample(TLogErrorExample example) {
        List list = getSqlMapClientTemplate().queryForList("t_log_error.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByExample(TLogErrorExample example) {
        int rows = getSqlMapClientTemplate().delete("t_log_error.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int countByExample(TLogErrorExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("t_log_error.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExampleSelective(TLogError record, TLogErrorExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_log_error.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExample(TLogError record, TLogErrorExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_log_error.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_log_error
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private static class UpdateByExampleParms extends TLogErrorExample {
        private Object record;

        public UpdateByExampleParms(Object record, TLogErrorExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}