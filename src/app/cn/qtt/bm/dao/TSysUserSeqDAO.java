package app.cn.qtt.bm.dao;

import app.cn.qtt.bm.model.TSysUserSeq;
import app.cn.qtt.bm.model.TSysUserSeqExample;
import java.util.List;

public interface TSysUserSeqDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    void insert(TSysUserSeq record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int updateByPrimaryKey(TSysUserSeq record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int updateByPrimaryKeySelective(TSysUserSeq record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    List selectByExample(TSysUserSeqExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    TSysUserSeq selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int deleteByExample(TSysUserSeqExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int countByExample(TSysUserSeqExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int updateByExampleSelective(TSysUserSeq record, TSysUserSeqExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_sys_user_seq
     *
     * @abatorgenerated Mon Jun 17 18:06:10 CST 2013
     */
    int updateByExample(TSysUserSeq record, TSysUserSeqExample example);
    
    /**
     * 方法名称:      insertSeqByType  
     * 方法描述:      
     * @param record
     * @return        
     * @Author:      linch
     * @Create Date: 2013-4-8 下午10:13:18
     */ 
      
     Integer insertSeqByType(TSysUserSeq record);
}