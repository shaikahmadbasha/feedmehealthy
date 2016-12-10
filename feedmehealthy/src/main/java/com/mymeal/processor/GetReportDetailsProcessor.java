/**
 * 
 */
package com.mymeal.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mymeal.beans.OrderBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.requestvo.GetReportDetailsRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealHelper;

/**
 * @author basha
 *
 */
@Component("getReportDetailsProcessor")
public class GetReportDetailsProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(GetReportDetailsProcessor.class);

	@Autowired
	private MealDAO mealDAO;

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonReq) {

		logger.info(MealConstants.ENTER);

		BaseResponseVO responseVO = new BaseResponseVO();

		Map<String, Object> responseMap = new HashMap<String, Object>();

		String statusCode = MealConstants.STATUS_ERROR;

		try {
			GetReportDetailsRequestVO reqVO = (GetReportDetailsRequestVO) jsonReq;

			String date = reqVO.getDate();

			List<OrderBean> orderBeanList = null;

			if (!MealHelper.isEmpty(date)) {
				orderBeanList = mealDAO.getReportOrderDetails(date);
				responseMap.put("ordersList", orderBeanList);
				statusCode = MealConstants.STATUS_SUCCESS;
			}

		} catch (Exception excep) {
			logger.error("Exception while fetching order details", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

}
