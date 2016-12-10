/**
 * 
 */
package com.mymeal.beans;

import java.util.List;

/**
 * @author Vishal
 *
 */
public class DeliveryLocationBean extends BaseBean {

	private static final long serialVersionUID = -7499831566290086665L;

	private String locationId;
	private String locationName;
	private String locationType;
	private List<LocDateTimesBean> locationTimes;
	private List<String> locationDates;

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
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the locationTimes
	 */
	public List<LocDateTimesBean> getLocationTimes() {
		return locationTimes;
	}

	/**
	 * @param locationTimes
	 *            the locationTimes to set
	 */
	public void setLocationTimes(List<LocDateTimesBean> locationTimes) {
		this.locationTimes = locationTimes;
	}

	/**
	 * @return the locationDates
	 */
	public List<String> getLocationDates() {
		return locationDates;
	}

	/**
	 * @param locationDates
	 *            the locationDates to set
	 */
	public void setLocationDates(List<String> locationDates) {
		this.locationDates = locationDates;
	}

}
