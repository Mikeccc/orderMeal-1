package app.cn.qtt.bm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSysParameterExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public TSysParameterExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected TSysParameterExample(TSysParameterExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
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
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table t_sys_parameter
	 * @abatorgenerated  Sat Jun 08 15:02:10 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table t_sys_parameter
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

		public Criteria andParamIdIsNull() {
			addCriterion("param_id is null");
			return this;
		}

		public Criteria andParamIdIsNotNull() {
			addCriterion("param_id is not null");
			return this;
		}

		public Criteria andParamIdEqualTo(Integer value) {
			addCriterion("param_id =", value, "paramId");
			return this;
		}

		public Criteria andParamIdNotEqualTo(Integer value) {
			addCriterion("param_id <>", value, "paramId");
			return this;
		}

		public Criteria andParamIdGreaterThan(Integer value) {
			addCriterion("param_id >", value, "paramId");
			return this;
		}

		public Criteria andParamIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("param_id >=", value, "paramId");
			return this;
		}

		public Criteria andParamIdLessThan(Integer value) {
			addCriterion("param_id <", value, "paramId");
			return this;
		}

		public Criteria andParamIdLessThanOrEqualTo(Integer value) {
			addCriterion("param_id <=", value, "paramId");
			return this;
		}

		public Criteria andParamIdIn(List values) {
			addCriterion("param_id in", values, "paramId");
			return this;
		}

		public Criteria andParamIdNotIn(List values) {
			addCriterion("param_id not in", values, "paramId");
			return this;
		}

		public Criteria andParamIdBetween(Integer value1, Integer value2) {
			addCriterion("param_id between", value1, value2, "paramId");
			return this;
		}

		public Criteria andParamIdNotBetween(Integer value1, Integer value2) {
			addCriterion("param_id not between", value1, value2, "paramId");
			return this;
		}

		public Criteria andParamCodeIsNull() {
			addCriterion("param_code is null");
			return this;
		}

		public Criteria andParamCodeIsNotNull() {
			addCriterion("param_code is not null");
			return this;
		}

		public Criteria andParamCodeEqualTo(String value) {
			addCriterion("param_code =", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeNotEqualTo(String value) {
			addCriterion("param_code <>", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeGreaterThan(String value) {
			addCriterion("param_code >", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeGreaterThanOrEqualTo(String value) {
			addCriterion("param_code >=", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeLessThan(String value) {
			addCriterion("param_code <", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeLessThanOrEqualTo(String value) {
			addCriterion("param_code <=", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeLike(String value) {
			addCriterion("param_code like", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeNotLike(String value) {
			addCriterion("param_code not like", value, "paramCode");
			return this;
		}

		public Criteria andParamCodeIn(List values) {
			addCriterion("param_code in", values, "paramCode");
			return this;
		}

		public Criteria andParamCodeNotIn(List values) {
			addCriterion("param_code not in", values, "paramCode");
			return this;
		}

		public Criteria andParamCodeBetween(String value1, String value2) {
			addCriterion("param_code between", value1, value2, "paramCode");
			return this;
		}

		public Criteria andParamCodeNotBetween(String value1, String value2) {
			addCriterion("param_code not between", value1, value2, "paramCode");
			return this;
		}

		public Criteria andParamDescIsNull() {
			addCriterion("param_desc is null");
			return this;
		}

		public Criteria andParamDescIsNotNull() {
			addCriterion("param_desc is not null");
			return this;
		}

		public Criteria andParamDescEqualTo(String value) {
			addCriterion("param_desc =", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescNotEqualTo(String value) {
			addCriterion("param_desc <>", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescGreaterThan(String value) {
			addCriterion("param_desc >", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescGreaterThanOrEqualTo(String value) {
			addCriterion("param_desc >=", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescLessThan(String value) {
			addCriterion("param_desc <", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescLessThanOrEqualTo(String value) {
			addCriterion("param_desc <=", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescLike(String value) {
			addCriterion("param_desc like", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescNotLike(String value) {
			addCriterion("param_desc not like", value, "paramDesc");
			return this;
		}

		public Criteria andParamDescIn(List values) {
			addCriterion("param_desc in", values, "paramDesc");
			return this;
		}

		public Criteria andParamDescNotIn(List values) {
			addCriterion("param_desc not in", values, "paramDesc");
			return this;
		}

		public Criteria andParamDescBetween(String value1, String value2) {
			addCriterion("param_desc between", value1, value2, "paramDesc");
			return this;
		}

		public Criteria andParamDescNotBetween(String value1, String value2) {
			addCriterion("param_desc not between", value1, value2, "paramDesc");
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
	}
}