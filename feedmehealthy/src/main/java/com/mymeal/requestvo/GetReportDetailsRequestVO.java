/**
 * 
 */
package com.mymeal.requestvo;

/**
 * @author Vishal
 *
 */
public class GetReportDetailsRequestVO extends BaseRequestVO {
	
	private static final long serialVersionUID = -6653335859598472028L;
	private String date;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
}
