/*
 * 文 件 名:  ParameterCache.java
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

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;

/**
 * 项目名称：CmEnterpriseProject 类名称：ParameterCache 类描述： 创建人：linch 创建时间：2013-3-27
 * 下午10:48:40 修改人：linch 修改时间：2013-3-27 下午10:48:40 修改备注：
 * 
 * @version
 */

public class ParameterCache extends BaseService implements ICacheable {

	private ICommonService commonService;

	/**
	 * 方法名称: doCache 方法描述:
	 * 
	 * @param cache
	 * @Author: linch
	 * @Create Date: 2013-3-27 下午10:48:40
	 */

	@Override
	public void doCache(CachedData cache) {

		String xFunctionName = "doCache";

		gLogger.begin(xFunctionName);

		try {
			gLogger.info("-------do ParameterCache-------");
			List<TSysParameter> paramList = findParamList();
			if (CollectionUtils.isNotEmpty(paramList)) {
				List<Vector> cacheList = new ArrayList<Vector>(paramList.size());
				for (int i = 0, sizeNo = paramList.size(); i < sizeNo; i++) {
					cacheList = new ArrayList<Vector>(paramList.size());
					Vector<Object> row = new Vector<Object>();
					Object value = null;
					value = paramList.get(i).getParamValue();
					row.add(value);
					cacheList.add(row);
					cache.putCodeTable((paramList.get(i).getParamCode()), cacheList, "PARAMNAME", "PARAMVALUE");
				}
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.begin(xFunctionName);
		}

	}

	/**
	 * 方法名称: findParamList 方法描述: 获取所有的参数表记录
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-1 下午2:16:31
	 */

	public List<TSysParameter> findParamList() {

		String xFunctionName = "findParamList";
		gLogger.begin(xFunctionName);

		try {
			CommonRequestBean requestBean = new CommonRequestBean();
			CommonResponseBean responseBean = commonService.getSysParameter(requestBean);

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
