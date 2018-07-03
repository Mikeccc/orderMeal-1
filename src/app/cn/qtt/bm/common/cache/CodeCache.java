/*
 * 文 件 名:  CodeCache.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-3-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package app.cn.qtt.bm.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.model.TSysCodeTree;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;

/**
 * 项目名称：CmEnterpriseProject 类名称：CodeCache 类描述： 创建人：linch 创建时间：2013-3-27
 * 下午10:51:39 修改人：linch 修改时间：2013-3-27 下午10:51:39 修改备注：
 * 
 * @version
 */

public class CodeCache extends BaseService implements ICacheable {

	private ICommonService commonService;

	/**
	 * 方法名称: doCache 方法描述:
	 * 
	 * @param cache
	 * @Author: linch
	 * @Create Date: 2013-3-27 下午10:51:39
	 */

	@Override
	public void doCache(CachedData cache) {

		String xFunctionName = "doCache";

		gLogger.begin(xFunctionName);
		try {

			// TODO 取数据
			List<TSysCodeTree> codeGroudArrs = findCodeGrouds();

			if (CollectionUtils.isNotEmpty(codeGroudArrs)) {

				Vector<String> types = findAllTypeByAllCodeGroud(codeGroudArrs);
				if (CollectionUtils.isNotEmpty(types)) {
					for (String type : types) {
						// 根据类别取数据
						List<TSysCodeTree> codeList = findCodeGroudsByType(codeGroudArrs, type);

						if (CollectionUtils.isNotEmpty(codeList)) {
							List<Vector> cacheList = new ArrayList<Vector>(codeList.size());
							for (TSysCodeTree code : codeList) {
								Vector<Object> row = new Vector<Object>();
								row.add(code.getCodeType());
								row.add(code.getCodeName());
								row.add(code.getCodeValue());
								row.add(code.getCodeShowName());
								row.add(code.getCodeFilter());
								row.add(code.getCodeSort());
								row.add(code.getCodeParentValue());
								row.add(code.getCodeDescript());
								cacheList.add(row);
							}
							cache.putCodeTable(type, cacheList, new String[] { "CODETYPE", "CODENAME", "CODEVALUE",
									"CODESHOWNAME", "CODEFILTER", "CODESORT", "CODEPARENTVALUE", "CODEDESCRIPT" });
						}
					}
				}
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			// TODO: handle exception
		} finally {
			gLogger.end(xFunctionName);
		}
	}

	/**
	 * 方法名称: findAllTypeByAllCodeGroud 方法描述: 获取所有的code类别
	 * 
	 * @param codeGrouds
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-1 下午2:42:09
	 */

	public Vector<String> findAllTypeByAllCodeGroud(List<TSysCodeTree> codeGrouds) {
		String xFunctionName = "findAllTypeByAllCodeGroud";
		gLogger.begin(xFunctionName);
		Vector<String> types = new Vector<String>();
		try {
			if (CollectionUtils.isNotEmpty(codeGrouds)) {
				for (TSysCodeTree tSysCode : codeGrouds) {
					if (types.indexOf(tSysCode.getCodeType()) < 0) {
						types.add(tSysCode.getCodeType());
					}
				}
			}

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.begin(xFunctionName);
		}

		return types;
	}

	/**
	 * 方法名称: findCodeGroudsByType 方法描述: 根据类型获取相应类型的code
	 * 
	 * @param codeGrouds
	 * @param type
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-1 下午2:47:52
	 */

	public List<TSysCodeTree> findCodeGroudsByType(List<TSysCodeTree> codeGrouds, String type) {
		String xFunctionName = "findAllTypeByAllCodeGroud";
		gLogger.begin(xFunctionName);
		List<TSysCodeTree> types = new ArrayList<TSysCodeTree>();
		try {
			if (CollectionUtils.isNotEmpty(codeGrouds) && StringUtils.isNotBlank(type)) {
				for (TSysCodeTree tSysCode : codeGrouds) {
					if (type.indexOf(tSysCode.getCodeType()) > -1) {
						types.add(tSysCode);
					}
				}
			}

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.begin(xFunctionName);
		}

		return types;
	}

	/**
	 * 方法名称: findcodeGroud 方法描述: 获取code
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-1 下午2:29:35
	 */

	public List<TSysCodeTree> findCodeGrouds() {

		String xFunctionName = "findParamList";
		gLogger.begin(xFunctionName);

		try {
			CommonRequestBean requestBean = new CommonRequestBean();
			CommonResponseBean responseBean = commonService.getSyscode(requestBean);

			if (!"00".equals(responseBean.getResponseCode())) {
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				return null;
			} else {
				return responseBean.getList();
			}

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.begin(xFunctionName);
		}

		return null;

	}

	public ICommonService getCommonService() {

		return commonService;
	}

	public void setCommonService(ICommonService commonService) {

		this.commonService = commonService;
	}

}
