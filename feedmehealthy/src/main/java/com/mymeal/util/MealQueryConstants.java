/**
 * 
 */
package com.mymeal.util;

/**
 * @author Vishal
 *
 */
public class MealQueryConstants {

	public static final String COL_MENU_NAME = "menu_name";
	// public static final String COL_ITEM_NAME = "item_name";
	public static final String COL_ITEM_PRICE = "item_price";
	public static final String COL_ITEM_QTY = "item_qty";
	public static final String COL_MEAL_NAME = "meal_name";

	public static final String GET_MEAL_TYPE = "select * from meal_type";
	public static final String GET_MENU_TYPE = "select * from menu_type";
	// public static final String GET_MENU_ITEMS =
	// "select menu_items.date,menu_items.meal_name, menu_items.menu_name, menu_items.item_name, food_items.item_price, item_availability.item_qty from menu_items, food_items, item_availability where menu_items.item_name = food_items.item_name and food_items.item_name = item_availability.item_name and menu_items.date = item_availability.date and item_availability.item_qty > 0 and menu_items.date = ?";

	// new coding starts
	public static final String DELIMITER = ",";

	public static final String COL_LOCATION_ID = "location_id";
	public static final String COL_LOCATION_NAME = "location_name";
	public static final String COL_LOCATION_TYPE = "location_type";
	public static final String COL_LOCATION_TIMES = "location_times";
	public static final String COL_HOLIDAY_DATES = "holiday_dates";
	public static final String COL_CATEGORY_ID = "category_id";
	public static final String COL_CATEGORY_NAME = "category_name";
	public static final String COL_ITEM_ID = "item_id";
	public static final String COL_ITEM_NAME = "item_name";
	public static final String COL_DESCRIPTION1 = "description1";
	public static final String COL_DESCRIPTION2 = "description2";
	public static final String COL_PRICE = "price";
	public static final String COL_CALORIE = "calorie";
	public static final String COL_SERVING_QTY = "servingqty";
	public static final String COL_TAG = "tag";
	public static final String COL_IMAGE = "image";
	public static final String COL_AVAILABLE_TIMES = "available_times";
	public static final String COL_ORDER_ID	 = "order_id";
	public static final String COL_CUST_NAME = "cust_name";
	public static final String COL_CUST_MOBILE = "cust_mobile";
	public static final String COL_CUST_EMAIL = "cust_email";
	public static final String COL_DELIVER_DATE = "delivery_date";
	public static final String COL_DELIVER_TIME = "delivery_time";
	public static final String COL_QUANTITY = "quantity";
	public static final String COL_TOTAL = "total";
	public static final String COL_OFFER_CODE = "offer_code";
	public static final String COL_OFFER_PERCENTAGE = "offer_percentage";
	public static final String COL_OFFER_ON_ITEMS = "offer_on_items";
	public static final String COL_OFFER_MIN_AMT = "offer_min_amt";
	public static final String COL_DISCOUNT = "discount";
	public static final String COL_SURCHARGES = "surcharges";
	public static final String COL_INVOICE_NUM = "invoice_num";
	public static final String COL_PASSWORD = "password";
	public static final String COL_ADDRESS_LINE1 = "address_line1";
	public static final String COL_AREA = "area";
	
	public static final String GET_DELIVERY_LOCATIONS = "select * from delivery_locations";
	public static final String GET_LOCATION_HOLIDAYS = "select * from location_holidays";
	public static final String GET_MENU_CATEGORIES = "select * from menu_categories order by clasification";
	public static final String GET_MENU_ITEMS = "select * from menu_items order by clasification";
	public static final String GET_CUSTOMER_DETAILS = "select * from customer_details where cust_mobile = ?";
	public static final String INSERT_CUSTOMER_DETAILS = "insert into customer_details(cust_name, cust_mobile, cust_email, location_id, password, address_line1, area) values(?,?,?,?,?,?,?)";
	public static final String UPDATE_CUSTOMER_DETAILS = "update customer_details set cust_name = ?, cust_email = ?, location_id= ?, password=?,  address_line1=?, area=? where cust_mobile = ?";
	public static final String SAVE_ORDER_DETAILS ="insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SAVE_ORDER_ITEMS ="insert into order_items values(?,?,?,?,?,?,?,?,?)";
	public static final String GET_ORDERS = "select * from orders where delivery_date = ?";
	public static final String GET_OFFER_DETAILS = "select * from offers where offer_code = ?"; 
	public static final String GET_NEXT_INVOICE_NUM = "SELECT nextval('invoice_seq')";
	public static final String INSERT_INVOICE_DETAILS ="insert into invoices values(?,?,?,?)";
	public static final String GET_ORDER_DETAILS = "select * from orders where order_id = ?";
	public static final String GET_ORDER_ITEMS ="select * from order_items where order_id = ?";

}
