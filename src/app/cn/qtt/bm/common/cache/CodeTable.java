package app.cn.qtt.bm.common.cache;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import app.cn.qtt.bm.common.utils.DateUtil;


import java.util.Map;
import java.util.Iterator;
import java.util.Vector;

/**
 * <p>
 * Title: 全天通彩漫系统_缓存工具类
 * </p>
 * 
 * <p>
 * Description: 全天通彩漫系统2012年改造
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * <p>
 * Company: 北京全天通（www.qtt.cn）
 * </p>
 * 
 * @author zhengyi
 * @version 1.0
 */
public class CodeTable extends ArrayList {
	private Log log = LogFactory.getLog(CodeTable.class);

	private Vector colNames = new Vector(0);

	private Vector colTypes = new Vector(0);

	public CodeTable() {
		super();
	}

	public CodeTable(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			ResultSetMetaData rsmtd = rs.getMetaData();
			int colCount = rsmtd.getColumnCount();
			colNames = new Vector(colCount);
			colTypes = new Vector(colCount);
			for (int ii = 0; ii < colCount; ii++) {
				String name = rsmtd.getColumnName(ii + 1).toUpperCase();
				colNames.add(name);
				colTypes.add(new Integer(rsmtd.getColumnType(ii + 1)));
			}

			while (rs.next()) {
				Vector row = new Vector();
				for (int ii = 0; ii < colCount; ii++) {
					Object value = null;
					value = rs.getObject(ii + 1);
					row.add(value);
				}
				this.add(row);
			}
		} catch (SQLException ex) {
			log.error("", ex);
		} finally {
		}
	}

	public CodeTable(List codeList, String colName, String colType) {
		if (codeList == null) {
			return;
		}
		colNames = new Vector(1);
		colNames.add(colName);
		colTypes = new Vector(1);
		colTypes.add(colType);
		this.addAll(codeList);
	}
	

	public CodeTable(List codeList, String[] colName) {
		if (codeList == null) {
			return;
		}
		colNames = new Vector(colName.length);
		for (String str : colName) {
			colNames.add(str);
		}
		this.addAll(codeList);
	}

	public CodeTable(List codeList) {
		this.addAll(codeList);
	}

	public String[] getColNames() {
		String[] colNameArr = new String[colNames.size()];
		for (int ii = 0; ii < colNames.size(); ii++)
			colNameArr[ii] = (String) colNames.elementAt(ii);

		return colNameArr;
	}

	public void setColNames(String[] colNameArr) {
		if (colNameArr == null)
			return;
		if (colNames == null)
			this.colNames = new Vector(colNameArr.length);
		for (int ii = 0; ii < colNameArr.length; ii++)
			this.colNames.add(colNameArr[ii]);
	}

	public void setColNames(Vector rowHead) {
		this.colNames = rowHead;
	}

	public int[] getColTypes() {
		int[] colTypesArr = new int[colTypes.size()];
		for (int ii = 0; ii < colTypes.size(); ii++)
			colTypesArr[ii] = ((Integer) colTypes.elementAt(ii)).intValue();

		return colTypesArr;
	}

	public void setColTypes(int[] colTypeArr) {
		if (colTypeArr == null)
			return;
		if (colTypes == null)
			this.colTypes = new Vector(colTypeArr.length);
		for (int ii = 0; ii < colTypeArr.length; ii++)
			this.colNames.add(new Integer(colTypeArr[ii]));
	}

	public void setColTypes(Vector colTypes) {
		this.colTypes = colTypes;
	}

	public int getColIndex(String colName) {
		if (colNames == null || colNames.isEmpty())
			return -1;
		return colNames.indexOf(colName);
	}

	public int getColType(String colName) {
		if (colTypes == null || colTypes.isEmpty())
			return -1;
		int idx = getColIndex(colName);
		return ((Integer) colTypes.get(idx)).intValue();
	}

	/**
	 * ȡ�ô��������
	 * 
	 * @return int
	 */
	public int getCols() {
		return this.colNames.size();
	}

	public Object getObject(int row, String colName) {
		if (this == null || this.size() < 1) {
			return null;
		}
		if (row < 0) {
			return null;
		}
		int col = this.getColIndex(colName);
		return getObject(row, col);
	}

	/**
	 * ����кź��к�ȡ��������ֵ
	 * 
	 * @param row
	 *            int �к�
	 * @param col
	 *            int �к�
	 * @return Object
	 */
	public Object getObject(int row, int col) {
		if (col < 0) {
			return null;
		}
		if (col > colNames.size() - 1)
			col = (colNames.size() - 1);
		return ((Vector) get(row)).get(col);
	}

	public String getString(int row, String colName) {
		Object obj = getObject(row, colName);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	public String getString(int row, int col) {
		Object obj = getObject(row, col);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	public int getInt(int row, String colName) {
		Object obj = getObject(row, colName);
		int iV = 0;
		if (obj != null) {
			if (obj instanceof Integer) {
				iV = ((Integer) obj).intValue();
			} else if (obj instanceof Float) {
				iV = ((Float) obj).intValue();
			} else if (obj instanceof Number) {
				iV = ((Number) obj).intValue();
			}
		}
		return iV;
	}

	public int getInt(int row, int col) {
		Object obj = getObject(row, col);
		int iV = 0;
		if (obj != null) {
			if (obj instanceof Integer) {
				iV = ((Integer) obj).intValue();
			} else if (obj instanceof Float) {
				iV = ((Float) obj).intValue();
			} else if (obj instanceof Number) {
				iV = ((Number) obj).intValue();
			}
		}
		return iV;
	}

	public long getLong(int row, String colName) {
		Object obj = getObject(row, colName);
		long lV = 0;
		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				lV = ((Number) obj).longValue();
			} else if (obj instanceof String) {
				lV = Long.parseLong((String) obj);
			} else if (obj instanceof java.util.Date) {
				lV = ((java.util.Date) obj).getTime();
			}
		}
		return lV;
	}

	public long getLong(int row, int col) {
		Object obj = getObject(row, col);
		long lV = 0;
		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				lV = ((Number) obj).longValue();
			} else if (obj instanceof String) {
				lV = Long.parseLong((String) obj);
			} else if (obj instanceof java.util.Date) {
				lV = ((java.util.Date) obj).getTime();
			}
		}
		return lV;
	}

	public float getFloat(int row, String colName) {
		Object obj = getObject(row, colName);
		float fV = 0;

		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				fV = ((Number) obj).floatValue();
			} else if (obj instanceof String) {
				fV = Float.parseFloat((String) obj);
			}
		}
		return fV;
	}

	public float getFloat(int row, int col) {
		Object obj = getObject(row, col);
		float fV = 0;

		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				fV = ((Number) obj).floatValue();
			} else if (obj instanceof String) {
				fV = Float.parseFloat((String) obj);
			}
		}
		return fV;
	}

	public Number getNumber(int row, String colName) {
		Object obj = getObject(row, colName);
		Number nV = null;

		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				nV = (Number) obj;
			} else if (obj instanceof String) {
				nV = Float.valueOf((String) obj);
			}
		}
		return nV;
	}

	public Number getNumber(int row, int col) {
		Object obj = getObject(row, col);
		Number nV = null;

		if (obj != null) {
			if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				nV = (Number) obj;
			} else if (obj instanceof String) {
				nV = Float.valueOf((String) obj);
			}
		}
		return nV;
	}

	public java.util.Date getDate(int row, String colName) {
		Object obj = getObject(row, colName);
		java.util.Date dV = null;
		if (obj != null) {
			if (obj instanceof java.sql.Date
					|| obj instanceof java.sql.Timestamp
					|| obj instanceof java.util.Date) {
				dV = (java.util.Date) obj;
			} else if (obj instanceof String) {
				String pattern = "yyyy-MM-dd";
				if (((String) obj).indexOf("-") > 0) {
					pattern = "yyyy-MM-dd";
				} else if (((String) obj).indexOf("/") > 0) {
					pattern = "yyyy/MM/dd";
				}
				DateUtil dateUtil = new DateUtil();
				dV = dateUtil.stringToDate((String) obj, pattern);
			}
		}
		return dV;
	}

	public java.util.Date getDate(int row, int col) {
		Object obj = getObject(row, col);
		java.util.Date dV = null;
		if (obj != null) {
			if (obj instanceof java.sql.Date
					|| obj instanceof java.sql.Timestamp
					|| obj instanceof java.util.Date) {
				dV = (java.util.Date) obj;
			} else if (obj instanceof String) {
				String pattern = "yyyy-MM-dd";
				if (((String) obj).indexOf("-") > 0) {
					pattern = "yyyy-MM-dd";
				} else if (((String) obj).indexOf("/") > 0) {
					pattern = "yyyy/MM/dd";
				}
				DateUtil dateUtil = new DateUtil();
				dV = dateUtil.stringToDate((String) obj, pattern);
			}
		}
		return dV;
	}

	public boolean getBoolean(int row, String colName) {
		Object obj = getObject(row, colName);
		boolean bV = false;
		if (obj != null) {
			if (obj instanceof Boolean) {
				bV = ((Boolean) obj).booleanValue();
			} else if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				if (((Number) obj).doubleValue() >= 0) {
					bV = true;
				}
			} else if (obj instanceof String) {
				bV = Boolean.getBoolean((String) obj);
			}
		}
		return bV;
	}

	public boolean getBoolean(int row, int col) {
		Object obj = getObject(row, col);
		boolean bV = false;
		if (obj != null) {
			if (obj instanceof Boolean) {
				bV = ((Boolean) obj).booleanValue();
			} else if (obj instanceof Integer || obj instanceof Long
					|| obj instanceof Float
					|| obj instanceof java.math.BigInteger
					|| obj instanceof java.math.BigDecimal) {
				if (((Number) obj).doubleValue() >= 0) {
					bV = true;
				}
			} else if (obj instanceof String) {
				bV = Boolean.getBoolean((String) obj);
			}
		}
		return bV;
	}

	public CodeTable sort(String sortCol) {
		return sort(sortCol, "");
	}

	public CodeTable sort(String sortCol, String sortOrder) {
		boolean isDesc = false;
		if ("DESC".equalsIgnoreCase(sortOrder)) {
			isDesc = true;
		}

		int colType = this.getColType(sortCol);
		boolean isNumber = false;
		switch (colType) {
		case java.sql.Types.TINYINT:
		case java.sql.Types.SMALLINT:
		case java.sql.Types.BIGINT:
		case java.sql.Types.INTEGER:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.DOUBLE:
		case java.sql.Types.FLOAT:
		case java.sql.Types.NUMERIC:
			isNumber = true;
			break;
		case java.sql.Types.CHAR:
		case java.sql.Types.LONGVARCHAR:
		case java.sql.Types.VARCHAR:
			isNumber = false;
		}
		TreeMap sortedMap = new TreeMap();
		if (isNumber) {
			for (int ii = 0; ii < this.size(); ii++) {
				Number key = this.getNumber(ii, sortCol);
				sortedMap.put(key, this.get(ii));
			}
		} else {
			for (int ii = 0; ii < this.size(); ii++) {
				String key = this.getString(ii, sortCol);
				sortedMap.put(key, this.get(ii));
			}
		}

		CodeTable table = (CodeTable) this.clone();
		;
		table.clear();
		if (!isDesc) {
			table.addAll(sortedMap.values());
		} else {
			Object[] keys = sortedMap.keySet().toArray();
			for (int ii = keys.length - 1; ii >= 0; ii--) {
				table.add(sortedMap.get(keys[ii]));
			}
		}
		return table;
	}

	public Vector findRow(Map conditionMap) {
		String[] colNameArr = new String[conditionMap.size()];
		Iterator itKey = conditionMap.keySet().iterator();
		int cnt = 0;
		while (itKey.hasNext()) {
			colNameArr[cnt++] = (String) itKey.next();
		}

		for (int row = 0; row < this.size(); row++) {
			Vector thisRow = (Vector) this.get(row);
			boolean bMatch = true;
			for (int col = 0; col < colNameArr.length; col++) {
				if (col >= (thisRow.size() - 1))
					break;
				int idx = this.colNames.indexOf(colNameArr[col]);
				String val1 = "";
				if (idx >= 0 && idx < this.getCols()) {
					val1 = (String) thisRow.get(idx);
				}
				String val2 = (String) conditionMap.get(colNameArr[col]);
				if (!val1.equals(val2)) {
					bMatch = false;
					break;
				}
			}
			if (bMatch) {
				return thisRow;
			}
		}
		return null;
	}

	public Map findRowMap(Map conditionMap) {
		Vector row = this.findRow(conditionMap);
		if (row != null) {
			HashMap row1 = new HashMap();
			for (int ii = 0; ii < this.colNames.size(); ii++) {
				row1.put(this.colNames.get(ii), row.get(ii));
			}
			return row1;
		}
		return new HashMap();
	}

	public String getCodeValueText(String codeColName, String codeColValue,
			String valColName) {
		if (this.size() < 1) {
			return null;
		}
		try {
			for (int ii = 0; ii < this.size(); ii++) {
				String txt = this.getString(ii, codeColName);
				if (txt.equals(codeColValue)) {
					return this.getString(ii, valColName);
				}
			}
		} catch (Exception ex) {
			log.error("", ex);
		}
		return null;
	}

	public String getCodeValueText(Map codeColMap, String valColName) {
		if (this.size() < 1) {
			return null;
		}
		Vector row = this.findRow(codeColMap);
		int col = this.getColIndex(valColName);
		if (row != null)
			return (String) row.get(col);
		return null;
	}
}
