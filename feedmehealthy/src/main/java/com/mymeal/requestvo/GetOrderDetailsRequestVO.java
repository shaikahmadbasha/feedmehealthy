/**
 * 
 */
package com.mymeal.requestvo;

/**
 * @author Vishal
 *
 */
public class GetOrderDetailsRequestVO extends BaseRequestVO {

	private static final long serialVersionUID = -5196626958268978634L;

	private String mobilePhone;

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
