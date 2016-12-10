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
import com.mymeal.requestvo.UpdateCustomerDetailsRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;

/**
 * @author basha
 *
 */
@Component("updateCustomerDetailsProcessor")
public class UpdateCustomerDetailsProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(UpdateCustomerDetailsProcessor.class);

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
		String signupResp = ResponseConstants.SUCCESS.value();
		
		try {
			UpdateCustomerDetailsRequestVO reqVO = (UpdateCustomerDetailsRequestVO) jsonReq;

			CustomerBean customerBean = reqVO.getCustomer();

			int status = mealDAO.insertOrUpdateCustDtls(customerBean);

			if (status == 1) {
				logger.info("Customer details are inserted ");
			} else if (status == 2) {
				logger.info("Customer details are updated");
			}

			statusCode = MealConstants.STATUS_SUCCESS;

		} catch (Exception excep) {
			logger.error("Exception while updating customer details", excep);
			signupResp = ResponseConstants.FAIL.value();
		} finally {
			responseVO.setStatusCode(statusCode);
			responseMap.put(ResponseConstants.SignupResp.value(), signupResp);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

}
