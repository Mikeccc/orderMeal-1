package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSysCodeTreeExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public TSysCodeTreeExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected TSysCodeTreeExample(TSysCodeTreeExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_sys_code_tree
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
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

		public Criteria andCodeNameIsNull() {
			addCriterion("code_name is null");
			return this;
		}

		public Criteria andCodeNameIsNotNull() {
			addCriterion("code_name is not null");
			return this;
		}

		public Criteria andCodeNameEqualTo(String value) {
			addCriterion("code_name =", value, "codeName");
			return this;
		}

		public Criteria andCodeNameNotEqualTo(String value) {
			addCriterion("code_name <>", value, "codeName");
			return this;
		}

		public Criteria andCodeNameGreaterThan(String value) {
			addCriterion("code_name >", value, "codeName");
			return this;
		}

		public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
			addCriterion("code_name >=", value, "codeName");
			return this;
		}

		public Criteria andCodeNameLessThan(String value) {
			addCriterion("code_name <", value, "codeName");
			return this;
		}

		public Criteria andCodeNameLessThanOrEqualTo(String value) {
			addCriterion("code_name <=", value, "codeName");
			return this;
		}

		public Criteria andCodeNameLike(String value) {
			addCriterion("code_name like", value, "codeName");
			return this;
		}

		public Criteria andCodeNameNotLike(String value) {
			addCriterion("code_name not like", value, "codeName");
			return this;
		}

		public Criteria andCodeNameIn(List values) {
			addCriterion("code_name in", values, "codeName");
			return this;
		}

		public Criteria andCodeNameNotIn(List values) {
			addCriterion("code_name not in", values, "codeName");
			return this;
		}

		public Criteria andCodeNameBetween(String value1, String value2) {
			addCriterion("code_name between", value1, value2, "codeName");
			return this;
		}

		public Criteria andCodeNameNotBetween(String value1, String value2) {
			addCriterion("code_name not between", value1, value2, "codeName");
			return this;
		}

		public Criteria andCodeTypeIsNull() {
			addCriterion("code_type is null");
			return this;
		}

		public Criteria andCodeTypeIsNotNull() {
			addCriterion("code_type is not null");
			return this;
		}

		public Criteria andCodeTypeEqualTo(String value) {
			addCriterion("code_type =", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeNotEqualTo(String value) {
			addCriterion("code_type <>", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeGreaterThan(String value) {
			addCriterion("code_type >", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
			addCriterion("code_type >=", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeLessThan(String value) {
			addCriterion("code_type <", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeLessThanOrEqualTo(String value) {
			addCriterion("code_type <=", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeLike(String value) {
			addCriterion("code_type like", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeNotLike(String value) {
			addCriterion("code_type not like", value, "codeType");
			return this;
		}

		public Criteria andCodeTypeIn(List values) {
			addCriterion("code_type in", values, "codeType");
			return this;
		}

		public Criteria andCodeTypeNotIn(List values) {
			addCriterion("code_type not in", values, "codeType");
			return this;
		}

		public Criteria andCodeTypeBetween(String value1, String value2) {
			addCriterion("code_type between", value1, value2, "codeType");
			return this;
		}

		public Criteria andCodeTypeNotBetween(String value1, String value2) {
			addCriterion("code_type not between", value1, value2, "codeType");
			return this;
		}

		public Criteria andCodeValueIsNull() {
			addCriterion("code_value is null");
			return this;
		}

		public Criteria andCodeValueIsNotNull() {
			addCriterion("code_value is not null");
			return this;
		}

		public Criteria andCodeValueEqualTo(String value) {
			addCriterion("code_value =", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueNotEqualTo(String value) {
			addCriterion("code_value <>", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueGreaterThan(String value) {
			addCriterion("code_value >", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueGreaterThanOrEqualTo(String value) {
			addCriterion("code_value >=", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueLessThan(String value) {
			addCriterion("code_value <", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueLessThanOrEqualTo(String value) {
			addCriterion("code_value <=", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueLike(String value) {
			addCriterion("code_value like", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueNotLike(String value) {
			addCriterion("code_value not like", value, "codeValue");
			return this;
		}

		public Criteria andCodeValueIn(List values) {
			addCriterion("code_value in", values, "codeValue");
			return this;
		}

		public Criteria andCodeValueNotIn(List values) {
			addCriterion("code_value not in", values, "codeValue");
			return this;
		}

		public Criteria andCodeValueBetween(String value1, String value2) {
			addCriterion("code_value between", value1, value2, "codeValue");
			return this;
		}

		public Criteria andCodeValueNotBetween(String value1, String value2) {
			addCriterion("code_value not between", value1, value2, "codeValue");
			return this;
		}

		public Criteria andCodeStatusIsNull() {
			addCriterion("code_status is null");
			return this;
		}

		public Criteria andCodeStatusIsNotNull() {
			addCriterion("code_status is not null");
			return this;
		}

		public Criteria andCodeStatusEqualTo(Integer value) {
			addCriterion("code_status =", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusNotEqualTo(Integer value) {
			addCriterion("code_status <>", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusGreaterThan(Integer value) {
			addCriterion("code_status >", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("code_status >=", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusLessThan(Integer value) {
			addCriterion("code_status <", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusLessThanOrEqualTo(Integer value) {
			addCriterion("code_status <=", value, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusIn(List values) {
			addCriterion("code_status in", values, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusNotIn(List values) {
			addCriterion("code_status not in", values, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusBetween(Integer value1, Integer value2) {
			addCriterion("code_status between", value1, value2, "codeStatus");
			return this;
		}

		public Criteria andCodeStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("code_status not between", value1, value2,
					"codeStatus");
			return this;
		}

		public Criteria andCodeShowNameIsNull() {
			addCriterion("code_show_name is null");
			return this;
		}

		public Criteria andCodeShowNameIsNotNull() {
			addCriterion("code_show_name is not null");
			return this;
		}

		public Criteria andCodeShowNameEqualTo(String value) {
			addCriterion("code_show_name =", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameNotEqualTo(String value) {
			addCriterion("code_show_name <>", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameGreaterThan(String value) {
			addCriterion("code_show_name >", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameGreaterThanOrEqualTo(String value) {
			addCriterion("code_show_name >=", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameLessThan(String value) {
			addCriterion("code_show_name <", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameLessThanOrEqualTo(String value) {
			addCriterion("code_show_name <=", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameLike(String value) {
			addCriterion("code_show_name like", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameNotLike(String value) {
			addCriterion("code_show_name not like", value, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameIn(List values) {
			addCriterion("code_show_name in", values, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameNotIn(List values) {
			addCriterion("code_show_name not in", values, "codeShowName");
			return this;
		}

		public Criteria andCodeShowNameBetween(String value1, String value2) {
			addCriterion("code_show_name between", value1, value2,
					"codeShowName");
			return this;
		}

		public Criteria andCodeShowNameNotBetween(String value1, String value2) {
			addCriterion("code_show_name not between", value1, value2,
					"codeShowName");
			return this;
		}

		public Criteria andCodeFilterIsNull() {
			addCriterion("code_filter is null");
			return this;
		}

		public Criteria andCodeFilterIsNotNull() {
			addCriterion("code_filter is not null");
			return this;
		}

		public Criteria andCodeFilterEqualTo(Integer value) {
			addCriterion("code_filter =", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterNotEqualTo(Integer value) {
			addCriterion("code_filter <>", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterGreaterThan(Integer value) {
			addCriterion("code_filter >", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterGreaterThanOrEqualTo(Integer value) {
			addCriterion("code_filter >=", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterLessThan(Integer value) {
			addCriterion("code_filter <", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterLessThanOrEqualTo(Integer value) {
			addCriterion("code_filter <=", value, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterIn(List values) {
			addCriterion("code_filter in", values, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterNotIn(List values) {
			addCriterion("code_filter not in", values, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterBetween(Integer value1, Integer value2) {
			addCriterion("code_filter between", value1, value2, "codeFilter");
			return this;
		}

		public Criteria andCodeFilterNotBetween(Integer value1, Integer value2) {
			addCriterion("code_filter not between", value1, value2,
					"codeFilter");
			return this;
		}

		public Criteria andCodeSortIsNull() {
			addCriterion("code_sort is null");
			return this;
		}

		public Criteria andCodeSortIsNotNull() {
			addCriterion("code_sort is not null");
			return this;
		}

		public Criteria andCodeSortEqualTo(Integer value) {
			addCriterion("code_sort =", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortNotEqualTo(Integer value) {
			addCriterion("code_sort <>", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortGreaterThan(Integer value) {
			addCriterion("code_sort >", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("code_sort >=", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortLessThan(Integer value) {
			addCriterion("code_sort <", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortLessThanOrEqualTo(Integer value) {
			addCriterion("code_sort <=", value, "codeSort");
			return this;
		}

		public Criteria andCodeSortIn(List values) {
			addCriterion("code_sort in", values, "codeSort");
			return this;
		}

		public Criteria andCodeSortNotIn(List values) {
			addCriterion("code_sort not in", values, "codeSort");
			return this;
		}

		public Criteria andCodeSortBetween(Integer value1, Integer value2) {
			addCriterion("code_sort between", value1, value2, "codeSort");
			return this;
		}

		public Criteria andCodeSortNotBetween(Integer value1, Integer value2) {
			addCriterion("code_sort not between", value1, value2, "codeSort");
			return this;
		}

		public Criteria andCodeParentValueIsNull() {
			addCriterion("code_parent_value is null");
			return this;
		}

		public Criteria andCodeParentValueIsNotNull() {
			addCriterion("code_parent_value is not null");
			return this;
		}

		public Criteria andCodeParentValueEqualTo(String value) {
			addCriterion("code_parent_value =", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueNotEqualTo(String value) {
			addCriterion("code_parent_value <>", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueGreaterThan(String value) {
			addCriterion("code_parent_value >", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueGreaterThanOrEqualTo(String value) {
			addCriterion("code_parent_value >=", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueLessThan(String value) {
			addCriterion("code_parent_value <", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueLessThanOrEqualTo(String value) {
			addCriterion("code_parent_value <=", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueLike(String value) {
			addCriterion("code_parent_value like", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueNotLike(String value) {
			addCriterion("code_parent_value not like", value, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueIn(List values) {
			addCriterion("code_parent_value in", values, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueNotIn(List values) {
			addCriterion("code_parent_value not in", values, "codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueBetween(String value1, String value2) {
			addCriterion("code_parent_value between", value1, value2,
					"codeParentValue");
			return this;
		}

		public Criteria andCodeParentValueNotBetween(String value1,
				String value2) {
			addCriterion("code_parent_value not between", value1, value2,
					"codeParentValue");
			return this;
		}

		public Criteria andCodeDescriptIsNull() {
			addCriterion("code_descript is null");
			return this;
		}

		public Criteria andCodeDescriptIsNotNull() {
			addCriterion("code_descript is not null");
			return this;
		}

		public Criteria andCodeDescriptEqualTo(String value) {
			addCriterion("code_descript =", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptNotEqualTo(String value) {
			addCriterion("code_descript <>", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptGreaterThan(String value) {
			addCriterion("code_descript >", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptGreaterThanOrEqualTo(String value) {
			addCriterion("code_descript >=", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptLessThan(String value) {
			addCriterion("code_descript <", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptLessThanOrEqualTo(String value) {
			addCriterion("code_descript <=", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptLike(String value) {
			addCriterion("code_descript like", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptNotLike(String value) {
			addCriterion("code_descript not like", value, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptIn(List values) {
			addCriterion("code_descript in", values, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptNotIn(List values) {
			addCriterion("code_descript not in", values, "codeDescript");
			return this;
		}

		public Criteria andCodeDescriptBetween(String value1, String value2) {
			addCriterion("code_descript between", value1, value2,
					"codeDescript");
			return this;
		}

		public Criteria andCodeDescriptNotBetween(String value1, String value2) {
			addCriterion("code_descript not between", value1, value2,
					"codeDescript");
			return this;
		}
	}
}