/**
 * 
 */
package com.mymeal.requestvo;


/**
 * @author Vishal
 *
 */
public class GenerateInvoiceRequestVO extends BaseRequestVO {

	private static final long serialVersionUID = -2659379238678902614L;

	private String orderId;

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

}
