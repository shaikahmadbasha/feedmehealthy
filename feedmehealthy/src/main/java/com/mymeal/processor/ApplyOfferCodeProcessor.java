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

import com.mymeal.beans.OfferBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.requestvo.ApplyOfferCodeRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;
import com.mymeal.util.MealHelper;

/**
 * @author basha
 *
 */
@Component("applyOfferCodeProcessor")
public class ApplyOfferCodeProcessor extends BaseProcessor {

	private static Logger logger = Logger
			.getLogger(ApplyOfferCodeProcessor.class);
	

	@Autowired
	private MealDAO mealDAO;

	@Override
	public Object processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@Override
	public Object processRequest(HttpServletRequest request,
			HttpServletResponse response, Object jsonReq) {

		logger.info(MealConstants.ENTER);

		BaseResponseVO responseVO = new BaseResponseVO();

		Map<String, Object> responseMap = new HashMap<String, Object>();

		String statusCode = MealConstants.STATUS_ERROR;

		try {

			ApplyOfferCodeRequestVO applyOfferCodeReqVO = (ApplyOfferCodeRequestVO) jsonReq;

			OfferBean offerBean = mealDAO
					.getOfferCodeDetails(applyOfferCodeReqVO.getOfferCode().toLowerCase());

			String offerApplyMsg = MealHelper.offerApplicable(offerBean, applyOfferCodeReqVO.getFoodItems() , applyOfferCodeReqVO.getAmount() );
			
			if (MealConstants.OFFER_VALID.equals(offerApplyMsg)) {
				responseMap.put(ResponseConstants.offerStatus.value(),
						ResponseConstants.VALID.value());
				responseMap.put(ResponseConstants.offerPercentage.value(),
						offerBean.getOfferPercentage());
			} else {
				if(MealConstants.OFFER_NO_MIN_AMT.equals(offerApplyMsg)) {
					responseMap.put(ResponseConstants.offerMessage.value(), "To avail this coupon you need to do minimum purchase of Rs. "  + offerBean.getMinAmt());
				} else if (MealConstants.OFFER_NO_ITMS_MATCH.equals(offerApplyMsg)) {
					responseMap.put(ResponseConstants.offerMessage.value(), "To avail this coupon you need to buy one of " + offerBean.getApplicableItems());
				} else {
					responseMap.put(ResponseConstants.offerMessage.value(), "Invalid Offer Code");
				}
				responseMap.put(ResponseConstants.offerStatus.value(),
						ResponseConstants.INVALID.value());
				responseMap.put(ResponseConstants.offerPercentage.value(), "0");
			}
			
			statusCode = MealConstants.STATUS_SUCCESS;

		} catch (Exception excep) {
			logger.error("Exception while fetching offer code details", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

}
