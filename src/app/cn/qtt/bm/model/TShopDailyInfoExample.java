package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TShopDailyInfoExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopDailyInfoExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected TShopDailyInfoExample(TShopDailyInfoExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_shop_daily_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andShopDailyIdIsNull() {
            addCriterion("shop_daily_id is null");
            return this;
        }

        public Criteria andShopDailyIdIsNotNull() {
            addCriterion("shop_daily_id is not null");
            return this;
        }

        public Criteria andShopDailyIdEqualTo(Integer value) {
            addCriterion("shop_daily_id =", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdNotEqualTo(Integer value) {
            addCriterion("shop_daily_id <>", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdGreaterThan(Integer value) {
            addCriterion("shop_daily_id >", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_daily_id >=", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdLessThan(Integer value) {
            addCriterion("shop_daily_id <", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_daily_id <=", value, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdIn(List values) {
            addCriterion("shop_daily_id in", values, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdNotIn(List values) {
            addCriterion("shop_daily_id not in", values, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_daily_id between", value1, value2, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_daily_id not between", value1, value2, "shopDailyId");
            return this;
        }

        public Criteria andShopDailyCodeIsNull() {
            addCriterion("shop_daily_code is null");
            return this;
        }

        public Criteria andShopDailyCodeIsNotNull() {
            addCriterion("shop_daily_code is not null");
            return this;
        }

        public Criteria andShopDailyCodeEqualTo(String value) {
            addCriterion("shop_daily_code =", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeNotEqualTo(String value) {
            addCriterion("shop_daily_code <>", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeGreaterThan(String value) {
            addCriterion("shop_daily_code >", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_daily_code >=", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeLessThan(String value) {
            addCriterion("shop_daily_code <", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_daily_code <=", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeLike(String value) {
            addCriterion("shop_daily_code like", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeNotLike(String value) {
            addCriterion("shop_daily_code not like", value, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeIn(List values) {
            addCriterion("shop_daily_code in", values, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeNotIn(List values) {
            addCriterion("shop_daily_code not in", values, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeBetween(String value1, String value2) {
            addCriterion("shop_daily_code between", value1, value2, "shopDailyCode");
            return this;
        }

        public Criteria andShopDailyCodeNotBetween(String value1, String value2) {
            addCriterion("shop_daily_code not between", value1, value2, "shopDailyCode");
            return this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return this;
        }

        public Criteria andShopIdIn(List values) {
            addCriterion("shop_id in", values, "shopId");
            return this;
        }

        public Criteria andShopIdNotIn(List values) {
            addCriterion("shop_id not in", values, "shopId");
            return this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return this;
        }

        public Criteria andShopSendContentIsNull() {
            addCriterion("shop_send_content is null");
            return this;
        }

        public Criteria andShopSendContentIsNotNull() {
            addCriterion("shop_send_content is not null");
            return this;
        }

        public Criteria andShopSendContentEqualTo(String value) {
            addCriterion("shop_send_content =", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentNotEqualTo(String value) {
            addCriterion("shop_send_content <>", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentGreaterThan(String value) {
            addCriterion("shop_send_content >", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentGreaterThanOrEqualTo(String value) {
            addCriterion("shop_send_content >=", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentLessThan(String value) {
            addCriterion("shop_send_content <", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentLessThanOrEqualTo(String value) {
            addCriterion("shop_send_content <=", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentLike(String value) {
            addCriterion("shop_send_content like", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentNotLike(String value) {
            addCriterion("shop_send_content not like", value, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentIn(List values) {
            addCriterion("shop_send_content in", values, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentNotIn(List values) {
            addCriterion("shop_send_content not in", values, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentBetween(String value1, String value2) {
            addCriterion("shop_send_content between", value1, value2, "shopSendContent");
            return this;
        }

        public Criteria andShopSendContentNotBetween(String value1, String value2) {
            addCriterion("shop_send_content not between", value1, value2, "shopSendContent");
            return this;
        }

        public Criteria andShopDailyEmailStatusIsNull() {
            addCriterion("shop_daily_email_status is null");
            return this;
        }

        public Criteria andShopDailyEmailStatusIsNotNull() {
            addCriterion("shop_daily_email_status is not null");
            return this;
        }

        public Criteria andShopDailyEmailStatusEqualTo(String value) {
            addCriterion("shop_daily_email_status =", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusNotEqualTo(String value) {
            addCriterion("shop_daily_email_status <>", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusGreaterThan(String value) {
            addCriterion("shop_daily_email_status >", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusGreaterThanOrEqualTo(String value) {
            addCriterion("shop_daily_email_status >=", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusLessThan(String value) {
            addCriterion("shop_daily_email_status <", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusLessThanOrEqualTo(String value) {
            addCriterion("shop_daily_email_status <=", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusLike(String value) {
            addCriterion("shop_daily_email_status like", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusNotLike(String value) {
            addCriterion("shop_daily_email_status not like", value, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusIn(List values) {
            addCriterion("shop_daily_email_status in", values, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusNotIn(List values) {
            addCriterion("shop_daily_email_status not in", values, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusBetween(String value1, String value2) {
            addCriterion("shop_daily_email_status between", value1, value2, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailyEmailStatusNotBetween(String value1, String value2) {
            addCriterion("shop_daily_email_status not between", value1, value2, "shopDailyEmailStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusIsNull() {
            addCriterion("shop_daily_sms_status is null");
            return this;
        }

        public Criteria andShopDailySmsStatusIsNotNull() {
            addCriterion("shop_daily_sms_status is not null");
            return this;
        }

        public Criteria andShopDailySmsStatusEqualTo(String value) {
            addCriterion("shop_daily_sms_status =", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusNotEqualTo(String value) {
            addCriterion("shop_daily_sms_status <>", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusGreaterThan(String value) {
            addCriterion("shop_daily_sms_status >", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("shop_daily_sms_status >=", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusLessThan(String value) {
            addCriterion("shop_daily_sms_status <", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusLessThanOrEqualTo(String value) {
            addCriterion("shop_daily_sms_status <=", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusLike(String value) {
            addCriterion("shop_daily_sms_status like", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusNotLike(String value) {
            addCriterion("shop_daily_sms_status not like", value, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusIn(List values) {
            addCriterion("shop_daily_sms_status in", values, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusNotIn(List values) {
            addCriterion("shop_daily_sms_status not in", values, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusBetween(String value1, String value2) {
            addCriterion("shop_daily_sms_status between", value1, value2, "shopDailySmsStatus");
            return this;
        }

        public Criteria andShopDailySmsStatusNotBetween(String value1, String value2) {
            addCriterion("shop_daily_sms_status not between", value1, value2, "shopDailySmsStatus");
            return this;
        }

        public Criteria andCreateUserCodeIsNull() {
            addCriterion("create_user_code is null");
            return this;
        }

        public Criteria andCreateUserCodeIsNotNull() {
            addCriterion("create_user_code is not null");
            return this;
        }

        public Criteria andCreateUserCodeEqualTo(String value) {
            addCriterion("create_user_code =", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeNotEqualTo(String value) {
            addCriterion("create_user_code <>", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeGreaterThan(String value) {
            addCriterion("create_user_code >", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_code >=", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeLessThan(String value) {
            addCriterion("create_user_code <", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeLessThanOrEqualTo(String value) {
            addCriterion("create_user_code <=", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeLike(String value) {
            addCriterion("create_user_code like", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeNotLike(String value) {
            addCriterion("create_user_code not like", value, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeIn(List values) {
            addCriterion("create_user_code in", values, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeNotIn(List values) {
            addCriterion("create_user_code not in", values, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeBetween(String value1, String value2) {
            addCriterion("create_user_code between", value1, value2, "createUserCode");
            return this;
        }

        public Criteria andCreateUserCodeNotBetween(String value1, String value2) {
            addCriterion("create_user_code not between", value1, value2, "createUserCode");
            return this;
        }

        public Criteria andModifyUserCodeIsNull() {
            addCriterion("modify_user_code is null");
            return this;
        }

        public Criteria andModifyUserCodeIsNotNull() {
            addCriterion("modify_user_code is not null");
            return this;
        }

        public Criteria andModifyUserCodeEqualTo(String value) {
            addCriterion("modify_user_code =", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeNotEqualTo(String value) {
            addCriterion("modify_user_code <>", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeGreaterThan(String value) {
            addCriterion("modify_user_code >", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("modify_user_code >=", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeLessThan(String value) {
            addCriterion("modify_user_code <", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeLessThanOrEqualTo(String value) {
            addCriterion("modify_user_code <=", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeLike(String value) {
            addCriterion("modify_user_code like", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeNotLike(String value) {
            addCriterion("modify_user_code not like", value, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeIn(List values) {
            addCriterion("modify_user_code in", values, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeNotIn(List values) {
            addCriterion("modify_user_code not in", values, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeBetween(String value1, String value2) {
            addCriterion("modify_user_code between", value1, value2, "modifyUserCode");
            return this;
        }

        public Criteria andModifyUserCodeNotBetween(String value1, String value2) {
            addCriterion("modify_user_code not between", value1, value2, "modifyUserCode");
            return this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeIn(List values) {
            addCriterion("create_time in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotIn(List values) {
            addCriterion("create_time not in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeIn(List values) {
            addCriterion("modify_time in", values, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeNotIn(List values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return this;
        }
    }
}