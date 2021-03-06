package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TSysCodeMetadata;
import app.cn.qtt.bm.model.TSysCodeMetadataExample;
import java.util.List;

public interface TSysCodeMetadataDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    void insert(TSysCodeMetadata record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    List selectByExample(TSysCodeMetadataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int deleteByExample(TSysCodeMetadataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int countByExample(TSysCodeMetadataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExampleSelective(TSysCodeMetadata record, TSysCodeMetadataExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_code_metadata
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    int updateByExample(TSysCodeMetadata record, TSysCodeMetadataExample example);
}