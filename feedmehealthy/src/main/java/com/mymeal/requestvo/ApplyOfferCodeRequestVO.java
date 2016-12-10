/**
 * 
 */
package com.mymeal.requestvo;

import java.util.List;

import com.mymeal.beans.MenuItemBean;

/**
 * @author Vishal
 *
 */
public class ApplyOfferCodeRequestVO extends BaseRequestVO {
	private static final long serialVersionUID = -890695939051429913L;

	private List<MenuItemBean> foodItems;
	private String offerCode;
	private int amount;

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
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}

	/**
	 * @param offerCode
	 *            the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
