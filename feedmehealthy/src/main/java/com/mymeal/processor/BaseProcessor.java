/**
 * 
 */
package com.mymeal.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author basha
 *
 */
public abstract class BaseProcessor {

	public abstract Object processRequest(HttpServletRequest request, HttpServletResponse response);

	public abstract Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonReq);
	

}
