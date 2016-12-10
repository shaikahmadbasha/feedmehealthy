/**
 * 
 */
package com.mymeal.beans;

/**
 * @author Vishal
 *
 */
public class OfferBean extends BaseBean {

	private static final long serialVersionUID = -992575203847881087L;

	private String offerStatus;
	private String offerPercentage;
	private String applicableItems;
	private int minAmt;
	
	/**
	 * @return the offerStatus
	 */
	public String getOfferStatus() {
		return offerStatus;
	}

	/**
	 * @param offerStatus
	 *            the offerStatus to set
	 */
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	/**
	 * @return the offerPercentage
	 */
	public String getOfferPercentage() {
		return offerPercentage;
	}

	/**
	 * @param offerPercentage
	 *            the offerPercentage to set
	 */
	public void setOfferPercentage(String offerPercentage) {
		this.offerPercentage = offerPercentage;
	}

	/**
	 * @return the applicableItems
	 */
	public String getApplicableItems() {
		return applicableItems;
	}

	/**
	 * @param applicableItems the applicableItems to set
	 */
	public void setApplicableItems(String applicableItems) {
		this.applicableItems = applicableItems;
	}

	/**
	 * @return the minAmt
	 */
	public int getMinAmt() {
		return minAmt;
	}

	/**
	 * @param minAmt the minAmt to set
	 */
	public void setMinAmt(int minAmt) {
		this.minAmt = minAmt;
	}

}
