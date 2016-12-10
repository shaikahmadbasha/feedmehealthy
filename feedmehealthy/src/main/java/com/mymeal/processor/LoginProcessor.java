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

import com.mymeal.beans.CustomerBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.requestvo.LoginRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;

/**
 * @author basha
 *
 */
@Component("loginProcessor")
public class LoginProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(LoginProcessor.class);

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
		String loginResp = ResponseConstants.SUCCESS.value();

		try {
			LoginRequestVO reqVO = (LoginRequestVO) jsonReq;

			CustomerBean customerBean = reqVO.getCustomer();

			loginResp = mealDAO.login(customerBean);

			statusCode = MealConstants.STATUS_SUCCESS;

		} catch (Exception excep) {
			logger.error("Exception while login", excep);
			loginResp = ResponseConstants.FAIL.value();
		} finally {
			responseVO.setStatusCode(statusCode);
			responseMap.put(ResponseConstants.Status.value(), loginResp);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

}
