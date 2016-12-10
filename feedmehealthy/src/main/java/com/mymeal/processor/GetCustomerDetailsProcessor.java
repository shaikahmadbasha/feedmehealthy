/**
 * 
 */
package com.mymeal.processor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mymeal.dao.MealDAO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;

/**
 * @author basha
 *
 */
@Component("getCustomerDetailsProcessor")
public class GetCustomerDetailsProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(GetCustomerDetailsProcessor.class);

	@Autowired
	private MealDAO mealDAO;

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonReq) {
		logger.info(MealConstants.ENTER);

		BaseResponseVO responseVO = new BaseResponseVO();

		Map<String, Object> responseMap = new HashMap<String, Object>();

		String statusCode = MealConstants.STATUS_ERROR;

		try {

			responseMap.put(ResponseConstants.customerDetails.value(), mealDAO.getCustomerDetails((String)jsonReq));

			statusCode = MealConstants.STATUS_SUCCESS;

		} catch (Exception excep) {
			logger.error("Exception while fetching customer details", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
