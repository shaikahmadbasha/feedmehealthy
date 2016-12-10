/**
 * 
 */
package com.mymeal.requestvo;

import com.mymeal.beans.CustomerBean;

/**
 * @author Vishal
 *
 */
public class UpdateCustomerDetailsRequestVO extends BaseRequestVO {

	private static final long serialVersionUID = -2772566839078519626L;

	private CustomerBean customer;

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

}
