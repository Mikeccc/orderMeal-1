package app.cn.qtt.bm.common.cache;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class CachedData extends HashMap {
	private static Log log = LogFactory.getLog(CachedData.class);

	public CachedData() {
		super();
	}

	public void putCodeTable(String name, ResultSet rs) {
		this.putCodeTable(name, new CodeTable(rs));
	}

	public void putCodeTable(String name, List value) {
		this.putCodeTable(name, new CodeTable(value));
	}

	public void putCodeTable(String name, List value, String colName,
			String colType) {
		this.putCodeTable(name, new CodeTable(value, colName, colType));
	}

	public void putCodeTable(String name, List value, String[] colName) {
		this.putCodeTable(name, new CodeTable(value, colName));
	}

	public void putCodeTable(String name, CodeTable table) {
		if (name == null) {
			throw new IllegalArgumentException("Name cann't be null!");
		}
		this.put(name.toUpperCase(), table);
	}

	public CodeTable getCodeTable(String name) {
		if (name == null) {
			log.error("Name cann't be null!");
			return null;
		}
		return (CodeTable) this.get(name.toUpperCase());
	}

	public CodeTable getSortedCodeTable(String name, String sortCol) {
		return getSortedCodeTable(name, sortCol, "");
	}

	public CodeTable getSortedCodeTable(String name, String sortCol,
			String sortOrder) {
		if (name == null) {
			log.error("Name cann't be null!");
			return null;
		}
		return ((CodeTable) this.get(name.toUpperCase())).sort(sortCol,
				sortOrder);
	}

	public void clear() {
		String[] tableNames = this.getTableNames();
		for (int ii = 0; ii < tableNames.length; ii++) {
			((CodeTable) get(tableNames[ii])).clear();
			this.remove(tableNames[ii]);
		}
		super.clear();
	}

	/**
	 * 根据参数名称获取参数值
	 * 
	 * @param paramName
	 * @return
	 */
	public String getParameterValueByName(String paramName) {
		CodeTable codeTable = getCodeTable(paramName);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				String dmmc = codeTable.getString(0, 0);
				if (dmmc != null) {
					return dmmc;
				}
			}
		}
		return null;
	}

	/**
	 * 根据代码类型和代码名称取得代码显示值
	 * 
	 * @param dmlx
	 * @param dm_mc
	 * @return
	 */
	public String getCodeShowValueByCodeTypeAndCodeName(String codeType,
			String codeName) {
		CodeTable codeTable = getCodeTable(codeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object dmmc = codeTable.getString(ii, "CODENAME");
				if (dmmc != null && dmmc.equals(codeName)) {
					return codeTable.getString(ii, "CODESHOWNAME");
				}
			}
		}
		return null;
	}

	/**
	 * 根据代码类型和代码名称取得代码值
	 * 
	 * @param dmlx
	 * @param dm_mc
	 * @return
	 */
	public String getCodeValueByCodeTypeAndCodeName(String codeType,
			String codeName) {
		CodeTable codeTable = getCodeTable(codeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object dmmc = codeTable.getString(ii, "CODENAME");
				if (dmmc != null && dmmc.equals(codeName)) {
					return codeTable.getString(ii, "CODEVALUE");
				}
			}
		}
		return null;
	}

	
	
	
	/**
	* 方法名称:      getShowNameByCodeTypeByValue  
	* 方法描述:      
	* @param codeType
	* @param codeValue
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-17 下午3:58:21
	*/ 
	 
	public String getShowNameByCodeTypeByValue(String codeType,
			String codeValue) {
		CodeTable codeTable = getCodeTable(codeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object value = codeTable.getString(ii, "CODEVALUE");
				if (value != null && value.equals(codeValue)) {
					 return codeTable.getString(ii, "CODESHOWNAME");
				}
			}
		}
		return null;
	}
	
	public String getParCodeByCodeTypeByValue(String codeType,
			String codeValue) {
		CodeTable codeTable = getCodeTable(codeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object value = codeTable.getString(ii, "CODENAME");
				if (value != null && value.equals(codeValue)) {
					 return codeTable.getString(ii, "CODEPARENTVALUE");
				}
			}
		}
		return null;
	}
	/**
	 * 
	* @Title: getCodeNameByCodeTypeByValue
	* @Description: getCodeNameByCodeTypeByValue
	* @param @param codeType
	* @param @param codeValue
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getCodeNameByCodeTypeByValue(String codeType,
			String codeValue) {
		CodeTable codeTable = getCodeTable(codeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object value = codeTable.getString(ii, "CODEVALUE");
				if (value != null && value.equals(codeValue)) {
					 return codeTable.getString(ii, "CODENAME");
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据代码类型和代码名称取得代码显示值
	 * 
	 * @param dmlx
	 * @param dm_mc
	 * @return
	 */
	public List<String[]> getSubCodeValueByParentCodeTypeAndValue(
			String parentCodeValue, String subCodeType) {
		List<String[]> rtnCodeList = new ArrayList<String[]>();
		CodeTable codeTable = getCodeTable(subCodeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object parentValue = codeTable.getString(ii, "CODEPARENTVALUE");
				if (parentValue != null && parentValue.equals(parentCodeValue)) {
					rtnCodeList.add(new String[] {
							codeTable.getString(ii, "CODETYPE"),
							codeTable.getString(ii, "CODENAME"),
							codeTable.getString(ii, "CODEVALUE"),
							codeTable.getString(ii, "CODESHOWNAME"),
							codeTable.getString(ii, "CODEFILTER"),
							codeTable.getString(ii, "CODESORT"),
							codeTable.getString(ii, "CODEPARENTVALUE"),
							codeTable.getString(ii, "CODEDESCRIPT") });
				}
			}
		}

		return rtnCodeList;
	}

	/**
	 *getParentCodeValueByCodeTypeAndValue
	 * 根据子节点获取父节点的值
	 * @param codeChildValue
	 * @param subCodeType
	 * @return
	 */
	public Map<String, String> getParentCodeValueByCodeTypeAndValue(
			String codeChildValue, String subCodeType) {
		Map<String, String> map = null;
		CodeTable codeTable = getCodeTable(subCodeType);
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object parentValue = codeTable.getString(ii, "CODENAME");
				if (parentValue != null && parentValue.equals(codeChildValue)) {
					map = new HashMap<String, String>();
					map.put("CODETYPE", codeTable.getString(ii, "CODETYPE"));
					map.put("CODENAME", codeTable.getString(ii, "CODENAME"));
					map.put("CODEVALUE", codeTable.getString(ii, "CODEVALUE"));
					map.put("CODESHOWNAME",
							codeTable.getString(ii, "CODESHOWNAME"));
					map.put("CODEFILTER", codeTable.getString(ii, "CODEFILTER"));
					map.put("CODESORT", codeTable.getString(ii, "CODESORT"));
					map.put("CODEPARENTVALUE",
							codeTable.getString(ii, "CODEPARENTVALUE"));
					map.put("CODEDESCRIPT",
							codeTable.getString(ii, "CODEDESCRIPT"));
				}
			}}
		return map;
		
	
	}

	/**
	 * 方法名称: getMapCodeValueByCodeType 方法描述: 根据codeType获取code
	 * 
	 * @param subCodeType
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-6 下午7:26:26
	 */

	public List<Map<String, String>> getMapCodeValueByCodeType(
			String subCodeType) {
		List<Map<String, String>> rtnCodeList = new ArrayList<Map<String, String>>();
		CodeTable codeTable = getCodeTable(subCodeType);
		if (codeTable != null) {
			Map<String, String> map = null;
			for (int ii = 0; ii < codeTable.size(); ii++) {
				map = new HashMap<String, String>();
				map.put("CODETYPE", codeTable.getString(ii, "CODETYPE"));
				map.put("CODENAME", codeTable.getString(ii, "CODENAME"));
				map.put("CODEVALUE", codeTable.getString(ii, "CODEVALUE"));
				map.put("CODESHOWNAME", codeTable.getString(ii, "CODESHOWNAME"));
				map.put("CODEFILTER", codeTable.getString(ii, "CODEFILTER"));
				map.put("CODESORT", codeTable.getString(ii, "CODESORT"));
				map.put("CODEPARENTVALUE",
						codeTable.getString(ii, "CODEPARENTVALUE"));
				map.put("CODEDESCRIPT", codeTable.getString(ii, "CODEDESCRIPT"));
				rtnCodeList.add(map);
			}
		}
		return rtnCodeList;
	}

	/**
	 * 方法名称: getMapCodeValueByParentCodeTypeAndValue 方法描述: 返回map类型
	 * 
	 * @param parentCodeValue
	 * @param subCodeType
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-6 上午10:28:08
	 */

	public List<Map<String, String>> getMapCodeValueByParentCodeTypeAndValue(
			String parentCodeValue, String subCodeType) {
		List<Map<String, String>> rtnCodeList = new ArrayList<Map<String, String>>();
		CodeTable codeTable = getCodeTable(subCodeType);
		if (codeTable != null) {
			Map<String, String> map = null;
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object parentValue = codeTable.getString(ii, "CODEPARENTVALUE");
				if (parentValue != null && parentValue.equals(parentCodeValue)) {
					map = new HashMap<String, String>();
					map.put("CODETYPE", codeTable.getString(ii, "CODETYPE"));
					map.put("CODENAME", codeTable.getString(ii, "CODENAME"));
					map.put("CODEVALUE", codeTable.getString(ii, "CODEVALUE"));
					map.put("CODESHOWNAME",
							codeTable.getString(ii, "CODESHOWNAME"));
					map.put("CODEFILTER", codeTable.getString(ii, "CODEFILTER"));
					map.put("CODESORT", codeTable.getString(ii, "CODESORT"));
					map.put("CODEPARENTVALUE",
							codeTable.getString(ii, "CODEPARENTVALUE"));
					map.put("CODEDESCRIPT",
							codeTable.getString(ii, "CODEDESCRIPT"));
					rtnCodeList.add(map);
				}
			}
		}
		return rtnCodeList;
	}

	/**
	 * 方法名称: getMapRootCodeValueByParentCodeTypeAndValue 方法描述: 获取根路径
	 * 
	 * @param subCodeType
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-6 下午1:32:31
	 */

	public List<Map<String, String>> getMapRootCodeValueByParentCodeTypeAndValue(
			String subCodeType) {
		List<Map<String, String>> rtnCodeList = new ArrayList<Map<String, String>>();
		CodeTable codeTable = getCodeTable(subCodeType);
		Map<String, String> map = null;
		if (codeTable != null) {
			for (int ii = 0; ii < codeTable.size(); ii++) {
				Object parentValue = codeTable.getString(ii, "CODEPARENTVALUE");
				if (StringUtils.isBlank((String) parentValue)) {
					map = new HashMap<String, String>();
					map.put("CODETYPE", codeTable.getString(ii, "CODETYPE"));
					map.put("CODENAME", codeTable.getString(ii, "CODENAME"));
					map.put("CODEVALUE", codeTable.getString(ii, "CODEVALUE"));
					map.put("CODESHOWNAME",
							codeTable.getString(ii, "CODESHOWNAME"));
					map.put("CODEFILTER", codeTable.getString(ii, "CODEFILTER"));
					map.put("CODESORT", codeTable.getString(ii, "CODESORT"));
					map.put("CODEPARENTVALUE",
							codeTable.getString(ii, "CODEPARENTVALUE"));
					map.put("CODEDESCRIPT",
							codeTable.getString(ii, "CODEDESCRIPT"));
					rtnCodeList.add(map);
				}
			}
		}
		return rtnCodeList;
	}

	public String[] getTableNames() {
		return ((String[]) this.keySet().toArray(new String[0]));
	}

	public String getCodeValueText(String tableName, String codeColName,
			String codeColValue, String valColName) {
		CodeTable codeTable = getCodeTable(tableName);
		if (codeTable == null || codeTable.size() < 1) {
			return null;
		}
		return codeTable
				.getCodeValueText(codeColName, codeColValue, valColName);
	}

	public String getCodeValueText(String tableName, Map codeColMap,
			String valColName) {
		CodeTable codeTable = getCodeTable(tableName);
		if (codeTable == null || codeTable.size() < 1) {
			return null;
		}
		return codeTable.getCodeValueText(codeColMap, valColName);
	}

	boolean isContain(String tableName) {
		return this.keySet().contains(tableName);
	}
}
