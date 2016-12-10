/**
 * 
 */
package com.mymeal.beans;

import java.util.List;

/**
 * @author Vishal
 *
 */
public class LocDateTimesBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7328722615457344941L;
	private String locationDate;
	private List<String> locationTimes;

	/**
	 * @return the locationDate
	 */
	public String getLocationDate() {
		return locationDate;
	}

	/**
	 * @param locationDate
	 *            the locationDate to set
	 */
	public void setLocationDate(String locationDate) {
		this.locationDate = locationDate;
	}

	/**
	 * @return the locationTimes
	 */
	public List<String> getLocationTimes() {
		return locationTimes;
	}

	/**
	 * @param locationTimes
	 *            the locationTimes to set
	 */
	public void setLocationTimes(List<String> locationTimes) {
		this.locationTimes = locationTimes;
	}

}
