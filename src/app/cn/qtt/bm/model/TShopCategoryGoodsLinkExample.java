package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TShopCategoryGoodsLinkExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public TShopCategoryGoodsLinkExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	protected TShopCategoryGoodsLinkExample(
			TShopCategoryGoodsLinkExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_shop_category_goods_link
	 * @abatorgenerated  Thu Jun 13 15:31:35 CST 2013
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

		public Criteria andCategoryGoodsIdIsNull() {
			addCriterion("category_goods_id is null");
			return this;
		}

		public Criteria andCategoryGoodsIdIsNotNull() {
			addCriterion("category_goods_id is not null");
			return this;
		}

		public Criteria andCategoryGoodsIdEqualTo(Integer value) {
			addCriterion("category_goods_id =", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdNotEqualTo(Integer value) {
			addCriterion("category_goods_id <>", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdGreaterThan(Integer value) {
			addCriterion("category_goods_id >", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("category_goods_id >=", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdLessThan(Integer value) {
			addCriterion("category_goods_id <", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdLessThanOrEqualTo(Integer value) {
			addCriterion("category_goods_id <=", value, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdIn(List values) {
			addCriterion("category_goods_id in", values, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdNotIn(List values) {
			addCriterion("category_goods_id not in", values, "categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdBetween(Integer value1, Integer value2) {
			addCriterion("category_goods_id between", value1, value2,
					"categoryGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsIdNotBetween(Integer value1,
				Integer value2) {
			addCriterion("category_goods_id not between", value1, value2,
					"categoryGoodsId");
			return this;
		}

		public Criteria andCategoryIdIsNull() {
			addCriterion("category_id is null");
			return this;
		}

		public Criteria andCategoryIdIsNotNull() {
			addCriterion("category_id is not null");
			return this;
		}

		public Criteria andCategoryIdEqualTo(Integer value) {
			addCriterion("category_id =", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdNotEqualTo(Integer value) {
			addCriterion("category_id <>", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdGreaterThan(Integer value) {
			addCriterion("category_id >", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("category_id >=", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdLessThan(Integer value) {
			addCriterion("category_id <", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
			addCriterion("category_id <=", value, "categoryId");
			return this;
		}

		public Criteria andCategoryIdIn(List values) {
			addCriterion("category_id in", values, "categoryId");
			return this;
		}

		public Criteria andCategoryIdNotIn(List values) {
			addCriterion("category_id not in", values, "categoryId");
			return this;
		}

		public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
			addCriterion("category_id between", value1, value2, "categoryId");
			return this;
		}

		public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
			addCriterion("category_id not between", value1, value2,
					"categoryId");
			return this;
		}

		public Criteria andShopGoodsIdIsNull() {
			addCriterion("shop_goods_id is null");
			return this;
		}

		public Criteria andShopGoodsIdIsNotNull() {
			addCriterion("shop_goods_id is not null");
			return this;
		}

		public Criteria andShopGoodsIdEqualTo(Integer value) {
			addCriterion("shop_goods_id =", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdNotEqualTo(Integer value) {
			addCriterion("shop_goods_id <>", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdGreaterThan(Integer value) {
			addCriterion("shop_goods_id >", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("shop_goods_id >=", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdLessThan(Integer value) {
			addCriterion("shop_goods_id <", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdLessThanOrEqualTo(Integer value) {
			addCriterion("shop_goods_id <=", value, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdIn(List values) {
			addCriterion("shop_goods_id in", values, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdNotIn(List values) {
			addCriterion("shop_goods_id not in", values, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdBetween(Integer value1, Integer value2) {
			addCriterion("shop_goods_id between", value1, value2, "shopGoodsId");
			return this;
		}

		public Criteria andShopGoodsIdNotBetween(Integer value1, Integer value2) {
			addCriterion("shop_goods_id not between", value1, value2,
					"shopGoodsId");
			return this;
		}

		public Criteria andCategoryGoodsSortIsNull() {
			addCriterion("category_goods_sort is null");
			return this;
		}

		public Criteria andCategoryGoodsSortIsNotNull() {
			addCriterion("category_goods_sort is not null");
			return this;
		}

		public Criteria andCategoryGoodsSortEqualTo(String value) {
			addCriterion("category_goods_sort =", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortNotEqualTo(String value) {
			addCriterion("category_goods_sort <>", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortGreaterThan(String value) {
			addCriterion("category_goods_sort >", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortGreaterThanOrEqualTo(String value) {
			addCriterion("category_goods_sort >=", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortLessThan(String value) {
			addCriterion("category_goods_sort <", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortLessThanOrEqualTo(String value) {
			addCriterion("category_goods_sort <=", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortLike(String value) {
			addCriterion("category_goods_sort like", value, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortNotLike(String value) {
			addCriterion("category_goods_sort not like", value,
					"categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortIn(List values) {
			addCriterion("category_goods_sort in", values, "categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortNotIn(List values) {
			addCriterion("category_goods_sort not in", values,
					"categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortBetween(String value1, String value2) {
			addCriterion("category_goods_sort between", value1, value2,
					"categoryGoodsSort");
			return this;
		}

		public Criteria andCategoryGoodsSortNotBetween(String value1,
				String value2) {
			addCriterion("category_goods_sort not between", value1, value2,
					"categoryGoodsSort");
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
	}
}