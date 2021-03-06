package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TOrderInfoExample {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public TOrderInfoExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected TOrderInfoExample(TOrderInfoExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
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
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_order_info
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table t_order_info
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return this;
        }

        public Criteria andOrderIdIn(List values) {
            addCriterion("order_id in", values, "orderId");
            return this;
        }

        public Criteria andOrderIdNotIn(List values) {
            addCriterion("order_id not in", values, "orderId");
            return this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return this;
        }

        public Criteria andOrderCodeIn(List values) {
            addCriterion("order_code in", values, "orderCode");
            return this;
        }

        public Criteria andOrderCodeNotIn(List values) {
            addCriterion("order_code not in", values, "orderCode");
            return this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return this;
        }

        public Criteria andOrderNameIsNull() {
            addCriterion("order_name is null");
            return this;
        }

        public Criteria andOrderNameIsNotNull() {
            addCriterion("order_name is not null");
            return this;
        }

        public Criteria andOrderNameEqualTo(String value) {
            addCriterion("order_name =", value, "orderName");
            return this;
        }

        public Criteria andOrderNameNotEqualTo(String value) {
            addCriterion("order_name <>", value, "orderName");
            return this;
        }

        public Criteria andOrderNameGreaterThan(String value) {
            addCriterion("order_name >", value, "orderName");
            return this;
        }

        public Criteria andOrderNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_name >=", value, "orderName");
            return this;
        }

        public Criteria andOrderNameLessThan(String value) {
            addCriterion("order_name <", value, "orderName");
            return this;
        }

        public Criteria andOrderNameLessThanOrEqualTo(String value) {
            addCriterion("order_name <=", value, "orderName");
            return this;
        }

        public Criteria andOrderNameLike(String value) {
            addCriterion("order_name like", value, "orderName");
            return this;
        }

        public Criteria andOrderNameNotLike(String value) {
            addCriterion("order_name not like", value, "orderName");
            return this;
        }

        public Criteria andOrderNameIn(List values) {
            addCriterion("order_name in", values, "orderName");
            return this;
        }

        public Criteria andOrderNameNotIn(List values) {
            addCriterion("order_name not in", values, "orderName");
            return this;
        }

        public Criteria andOrderNameBetween(String value1, String value2) {
            addCriterion("order_name between", value1, value2, "orderName");
            return this;
        }

        public Criteria andOrderNameNotBetween(String value1, String value2) {
            addCriterion("order_name not between", value1, value2, "orderName");
            return this;
        }

        public Criteria andOrderDescIsNull() {
            addCriterion("order_desc is null");
            return this;
        }

        public Criteria andOrderDescIsNotNull() {
            addCriterion("order_desc is not null");
            return this;
        }

        public Criteria andOrderDescEqualTo(String value) {
            addCriterion("order_desc =", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescNotEqualTo(String value) {
            addCriterion("order_desc <>", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescGreaterThan(String value) {
            addCriterion("order_desc >", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescGreaterThanOrEqualTo(String value) {
            addCriterion("order_desc >=", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescLessThan(String value) {
            addCriterion("order_desc <", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescLessThanOrEqualTo(String value) {
            addCriterion("order_desc <=", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescLike(String value) {
            addCriterion("order_desc like", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescNotLike(String value) {
            addCriterion("order_desc not like", value, "orderDesc");
            return this;
        }

        public Criteria andOrderDescIn(List values) {
            addCriterion("order_desc in", values, "orderDesc");
            return this;
        }

        public Criteria andOrderDescNotIn(List values) {
            addCriterion("order_desc not in", values, "orderDesc");
            return this;
        }

        public Criteria andOrderDescBetween(String value1, String value2) {
            addCriterion("order_desc between", value1, value2, "orderDesc");
            return this;
        }

        public Criteria andOrderDescNotBetween(String value1, String value2) {
            addCriterion("order_desc not between", value1, value2, "orderDesc");
            return this;
        }

        public Criteria andOrderRunStatusIsNull() {
            addCriterion("order_run_status is null");
            return this;
        }

        public Criteria andOrderRunStatusIsNotNull() {
            addCriterion("order_run_status is not null");
            return this;
        }

        public Criteria andOrderRunStatusEqualTo(String value) {
            addCriterion("order_run_status =", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusNotEqualTo(String value) {
            addCriterion("order_run_status <>", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusGreaterThan(String value) {
            addCriterion("order_run_status >", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusGreaterThanOrEqualTo(String value) {
            addCriterion("order_run_status >=", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusLessThan(String value) {
            addCriterion("order_run_status <", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusLessThanOrEqualTo(String value) {
            addCriterion("order_run_status <=", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusLike(String value) {
            addCriterion("order_run_status like", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusNotLike(String value) {
            addCriterion("order_run_status not like", value, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusIn(List values) {
            addCriterion("order_run_status in", values, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusNotIn(List values) {
            addCriterion("order_run_status not in", values, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusBetween(String value1, String value2) {
            addCriterion("order_run_status between", value1, value2, "orderRunStatus");
            return this;
        }

        public Criteria andOrderRunStatusNotBetween(String value1, String value2) {
            addCriterion("order_run_status not between", value1, value2, "orderRunStatus");
            return this;
        }

        public Criteria andOrderGoodsCountIsNull() {
            addCriterion("order_goods_count is null");
            return this;
        }

        public Criteria andOrderGoodsCountIsNotNull() {
            addCriterion("order_goods_count is not null");
            return this;
        }

        public Criteria andOrderGoodsCountEqualTo(String value) {
            addCriterion("order_goods_count =", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountNotEqualTo(String value) {
            addCriterion("order_goods_count <>", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountGreaterThan(String value) {
            addCriterion("order_goods_count >", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountGreaterThanOrEqualTo(String value) {
            addCriterion("order_goods_count >=", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountLessThan(String value) {
            addCriterion("order_goods_count <", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountLessThanOrEqualTo(String value) {
            addCriterion("order_goods_count <=", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountLike(String value) {
            addCriterion("order_goods_count like", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountNotLike(String value) {
            addCriterion("order_goods_count not like", value, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountIn(List values) {
            addCriterion("order_goods_count in", values, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountNotIn(List values) {
            addCriterion("order_goods_count not in", values, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountBetween(String value1, String value2) {
            addCriterion("order_goods_count between", value1, value2, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderGoodsCountNotBetween(String value1, String value2) {
            addCriterion("order_goods_count not between", value1, value2, "orderGoodsCount");
            return this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("order_price is null");
            return this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("order_price is not null");
            return this;
        }

        public Criteria andOrderPriceEqualTo(String value) {
            addCriterion("order_price =", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceNotEqualTo(String value) {
            addCriterion("order_price <>", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceGreaterThan(String value) {
            addCriterion("order_price >", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(String value) {
            addCriterion("order_price >=", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceLessThan(String value) {
            addCriterion("order_price <", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(String value) {
            addCriterion("order_price <=", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceLike(String value) {
            addCriterion("order_price like", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceNotLike(String value) {
            addCriterion("order_price not like", value, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceIn(List values) {
            addCriterion("order_price in", values, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceNotIn(List values) {
            addCriterion("order_price not in", values, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceBetween(String value1, String value2) {
            addCriterion("order_price between", value1, value2, "orderPrice");
            return this;
        }

        public Criteria andOrderPriceNotBetween(String value1, String value2) {
            addCriterion("order_price not between", value1, value2, "orderPrice");
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