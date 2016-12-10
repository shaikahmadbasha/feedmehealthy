/**
 * 
 */
package com.mymeal.beans;

import java.util.List;

/**
 * @author Vishal
 *
 */
public class OrderBean extends BaseBean {

	private static final long serialVersionUID = -8934644882992399386L;

	private String orderId;
	private String paymentMode;
	private CustomerBean customer;
	private double surcharge;
	private double discountAmount;
	private String deliveryTime;
	private String locationId;
	private String locationName;
	private double total;
	private String deliveryDate;
	private int offerPercentage;
	private long invoiceNum;
	
	private List<MenuItemBean> foodItems;

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the customer
	 */
	public CustomerBean getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	/**
	 * @return the discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @return the surcharge
	 */
	public double getSurcharge() {
		return surcharge;
	}

	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}

	/**
	 * @param discountAmount
	 *            the discountAmount to set
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 *            the deliveryTime to set
	 */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName
	 *            the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the foodItems
	 */
	public List<MenuItemBean> getFoodItems() {
		return foodItems;
	}

	/**
	 * @param foodItems
	 *            the foodItems to set
	 */
	public void setFoodItems(List<MenuItemBean> foodItems) {
		this.foodItems = foodItems;
	}

	/**
	 * @return the offerPercentage
	 */
	public int getOfferPercentage() {
		return offerPercentage;
	}

	/**
	 * @param offerPercentage the offerPercentage to set
	 */
	public void setOfferPercentage(int offerPercentage) {
		this.offerPercentage = offerPercentage;
	}

	/**
	 * @return the invoiceNum
	 */
	public long getInvoiceNum() {
		return invoiceNum;
	}

	/**
	 * @param invoiceNum the invoiceNum to set
	 */
	public void setInvoiceNum(long invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	
	

}
