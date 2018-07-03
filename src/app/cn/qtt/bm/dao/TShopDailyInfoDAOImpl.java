package app.cn.qtt.bm.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TShopDailyInfoDAOImpl extends SqlMapClientDaoSupport implements TShopDailyInfoDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopDailyInfoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void insert(TShopDailyInfo record) {
        getSqlMapClientTemplate().insert("t_shop_daily_info.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKey(TShopDailyInfo record) {
        int rows = getSqlMapClientTemplate().update("t_shop_daily_info.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKeySelective(TShopDailyInfo record) {
        int rows = getSqlMapClientTemplate().update("t_shop_daily_info.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List selectByExample(TShopDailyInfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("t_shop_daily_info.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopDailyInfo selectByPrimaryKey(Integer shopDailyId) {
        TShopDailyInfo key = new TShopDailyInfo();
        key.setShopDailyId(shopDailyId);
        TShopDailyInfo record = (TShopDailyInfo) getSqlMapClientTemplate().queryForObject("t_shop_daily_info.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByExample(TShopDailyInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("t_shop_daily_info.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByPrimaryKey(Integer shopDailyId) {
        TShopDailyInfo key = new TShopDailyInfo();
        key.setShopDailyId(shopDailyId);
        int rows = getSqlMapClientTemplate().delete("t_shop_daily_info.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int countByExample(TShopDailyInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("t_shop_daily_info.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExampleSelective(TShopDailyInfo record, TShopDailyInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_daily_info.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExample(TShopDailyInfo record, TShopDailyInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_daily_info.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private static class UpdateByExampleParms extends TShopDailyInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, TShopDailyInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public void batchInsert(List<TShopDailyInfo> shopDailyInfoList) throws SQLException {
		SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		sqlMapClient.startBatch();
		for(TShopDailyInfo shopDailyInfo:shopDailyInfoList){
			getSqlMapClientTemplate().insert("t_shop_daily_info.abatorgenerated_insert", shopDailyInfo);
		}
		sqlMapClient.executeBatch();
	}
}