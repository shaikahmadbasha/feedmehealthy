/**
 * 
 */
package com.mymeal.util;

/**
 * @author basha
 *
 */
public class MealConstants {

	public static final String STATUS_ERROR = "STATUS_ERROR";
	public static final String STATUS_SUCCESS = "STATUS_OK";

	public static final String ENTER = "Entering method ";
	public static final String EXIT = "Exiting method";

	public static final String EMPTY = "";

	public static final String ORDER_OPEN = "OPEN";

	public static final String DATE_FORMAT = "dd-MMM-yyyy";
	public static final String DATE_SPLIT = "-";
	public static final String YEAR = "YEAR";
	public static final String MONTH = "MONTH";
	public static final String DAY = "DAY";

	public static final int FUTURE_ORDER_DAYS = 5;

	public static final String OFFER_DOES_NOT_EXISTS = "OFFER_DOES_NOT_EXISTS";
	public static final String OFFER_NO_MIN_AMT = "OFFER_NO_MIN_AMT";
	public static final String OFFER_NO_ITMS_MATCH = "OFFER_NO_ITMS_MATCH";
	public static final String OFFER_VALID = "OFFER_VALID";
	public static final String OFFER_INVALID = "OFFER_INVALID";
	public static final int MIN_PURCHASE = 200;
	public static final int DELIVERY_CHARGES = 40;

	public static final String NOT_FOUND = "NOT_FOUND";
	public static final String EMAIL_SENT = "EMAIL_SENT";

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	public enum BeanConstants {
		SubmitOrderProcessor("submitOrderProcessor"), GetDeliveryLocationsProcessor("getDeliveryLocationsProcessor"), GetMenuCategoriesProcessor(
				"getMenuCategoriesProcessor"), GetMenuItemsProcessor("getMenuItemsProcessor"), UpdateCustomerDetailsProcessor(
				"updateCustomerDetailsProcessor"), ApplyOfferCodeProcessor("applyOfferCodeProcessor"), GenerateInvoiceProcessor(
				"generateInvoiceProcessor"), GetReportDetailsProcessor("getReportDetailsProcessor"), LoginProcessor(
				"loginProcessor"), GetCustomerDetailsProcessor("getCustomerDetailsProcessor"), ForgotPasswordProcessor(
				"forgotPasswordProcessor"), SubmitFeedbackProcessor("submitFeedbackProcessor");

		String value;

		BeanConstants(String msg) {
			value = msg;
		}

		public String value() {
			return this.value;
		}
	}

	public enum ResponseConstants {
		MEALS("meals"), deliveryLocations("deliveryLocations"), menuCategories("menuCategories"), menuItems("menuItems"), orderId(
				"orderId"), total("total"), offerStatus("offerStatus"), offerPercentage("offerPercentage"), offerMessage(
				"offerMessage"), VALID("valid"), INVALID("invalid"), SignupResp("signupResp"), LoginResp("loginResp"), SUCCESS(
				"success"), FAIL("fail"), Status("status"), customerDetails("customerDetails"), fgStatus("fgStatus");

		String value;

		ResponseConstants(String msg) {
			value = msg;
		}

		public String value() {
			return this.value;
		}
	}

}
