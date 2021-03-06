package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TShopGoodsDealExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TShopGoodsDealExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected TShopGoodsDealExample(TShopGoodsDealExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
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
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_shop_goods_deal
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_shop_goods_deal
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

        public Criteria andShopGoodsDealIdIsNull() {
            addCriterion("shop_goods_deal_id is null");
            return this;
        }

        public Criteria andShopGoodsDealIdIsNotNull() {
            addCriterion("shop_goods_deal_id is not null");
            return this;
        }

        public Criteria andShopGoodsDealIdEqualTo(Integer value) {
            addCriterion("shop_goods_deal_id =", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdNotEqualTo(Integer value) {
            addCriterion("shop_goods_deal_id <>", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdGreaterThan(Integer value) {
            addCriterion("shop_goods_deal_id >", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_goods_deal_id >=", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdLessThan(Integer value) {
            addCriterion("shop_goods_deal_id <", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_goods_deal_id <=", value, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdIn(List values) {
            addCriterion("shop_goods_deal_id in", values, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdNotIn(List values) {
            addCriterion("shop_goods_deal_id not in", values, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_goods_deal_id between", value1, value2, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodsDealIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_goods_deal_id not between", value1, value2, "shopGoodsDealId");
            return this;
        }

        public Criteria andShopGoodIdIsNull() {
            addCriterion("shop_good_id is null");
            return this;
        }

        public Criteria andShopGoodIdIsNotNull() {
            addCriterion("shop_good_id is not null");
            return this;
        }

        public Criteria andShopGoodIdEqualTo(Integer value) {
            addCriterion("shop_good_id =", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdNotEqualTo(Integer value) {
            addCriterion("shop_good_id <>", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdGreaterThan(Integer value) {
            addCriterion("shop_good_id >", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_good_id >=", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdLessThan(Integer value) {
            addCriterion("shop_good_id <", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_good_id <=", value, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdIn(List values) {
            addCriterion("shop_good_id in", values, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdNotIn(List values) {
            addCriterion("shop_good_id not in", values, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_good_id between", value1, value2, "shopGoodId");
            return this;
        }

        public Criteria andShopGoodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_good_id not between", value1, value2, "shopGoodId");
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