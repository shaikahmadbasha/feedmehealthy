/**
 * 
 */
package com.mymeal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mymeal.beans.CustomerBean;
import com.mymeal.beans.DeliveryLocationBean;
import com.mymeal.beans.LocDateTimesBean;
import com.mymeal.beans.MenuCategoryBean;
import com.mymeal.beans.MenuItemBean;
import com.mymeal.beans.OfferBean;
import com.mymeal.beans.OrderBean;
import com.mymeal.exception.MealException;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealQueryConstants;

/**
 * @author basha
 *
 */
public class MealDAOImpl implements MealDAO {

	private static Logger logger = Logger.getLogger(MealDAOImpl.class);

	private DataSource dataSource;
	private Connection connection;

	public MealDAOImpl() {
		super();
	}

	public MealDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() throws SQLException {

		if (null == connection) {
			connection = dataSource.getConnection();
		}
		return connection;

	}

	/**
	 * getDeliveryLocations.
	 * 
	 * @return
	 * @throws MealException
	 */
	public List<DeliveryLocationBean> getDeliveryLocations() throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;

		List<DeliveryLocationBean> deliveryLocBeanList = new ArrayList<DeliveryLocationBean>();

		try {

			stmt = getConnection().createStatement();

			// Get Holidays for each location
			Map<String, String> locHolidayMap = new HashMap<String, String>();

			rs = stmt.executeQuery(MealQueryConstants.GET_LOCATION_HOLIDAYS);

			while (rs.next()) {
				locHolidayMap.put(rs.getString(MealQueryConstants.COL_LOCATION_ID),
						rs.getString(MealQueryConstants.COL_HOLIDAY_DATES));
			}
			close(rs, null, null);

			// Get Delivery Locations
			rs = stmt.executeQuery(MealQueryConstants.GET_DELIVERY_LOCATIONS);

			while (rs.next()) {
				DeliveryLocationBean deliveryLocBean = new DeliveryLocationBean();
				deliveryLocBean.setLocationId(rs.getString(MealQueryConstants.COL_LOCATION_ID));
				deliveryLocBean.setLocationName(rs.getString(MealQueryConstants.COL_LOCATION_NAME));
				deliveryLocBean.setLocationType(rs.getString(MealQueryConstants.COL_LOCATION_TYPE));

				List<String> locTimeList = MealDAOUtil.getAsList(rs.getString(MealQueryConstants.COL_LOCATION_TIMES),
						MealQueryConstants.DELIMITER);
				List<LocDateTimesBean> locDateTimesList = new ArrayList<LocDateTimesBean>();

				List<String> locationAvbleDates = new ArrayList<String>();

				List<String> locHolidayDates = MealDAOUtil.getAsList(
						locHolidayMap.get(deliveryLocBean.getLocationId()), MealQueryConstants.DELIMITER);

				Calendar calendar = Calendar.getInstance();
				TimeZone tz = TimeZone.getTimeZone("IST");
				calendar.setTimeZone(tz);
				
				SimpleDateFormat sd = new SimpleDateFormat(MealConstants.DATE_FORMAT);
				sd.setTimeZone(tz);

				boolean today = true;
				for (int i = 1; i <= MealConstants.FUTURE_ORDER_DAYS; i++) {
					java.util.Date dt = calendar.getTime();
					String date = sd.format(dt);

					if (!locHolidayDates.contains(date)) {
						

						if (today) {
							List<String> updLocTimeList = new ArrayList<String>();

							SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
							sdf.setTimeZone(tz);
							
							Calendar cal = Calendar.getInstance();
							Date mToday = new Date();
							//calendar.setTimeZone(tz);
							cal.setTime(mToday);
							cal.add(Calendar.MINUTE, 30);
							
							String curTime = sdf.format(cal.getTime());
							Date now = sdf.parse(curTime);
							
							for (String time : locTimeList) {
								Date start = sdf.parse(time);
								if(now.before(start)) {
									updLocTimeList.add(time);
						        } 
							}
							LocDateTimesBean locDtTmBean = new LocDateTimesBean();
							locDtTmBean.setLocationDate(date);
							locDtTmBean.setLocationTimes(updLocTimeList);
							locDateTimesList.add(locDtTmBean);
							
							if(null != updLocTimeList && !updLocTimeList.isEmpty()) {
								locationAvbleDates.add(date);
							}

						} else {
							LocDateTimesBean locDtTmBean = new LocDateTimesBean();
							locDtTmBean.setLocationDate(date);
							locDtTmBean.setLocationTimes(locTimeList);
							locDateTimesList.add(locDtTmBean);
							locationAvbleDates.add(date);
						}
						
					}
					calendar.add(Calendar.DATE, 1);
					today = false;
				}

				deliveryLocBean.setLocationDates(locationAvbleDates);
				deliveryLocBean.setLocationTimes(locDateTimesList);
				deliveryLocBeanList.add(deliveryLocBean);
			}
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing delivery locations", excep);
		} catch (ParseException e) {
			throw new MealException("Exception occured while retrieveing delivery locations", e);
		} finally {
			close(rs, stmt, null);
		}

		logger.info(MealConstants.EXIT);
		return deliveryLocBeanList;
	}

	/**
	 * getMenuCategories.
	 */
	public List<MenuCategoryBean> getMenuCategories() throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;

		List<MenuCategoryBean> menuCatBeanList = new ArrayList<MenuCategoryBean>();

		try {
			stmt = getConnection().createStatement();

			// Get Menu Categories
			rs = stmt.executeQuery(MealQueryConstants.GET_MENU_CATEGORIES);

			while (rs.next()) {
				MenuCategoryBean menuCatBean = new MenuCategoryBean();
				menuCatBean.setCategoryId(rs.getString(MealQueryConstants.COL_CATEGORY_ID));
				menuCatBean.setCategoryName(rs.getString(MealQueryConstants.COL_CATEGORY_NAME));

				menuCatBeanList.add(menuCatBean);
			}
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing menu categories", excep);
		} finally {
			close(rs, stmt, null);
		}

		logger.info(MealConstants.EXIT);
		return menuCatBeanList;
	}

	/**
	 * getMenuItems.
	 * 
	 * @return
	 * @throws MealException
	 */
	public List<MenuItemBean> getMenuItems() throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;

		List<MenuItemBean> menuItemBeanList = new ArrayList<MenuItemBean>();

		try {
			stmt = getConnection().createStatement();

			// Get Menu Items
			rs = stmt.executeQuery(MealQueryConstants.GET_MENU_ITEMS);

			while (rs.next()) {
				MenuItemBean menuItemBean = new MenuItemBean();
				menuItemBean.setItemId(rs.getString(MealQueryConstants.COL_ITEM_ID));
				menuItemBean.setItemName(rs.getString(MealQueryConstants.COL_ITEM_NAME));
				menuItemBean.setDescription1(rs.getString(MealQueryConstants.COL_DESCRIPTION1));
				menuItemBean.setDescription2(rs.getString(MealQueryConstants.COL_DESCRIPTION2));
				menuItemBean.setPrice(rs.getInt(MealQueryConstants.COL_PRICE));
				menuItemBean.setCalorie(rs.getString(MealQueryConstants.COL_CALORIE));
				menuItemBean.setServingQty(rs.getString(MealQueryConstants.COL_SERVING_QTY));
				menuItemBean.setCategoryNames(rs.getString(MealQueryConstants.COL_CATEGORY_NAME));
				menuItemBean.setTag(rs.getString(MealQueryConstants.COL_TAG));
				menuItemBean.setImageName(rs.getString(MealQueryConstants.COL_IMAGE));
				menuItemBean.setAvailableTimes(MealDAOUtil.getAsList(
						rs.getString(MealQueryConstants.COL_AVAILABLE_TIMES), MealQueryConstants.DELIMITER));

				// TODO: create a table item_non_available_dates, read that and
				// make future order days as false for this item
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sd = new SimpleDateFormat(MealConstants.DATE_FORMAT);

				Map<String, String> availableDates = new HashMap<String, String>();

				for (int i = 0; i < MealConstants.FUTURE_ORDER_DAYS; i++) {
					java.util.Date dt = calendar.getTime();
					String date = sd.format(dt);
					availableDates.put(date, "true");
				}
				menuItemBean.setAvailableDates(availableDates);

				menuItemBeanList.add(menuItemBean);
			}
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing menu categories", excep);
		} finally {
			close(rs, stmt, null);
		}

		logger.info(MealConstants.EXIT);
		return menuItemBeanList;
	}

	/**
	 * insertOrUpdateCustDtls.
	 * 
	 * @param customerBean
	 * @return
	 * @throws MealException
	 */
	public int insertOrUpdateCustDtls(CustomerBean customerBean) throws MealException {
		logger.info(MealConstants.ENTER);

		int retVal = 0;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// Get Customer Details
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_CUSTOMER_DETAILS);
			pstmt.setString(1, customerBean.getPhone());
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				// Insert customer details
				PreparedStatement updatePstmt = getConnection().prepareStatement(
						MealQueryConstants.INSERT_CUSTOMER_DETAILS);
				updatePstmt.setString(1, customerBean.getName());
				updatePstmt.setString(2, customerBean.getPhone());
				updatePstmt.setString(3, customerBean.getEmail());
				updatePstmt.setString(4, customerBean.getLocationId());
				updatePstmt.setString(5, customerBean.getPassword());
				updatePstmt.setString(6, customerBean.getAddLine1());
				updatePstmt.setString(7, customerBean.getArea());
				updatePstmt.executeUpdate();
				close(null, null, updatePstmt);
				retVal = 1;
			} else {
				// Update the customer details
				PreparedStatement updatePstmt = getConnection().prepareStatement(
						MealQueryConstants.UPDATE_CUSTOMER_DETAILS);
				updatePstmt.setString(1, customerBean.getName());
				updatePstmt.setString(2, customerBean.getEmail());
				updatePstmt.setString(3, customerBean.getLocationId());
				updatePstmt.setString(4, customerBean.getPassword());
				updatePstmt.setString(5, customerBean.getAddLine1());
				updatePstmt.setString(6, customerBean.getArea());
				updatePstmt.setString(7, customerBean.getPhone());
				updatePstmt.executeUpdate();
				close(null, null, updatePstmt);
				retVal = 2;
			}

		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing menu categories", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return retVal;
	}

	/**
	 * saveOrder.
	 * 
	 * @param orderBean
	 * @return
	 * @throws MealException
	 */
	public long saveOrder(OrderBean orderBean) throws MealException {
		long invoiceNum = 0;
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(MealQueryConstants.GET_NEXT_INVOICE_NUM);
			while (rs.next()) {
				invoiceNum = rs.getLong(1);
			}
			close(rs, null, null);
			
			pstmt = getConnection().prepareStatement(MealQueryConstants.SAVE_ORDER_DETAILS);
			pstmt.setString(1, orderBean.getOrderId());
			pstmt.setString(2, orderBean.getLocationId());
			pstmt.setString(3, orderBean.getLocationName());
			pstmt.setString(4, orderBean.getDeliveryTime());
			pstmt.setString(5, orderBean.getDeliveryDate());
			if (null != orderBean.getCustomer()) {
				CustomerBean cust = orderBean.getCustomer();
				pstmt.setString(6, cust.getName());
				pstmt.setString(7, cust.getPhone());
				pstmt.setString(14, cust.getEmail());
			}
			pstmt.setString(8, MealConstants.EMPTY);
			pstmt.setDouble(9, orderBean.getTotal());
			pstmt.setDouble(10, orderBean.getDiscountAmount());
			pstmt.setDouble(11, orderBean.getSurcharge());
			pstmt.setDouble(12, orderBean.getTotal() - orderBean.getDiscountAmount() + orderBean.getSurcharge());
			pstmt.setLong(13, invoiceNum);
			
			pstmt.executeUpdate();
			
			close(null, null, pstmt);
			
			pstmt = getConnection().prepareStatement(MealQueryConstants.INSERT_INVOICE_DETAILS);
			pstmt.setLong(1, invoiceNum);
			pstmt.setString(2, orderBean.getDeliveryDate());
			pstmt.setDouble(3, orderBean.getTotal() - orderBean.getDiscountAmount() + orderBean.getSurcharge());
			pstmt.setString(4, orderBean.getOrderId());
			pstmt.executeUpdate();
			close(null, null, pstmt);
			
		} catch (SQLException excep) {
			throw new MealException("Exception occured while saving order details ", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		try {
			pstmt = getConnection().prepareStatement(MealQueryConstants.SAVE_ORDER_ITEMS);

			List<MenuItemBean> foodItems = orderBean.getFoodItems();
			if (null != foodItems && !foodItems.isEmpty()) {
				for (MenuItemBean item : foodItems) {
					int qty = item.getQty();
					int price = item.getPrice();
					int totalbfdis = qty * price;
					int totaldis = totalbfdis * orderBean.getOfferPercentage() / 100;
					int total = totalbfdis - totaldis;
					
					pstmt.setString(1, orderBean.getOrderId());
					pstmt.setString(2, item.getItemId());
					pstmt.setString(3, item.getItemName());
					pstmt.setInt(4, qty);
					pstmt.setString(5, MealConstants.EMPTY);
					pstmt.setDouble(6, 0.0);
					pstmt.setDouble(7, price);
					pstmt.setDouble(8, totaldis);
					pstmt.setDouble(9, total);
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException excep) {
			throw new MealException("Exception occured while saving order details ", excep);
		} finally {
			close(rs, stmt, pstmt);
		}
		
		// Save customer info
		insertOrUpdateCustDtls(orderBean.getCustomer());

		logger.info(MealConstants.EXIT + " saveOrder status is success invoice num is " + invoiceNum);
		return invoiceNum;
	}

	/**
	 * getReportOrderDetails.
	 * 
	 * @return
	 * @throws MealException
	 */
	public List<OrderBean> getReportOrderDetails(String date) throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		List<OrderBean> orderBeanList = new ArrayList<OrderBean>();

		try {
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_ORDERS);
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
				orderBean.setOrderId(rs.getString(MealQueryConstants.COL_ORDER_ID));
				orderBean.setLocationName(rs.getString(MealQueryConstants.COL_LOCATION_NAME));
				orderBean.setDeliveryDate(rs.getString(MealQueryConstants.COL_DELIVER_DATE));
				orderBean.setDeliveryTime(rs.getString(MealQueryConstants.COL_DELIVER_TIME));
				orderBean.setInvoiceNum(rs.getLong(MealQueryConstants.COL_INVOICE_NUM));
				
				CustomerBean custBean = new CustomerBean();
				custBean.setName(rs.getString(MealQueryConstants.COL_CUST_NAME));
				custBean.setPhone(rs.getString(MealQueryConstants.COL_CUST_MOBILE));
				custBean.setEmail(rs.getString(MealQueryConstants.COL_CUST_EMAIL));
				orderBean.setCustomer(custBean);
				
				orderBean.setDiscountAmount(rs.getDouble(MealQueryConstants.COL_DISCOUNT));
				orderBean.setSurcharge(rs.getDouble(MealQueryConstants.COL_SURCHARGES));
				orderBean.setTotal(rs.getDouble(MealQueryConstants.COL_TOTAL));
				
				orderBeanList.add(orderBean);
			}

			close(rs, stmt, pstmt);
			
			for(OrderBean orderBean : orderBeanList) {
				List<MenuItemBean> foodItems = new ArrayList<MenuItemBean>();
				
				pstmt = getConnection().prepareStatement(MealQueryConstants.GET_ORDER_ITEMS);
				pstmt.setString(1, orderBean.getOrderId());
				rs = pstmt.executeQuery();
						
				while (rs.next()) {
					MenuItemBean menuItemBean = new MenuItemBean();
					menuItemBean.setItemId(rs.getString(MealQueryConstants.COL_ITEM_ID));
					menuItemBean.setItemName(rs.getString(MealQueryConstants.COL_ITEM_NAME));
					menuItemBean.setQty(rs.getInt(MealQueryConstants.COL_QUANTITY));
					menuItemBean.setPrice(rs.getInt(MealQueryConstants.COL_PRICE));
					menuItemBean.setDiscount(rs.getInt(MealQueryConstants.COL_DISCOUNT));
					
					foodItems.add(menuItemBean);
				}
				
				orderBean.setFoodItems(foodItems);

				close(rs, null, pstmt);
			}
			
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing order details", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return orderBeanList;
	
		
	
	}

	
	/**
	 * getOfferCodeDetails.
	 * 
	 * @return
	 * @throws MealException
	 */
	public OfferBean getOfferCodeDetails(String offerCode) throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		OfferBean offerBean = null;

		try {
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_OFFER_DETAILS);
			pstmt.setString(1, offerCode);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				offerBean = new OfferBean();
				offerBean.setOfferPercentage(rs.getString(MealQueryConstants.COL_OFFER_PERCENTAGE));
				offerBean.setApplicableItems(rs.getString(MealQueryConstants.COL_OFFER_ON_ITEMS));
				offerBean.setMinAmt(rs.getInt(MealQueryConstants.COL_OFFER_MIN_AMT));
			}

		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing offer code details", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return offerBean;
	}

	/**
	 * getOrderDetails.
	 */
	public OrderBean getOrderDetails(String orderId) throws MealException {

		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		OrderBean orderBean = null;

		try {
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_ORDER_DETAILS);
			pstmt.setString(1, orderId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderBean = new OrderBean();
				orderBean.setOrderId(rs.getString(MealQueryConstants.COL_ORDER_ID));
				orderBean.setLocationName(rs.getString(MealQueryConstants.COL_LOCATION_NAME));
				orderBean.setDeliveryDate(rs.getString(MealQueryConstants.COL_DELIVER_DATE));
				orderBean.setDeliveryTime(rs.getString(MealQueryConstants.COL_DELIVER_TIME));
				orderBean.setInvoiceNum(rs.getLong(MealQueryConstants.COL_INVOICE_NUM));
				
				CustomerBean custBean = new CustomerBean();
				custBean.setName(rs.getString(MealQueryConstants.COL_CUST_NAME));
				custBean.setPhone(rs.getString(MealQueryConstants.COL_CUST_MOBILE));
				custBean.setEmail(rs.getString(MealQueryConstants.COL_CUST_EMAIL));
				orderBean.setCustomer(custBean);
				
				orderBean.setDiscountAmount(rs.getDouble(MealQueryConstants.COL_DISCOUNT));
				orderBean.setSurcharge(rs.getDouble(MealQueryConstants.COL_SURCHARGES));
				orderBean.setTotal(rs.getDouble(MealQueryConstants.COL_TOTAL));
			}

			close(rs, stmt, pstmt);
			
			List<MenuItemBean> foodItems = new ArrayList<MenuItemBean>();
					
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_ORDER_ITEMS);
			pstmt.setString(1, orderId);
			rs = pstmt.executeQuery();
					
			while (rs.next()) {
				MenuItemBean menuItemBean = new MenuItemBean();
				menuItemBean.setItemId(rs.getString(MealQueryConstants.COL_ITEM_ID));
				menuItemBean.setItemName(rs.getString(MealQueryConstants.COL_ITEM_NAME));
				menuItemBean.setQty(rs.getInt(MealQueryConstants.COL_QUANTITY));
				menuItemBean.setPrice(rs.getInt(MealQueryConstants.COL_PRICE));
				menuItemBean.setDiscount(rs.getInt(MealQueryConstants.COL_DISCOUNT));
				
				foodItems.add(menuItemBean);
			}
			
			orderBean.setFoodItems(foodItems);
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing order details", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return orderBean;
	
		
	}

	@Override
	public String login(CustomerBean customerBean) throws MealException {

		logger.info(MealConstants.ENTER);

		String status = MealConstants.FAIL;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// Get Customer Details
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_CUSTOMER_DETAILS);
			pstmt.setString(1, customerBean.getPhone());
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				// Insert customer details
				
			} else {
				String dbPassword = rs.getString(MealQueryConstants.COL_PASSWORD);
				if(customerBean.getPassword().equalsIgnoreCase(dbPassword)  ) {
					status = MealConstants.SUCCESS; 
				}
			}

		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing login details.", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return status;
	
	}

	public CustomerBean getCustomerDetails(String phone) throws MealException {
		logger.info(MealConstants.ENTER);

		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		CustomerBean custBean = null;
		try {
			// Get Customer Details
			pstmt = getConnection().prepareStatement(MealQueryConstants.GET_CUSTOMER_DETAILS);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				custBean = new CustomerBean();
				custBean.setAddLine1(rs.getString(MealQueryConstants.COL_ADDRESS_LINE1));
				custBean.setArea(rs.getString(MealQueryConstants.COL_AREA)); 
				//custBean.setCustId(rs.getString(MealQueryConstants.COL_CUST_C)); 
				custBean.setEmail(rs.getString(MealQueryConstants.COL_CUST_EMAIL)); 
				custBean.setLocationId(rs.getString(MealQueryConstants.COL_LOCATION_ID)); 
				custBean.setName(rs.getString(MealQueryConstants.COL_CUST_NAME)); 
				custBean.setPhone(rs.getString(MealQueryConstants.COL_CUST_MOBILE));
				custBean.setPassword(rs.getString(MealQueryConstants.COL_PASSWORD));
				
			} 
		} catch (SQLException excep) {
			throw new MealException("Exception occured while retrieveing menu categories", excep);
		} finally {
			close(rs, stmt, pstmt);
		}

		logger.info(MealConstants.EXIT);
		return custBean;
	}
	/**
	 * close.
	 * 
	 * @param rs
	 * @param st
	 * @param ps
	 */
	private void close(ResultSet rs, Statement st, PreparedStatement ps) {
		try {

			if (null != rs) {
				rs.close();
			}

			if (null != st) {
				st.close();
			}

			if (null != ps) {
				ps.close();
			}
		} catch (Exception exce) {
			logger.warn("Exception while closing connections ", exce);
		}
	}

}
