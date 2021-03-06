package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TSysCodeTree;
import app.cn.qtt.bm.model.TSysCodeTreeExample;
import app.cn.qtt.bm.model.TSysCodeTreeKey;
import java.util.List;

public interface TSysCodeTreeDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TSysCodeTree record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKey(TSysCodeTree record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TSysCodeTree record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TSysCodeTreeExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TSysCodeTree selectByPrimaryKey(TSysCodeTreeKey key);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TSysCodeTreeExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(TSysCodeTreeKey key);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TSysCodeTreeExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TSysCodeTree record, TSysCodeTreeExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_tree
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TSysCodeTree record, TSysCodeTreeExample example);
}