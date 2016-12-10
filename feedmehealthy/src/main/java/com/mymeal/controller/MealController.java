/**
 * 
 */
package com.mymeal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mymeal.processor.BaseProcessor;
import com.mymeal.requestvo.ApplyOfferCodeRequestVO;
import com.mymeal.requestvo.GenerateInvoiceRequestVO;
import com.mymeal.requestvo.GetReportDetailsRequestVO;
import com.mymeal.requestvo.LoginRequestVO;
import com.mymeal.requestvo.SubmitFeedbackRequestVO;
import com.mymeal.requestvo.SubmitOrderRequestVO;
import com.mymeal.requestvo.UpdateCustomerDetailsRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.AppContext;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.BeanConstants;

/**
 * @author basha
 *
 */
@Controller
public class MealController {

	private static Logger logger = Logger.getLogger(MealController.class);

	@Autowired
	private AppContext appContext;

	@RequestMapping(value = "welcome")
	public void printWelcome(HttpServletRequest request, HttpServletResponse response) {
		logger.info("logging is enabled........");
	}

	@RequestMapping(value = "getDeliveryLocations", produces = "application/json")
	@ResponseBody
	public BaseResponseVO getDeliveryLocations(HttpServletRequest request, HttpServletResponse response) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GetDeliveryLocationsProcessor.value())).processRequest(request, response);
		return responseVO;
	}

	@RequestMapping(value = "getMenuCategories")
	@ResponseBody
	public BaseResponseVO getMenuCategories(HttpServletRequest request, HttpServletResponse response) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GetMenuCategoriesProcessor.value())).processRequest(request, response);
		return responseVO;
	}

	@RequestMapping(value = "getMenuItems")
	@ResponseBody
	public BaseResponseVO getMenuItems(HttpServletRequest request, HttpServletResponse response) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GetMenuItemsProcessor.value())).processRequest(request, response);
		return responseVO;
	}

	@RequestMapping(value = "updateCustomerDetails", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO updateCustomerDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody UpdateCustomerDetailsRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.UpdateCustomerDetailsProcessor.value())).processRequest(request, response,
				requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "getCustomerDetails", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO getCustomerDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GetCustomerDetailsProcessor.value())).processRequest(request, response,
				requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "forgotPassword", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO forgotPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.ForgotPasswordProcessor.value())).processRequest(request, response,
				requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "login", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.LoginProcessor.value())).processRequest(request, response,
				requestVO);
		return responseVO;
	}

	/**
	 * submitOrder.
	 * 
	 * @param request
	 * @param response
	 * @param requestVO
	 * @return
	 */
	@RequestMapping(value = "submitOrder", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO submitOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SubmitOrderRequestVO requestVO) {
		logger.info(MealConstants.ENTER + requestVO);
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.SubmitOrderProcessor.value())).processRequest(request, response, requestVO);
		return responseVO;
	}

	
	@RequestMapping(value = "applyOfferCode", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO applyOfferCode(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ApplyOfferCodeRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.ApplyOfferCodeProcessor.value())).processRequest(request, response, requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "generateInvoice", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO generateInvoice(HttpServletRequest request, HttpServletResponse response,
			@RequestBody GenerateInvoiceRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GenerateInvoiceProcessor.value())).processRequest(request, response, requestVO);
		return responseVO;
	}
	
	@RequestMapping(value = "getReports", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO getReports(HttpServletRequest request, HttpServletResponse response,
			@RequestBody GetReportDetailsRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.GetReportDetailsProcessor.value())).processRequest(request, response, requestVO);
		return responseVO;
	}

	@RequestMapping(value = "submitFeedback", consumes = "application/json")
	@ResponseBody
	public BaseResponseVO submitFeedback(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SubmitFeedbackRequestVO requestVO) {
		BaseResponseVO responseVO = (BaseResponseVO) ((BaseProcessor) appContext
				.getBean(BeanConstants.SubmitFeedbackProcessor.value())).processRequest(request, response, requestVO);
		return responseVO;
	}
}
