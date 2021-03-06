package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TOrderInfoShopExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public TOrderInfoShopExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	protected TOrderInfoShopExample(TOrderInfoShopExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_order_info_shop
	 * @abatorgenerated  Tue Sep 17 11:21:55 CST 2013
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values,
				String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andOrderShopIdIsNull() {
			addCriterion("order_shop_id is null");
			return this;
		}

		public Criteria andOrderShopIdIsNotNull() {
			addCriterion("order_shop_id is not null");
			return this;
		}

		public Criteria andOrderShopIdEqualTo(Integer value) {
			addCriterion("order_shop_id =", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdNotEqualTo(Integer value) {
			addCriterion("order_shop_id <>", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdGreaterThan(Integer value) {
			addCriterion("order_shop_id >", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_shop_id >=", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdLessThan(Integer value) {
			addCriterion("order_shop_id <", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdLessThanOrEqualTo(Integer value) {
			addCriterion("order_shop_id <=", value, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdIn(List values) {
			addCriterion("order_shop_id in", values, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdNotIn(List values) {
			addCriterion("order_shop_id not in", values, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdBetween(Integer value1, Integer value2) {
			addCriterion("order_shop_id between", value1, value2, "orderShopId");
			return this;
		}

		public Criteria andOrderShopIdNotBetween(Integer value1, Integer value2) {
			addCriterion("order_shop_id not between", value1, value2,
					"orderShopId");
			return this;
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

		public Criteria andOrderShopQrcodeIsNull() {
			addCriterion("order_shop_qrcode is null");
			return this;
		}

		public Criteria andOrderShopQrcodeIsNotNull() {
			addCriterion("order_shop_qrcode is not null");
			return this;
		}

		public Criteria andOrderShopQrcodeEqualTo(String value) {
			addCriterion("order_shop_qrcode =", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeNotEqualTo(String value) {
			addCriterion("order_shop_qrcode <>", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeGreaterThan(String value) {
			addCriterion("order_shop_qrcode >", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeGreaterThanOrEqualTo(String value) {
			addCriterion("order_shop_qrcode >=", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeLessThan(String value) {
			addCriterion("order_shop_qrcode <", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeLessThanOrEqualTo(String value) {
			addCriterion("order_shop_qrcode <=", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeLike(String value) {
			addCriterion("order_shop_qrcode like", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeNotLike(String value) {
			addCriterion("order_shop_qrcode not like", value, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeIn(List values) {
			addCriterion("order_shop_qrcode in", values, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeNotIn(List values) {
			addCriterion("order_shop_qrcode not in", values, "orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeBetween(String value1, String value2) {
			addCriterion("order_shop_qrcode between", value1, value2,
					"orderShopQrcode");
			return this;
		}

		public Criteria andOrderShopQrcodeNotBetween(String value1,
				String value2) {
			addCriterion("order_shop_qrcode not between", value1, value2,
					"orderShopQrcode");
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
			addCriterion("create_user_code between", value1, value2,
					"createUserCode");
			return this;
		}

		public Criteria andCreateUserCodeNotBetween(String value1, String value2) {
			addCriterion("create_user_code not between", value1, value2,
					"createUserCode");
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
			addCriterion("modify_user_code between", value1, value2,
					"modifyUserCode");
			return this;
		}

		public Criteria andModifyUserCodeNotBetween(String value1, String value2) {
			addCriterion("modify_user_code not between", value1, value2,
					"modifyUserCode");
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
			addCriterion("create_time not between", value1, value2,
					"createTime");
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
			addCriterion("modify_time not between", value1, value2,
					"modifyTime");
			return this;
		}

		public Criteria andOrderShopRunStatusIsNull() {
			addCriterion("order_shop_run_status is null");
			return this;
		}

		public Criteria andOrderShopRunStatusIsNotNull() {
			addCriterion("order_shop_run_status is not null");
			return this;
		}

		public Criteria andOrderShopRunStatusEqualTo(String value) {
			addCriterion("order_shop_run_status =", value, "orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusNotEqualTo(String value) {
			addCriterion("order_shop_run_status <>", value,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusGreaterThan(String value) {
			addCriterion("order_shop_run_status >", value, "orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusGreaterThanOrEqualTo(String value) {
			addCriterion("order_shop_run_status >=", value,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusLessThan(String value) {
			addCriterion("order_shop_run_status <", value, "orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusLessThanOrEqualTo(String value) {
			addCriterion("order_shop_run_status <=", value,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusLike(String value) {
			addCriterion("order_shop_run_status like", value,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusNotLike(String value) {
			addCriterion("order_shop_run_status not like", value,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusIn(List values) {
			addCriterion("order_shop_run_status in", values,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusNotIn(List values) {
			addCriterion("order_shop_run_status not in", values,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusBetween(String value1,
				String value2) {
			addCriterion("order_shop_run_status between", value1, value2,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andOrderShopRunStatusNotBetween(String value1,
				String value2) {
			addCriterion("order_shop_run_status not between", value1, value2,
					"orderShopRunStatus");
			return this;
		}

		public Criteria andPriceIsNull() {
			addCriterion("price is null");
			return this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("price is not null");
			return this;
		}

		public Criteria andPriceEqualTo(String value) {
			addCriterion("price =", value, "price");
			return this;
		}

		public Criteria andPriceNotEqualTo(String value) {
			addCriterion("price <>", value, "price");
			return this;
		}

		public Criteria andPriceGreaterThan(String value) {
			addCriterion("price >", value, "price");
			return this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(String value) {
			addCriterion("price >=", value, "price");
			return this;
		}

		public Criteria andPriceLessThan(String value) {
			addCriterion("price <", value, "price");
			return this;
		}

		public Criteria andPriceLessThanOrEqualTo(String value) {
			addCriterion("price <=", value, "price");
			return this;
		}

		public Criteria andPriceLike(String value) {
			addCriterion("price like", value, "price");
			return this;
		}

		public Criteria andPriceNotLike(String value) {
			addCriterion("price not like", value, "price");
			return this;
		}

		public Criteria andPriceIn(List values) {
			addCriterion("price in", values, "price");
			return this;
		}

		public Criteria andPriceNotIn(List values) {
			addCriterion("price not in", values, "price");
			return this;
		}

		public Criteria andPriceBetween(String value1, String value2) {
			addCriterion("price between", value1, value2, "price");
			return this;
		}

		public Criteria andPriceNotBetween(String value1, String value2) {
			addCriterion("price not between", value1, value2, "price");
			return this;
		}

		public Criteria andCaptchasIsNull() {
			addCriterion("captchas is null");
			return this;
		}

		public Criteria andCaptchasIsNotNull() {
			addCriterion("captchas is not null");
			return this;
		}

		public Criteria andCaptchasEqualTo(String value) {
			addCriterion("captchas =", value, "captchas");
			return this;
		}

		public Criteria andCaptchasNotEqualTo(String value) {
			addCriterion("captchas <>", value, "captchas");
			return this;
		}

		public Criteria andCaptchasGreaterThan(String value) {
			addCriterion("captchas >", value, "captchas");
			return this;
		}

		public Criteria andCaptchasGreaterThanOrEqualTo(String value) {
			addCriterion("captchas >=", value, "captchas");
			return this;
		}

		public Criteria andCaptchasLessThan(String value) {
			addCriterion("captchas <", value, "captchas");
			return this;
		}

		public Criteria andCaptchasLessThanOrEqualTo(String value) {
			addCriterion("captchas <=", value, "captchas");
			return this;
		}

		public Criteria andCaptchasLike(String value) {
			addCriterion("captchas like", value, "captchas");
			return this;
		}

		public Criteria andCaptchasNotLike(String value) {
			addCriterion("captchas not like", value, "captchas");
			return this;
		}

		public Criteria andCaptchasIn(List values) {
			addCriterion("captchas in", values, "captchas");
			return this;
		}

		public Criteria andCaptchasNotIn(List values) {
			addCriterion("captchas not in", values, "captchas");
			return this;
		}

		public Criteria andCaptchasBetween(String value1, String value2) {
			addCriterion("captchas between", value1, value2, "captchas");
			return this;
		}

		public Criteria andCaptchasNotBetween(String value1, String value2) {
			addCriterion("captchas not between", value1, value2, "captchas");
			return this;
		}
	}
}