package app.cn.qtt.bm.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TShopInfoExample;

public class TShopInfoDAOImpl extends SqlMapClientDaoSupport implements TShopInfoDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopInfoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void insert(TShopInfo record) {
        getSqlMapClientTemplate().insert("t_shop_info.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKey(TShopInfo record) {
        int rows = getSqlMapClientTemplate().update("t_shop_info.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKeySelective(TShopInfo record) {
        int rows = getSqlMapClientTemplate().update("t_shop_info.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List selectByExample(TShopInfoExample example) {
        List list = getSqlMapClientTemplate().queryForList("t_shop_info.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopInfo selectByPrimaryKey(Integer shopId) {
        TShopInfo key = new TShopInfo();
        key.setShopId(shopId);
        TShopInfo record = (TShopInfo) getSqlMapClientTemplate().queryForObject("t_shop_info.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByExample(TShopInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("t_shop_info.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByPrimaryKey(Integer shopId) {
        TShopInfo key = new TShopInfo();
        key.setShopId(shopId);
        int rows = getSqlMapClientTemplate().delete("t_shop_info.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int countByExample(TShopInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("t_shop_info.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExampleSelective(TShopInfo record, TShopInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_info.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExample(TShopInfo record, TShopInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_info.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_shop_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private static class UpdateByExampleParms extends TShopInfoExample {
        private Object record;
        private int currentPage;
        private int pageRecords;

        public UpdateByExampleParms(Object record, TShopInfoExample example) {
            super(example);
            this.record = record;
        }
        
        public UpdateByExampleParms(TShopInfoExample example,
                int currentPage,
                int pageRecords) {
			super(example);
			this.currentPage = currentPage;
			this.pageRecords = pageRecords;
		}

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<TShopInfo> selectPagesByExample(int currentPage,
			int pageRecords, TShopInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(example,pageRecords,currentPage );
		
		return getSqlMapClientTemplate().queryForList(
				"t_shop_info.selectPagesByExample", parms);
	}
	
}