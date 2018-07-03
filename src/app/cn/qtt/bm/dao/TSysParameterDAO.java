package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.model.TSysParameterExample;
import java.util.List;

public interface TSysParameterDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TSysParameter record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeyWithoutBLOBs(TSysParameter record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeyWithBLOBs(TSysParameter record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByPrimaryKeySelective(TSysParameter record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExampleWithoutBLOBs(TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExampleWithBLOBs(TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    TSysParameter selectByPrimaryKey(Integer paramId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByPrimaryKey(Integer paramId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TSysParameter record, TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleWithBLOBs(TSysParameter record, TSysParameterExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_parameter
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleWithoutBLOBs(TSysParameter record, TSysParameterExample example);
}