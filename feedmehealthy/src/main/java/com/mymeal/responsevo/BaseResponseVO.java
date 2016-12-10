/**
 * 
 */
package com.mymeal.responsevo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author basha
 *
 */
public class BaseResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127556012110255033L;

	private String statusCode;
	private String statusMessage;
	private Map<String, Object> body;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

}
