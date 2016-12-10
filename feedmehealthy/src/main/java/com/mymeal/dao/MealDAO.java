/**
 * 
 */
package com.mymeal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mymeal.beans.CustomerBean;
import com.mymeal.beans.DeliveryLocationBean;
import com.mymeal.beans.MenuCategoryBean;
import com.mymeal.beans.MenuItemBean;
import com.mymeal.beans.OfferBean;
import com.mymeal.beans.OrderBean;
import com.mymeal.exception.MealException;

/**
 * @author basha
 *
 */
@Component("myMealDAO")
public interface MealDAO {

	public List<DeliveryLocationBean> getDeliveryLocations() throws MealException;

	public List<MenuCategoryBean> getMenuCategories() throws MealException;

	public List<MenuItemBean> getMenuItems() throws MealException;

	public int insertOrUpdateCustDtls(CustomerBean customerBean) throws MealException;
	
	public long saveOrder(OrderBean orderBean) throws MealException;
	
	public List<OrderBean> getReportOrderDetails(String date) throws MealException;
	
	public OfferBean getOfferCodeDetails(String offerCode) throws MealException;
	
	public OrderBean getOrderDetails(String orderId) throws MealException;
	
	public String login(CustomerBean customerBean) throws MealException;
	
	public CustomerBean getCustomerDetails(String phone) throws MealException;
	
}
