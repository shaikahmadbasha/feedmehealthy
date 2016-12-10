/**
 * 
 */
package com.mymeal.beans;

import java.util.List;
import java.util.Map;

/**
 * @author Vishal
 *
 */
public class MenuItemBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2807850654736945627L;

	private String itemId;
	private String itemName;
	private String description1;
	private String description2;
	private int price;
	private String calorie;
	private String servingQty;
	private String categoryNames;
	private List<String> availableTimes;
	private String tag;
	private String imageName;
	private boolean available;
	private Map<String, String> availableDates;
	private int qty;
	private int discount;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the description1
	 */
	public String getDescription1() {
		return description1;
	}

	/**
	 * @param description1
	 *            the description1 to set
	 */
	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	/**
	 * @return the description2
	 */
	public String getDescription2() {
		return description2;
	}

	/**
	 * @param description2
	 *            the description2 to set
	 */
	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the calorie
	 */
	public String getCalorie() {
		return calorie;
	}

	/**
	 * @param calorie
	 *            the calorie to set
	 */
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	/**
	 * @return the servingQty
	 */
	public String getServingQty() {
		return servingQty;
	}

	/**
	 * @param servingQty
	 *            the servingQty to set
	 */
	public void setServingQty(String servingQty) {
		this.servingQty = servingQty;
	}

	/**
	 * @return the categoryNames
	 */
	public String getCategoryNames() {
		return categoryNames;
	}

	/**
	 * @param categoryNames
	 *            the categoryNames to set
	 */
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	/**
	 * @return the availableTimes
	 */
	public List<String> getAvailableTimes() {
		return availableTimes;
	}

	/**
	 * @param availableTimes
	 *            the availableTimes to set
	 */
	public void setAvailableTimes(List<String> availableTimes) {
		this.availableTimes = availableTimes;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available
	 *            the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the availableDates
	 */
	public Map<String, String> getAvailableDates() {
		return availableDates;
	}

	/**
	 * @param availableDates
	 *            the availableDates to set
	 */
	public void setAvailableDates(Map<String, String> availableDates) {
		this.availableDates = availableDates;
	}

	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
