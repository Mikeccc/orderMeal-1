package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TShopInfoDeal;
import app.cn.qtt.bm.model.TShopInfoDealExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TShopInfoDealDAOImpl extends SqlMapClientDaoSupport implements TShopInfoDealDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopInfoDealDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void insert(TShopInfoDeal record) {
        getSqlMapClientTemplate().insert("t_shop_info_deal.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKey(TShopInfoDeal record) {
        int rows = getSqlMapClientTemplate().update("t_shop_info_deal.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByPrimaryKeySelective(TShopInfoDeal record) {
        int rows = getSqlMapClientTemplate().update("t_shop_info_deal.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List selectByExample(TShopInfoDealExample example) {
        List list = getSqlMapClientTemplate().queryForList("t_shop_info_deal.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopInfoDeal selectByPrimaryKey(Integer shopDealId) {
        TShopInfoDeal key = new TShopInfoDeal();
        key.setShopDealId(shopDealId);
        TShopInfoDeal record = (TShopInfoDeal) getSqlMapClientTemplate().queryForObject("t_shop_info_deal.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByExample(TShopInfoDealExample example) {
        int rows = getSqlMapClientTemplate().delete("t_shop_info_deal.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int deleteByPrimaryKey(Integer shopDealId) {
        TShopInfoDeal key = new TShopInfoDeal();
        key.setShopDealId(shopDealId);
        int rows = getSqlMapClientTemplate().delete("t_shop_info_deal.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int countByExample(TShopInfoDealExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("t_shop_info_deal.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExampleSelective(TShopInfoDeal record, TShopInfoDealExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_info_deal.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public int updateByExample(TShopInfoDeal record, TShopInfoDealExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("t_shop_info_deal.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_shop_info_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private static class UpdateByExampleParms extends TShopInfoDealExample {
        private Object record;

        public UpdateByExampleParms(Object record, TShopInfoDealExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}