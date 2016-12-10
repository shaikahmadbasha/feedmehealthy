/**
 * 
 */
package com.mymeal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author basha
 *
 */
public class MealDAOUtil {
	
	/**
	 * getAsList.
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static List<String> getAsList(String str, String delimiter) {
		
		List<String> asList = new ArrayList<String>();
		
		if(!isEmpty(str)) {
			StringTokenizer st = new StringTokenizer(str, delimiter);
			
			while(st.hasMoreTokens()) {
				asList.add(st.nextToken());
			}
		}
		
		return asList;
		
	}
	
	/**
	 * isEmpty.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input) {
		boolean isEmpty = false;
		if (null == input || "".equals(input)) {
			isEmpty = true;
		}
		return isEmpty;
	}

}
