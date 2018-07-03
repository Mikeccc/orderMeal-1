package app.cn.qtt.bm.dao;

import java.sql.SQLException;
import java.util.List;

import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;

public interface TShopDailyInfoDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TShopDailyInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKey(TShopDailyInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TShopDailyInfo record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TShopDailyInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TShopDailyInfo selectByPrimaryKey(Integer shopDailyId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TShopDailyInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(Integer shopDailyId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TShopDailyInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TShopDailyInfo record, TShopDailyInfoExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TShopDailyInfo record, TShopDailyInfoExample example);

	/**
	 * @param shopDailyInfoList
	 * @author Gabriel
	 * @date 2013-6-28
	 */
	void batchInsert(List<TShopDailyInfo> shopDailyInfoList) throws SQLException;
}