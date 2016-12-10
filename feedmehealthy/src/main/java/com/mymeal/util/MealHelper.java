/**
 * 
 */
package com.mymeal.util;

import java.util.List;
import java.util.Random;

import com.mymeal.beans.MenuItemBean;
import com.mymeal.beans.OfferBean;

/**
 * @author Vishal
 *
 */
public class MealHelper {

	public static int randInt(int min, int max) {
		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.jj
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static boolean isEmpty(String input) {
		boolean isEmpty = false;
		if (null == input || "".equals(input)) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static String trailingChars(String str, int len) {
		if (isEmpty(str)) {
			return "";
		}

		if (str.length() <= len) {
			return str;
		}

		return str.substring(len, str.length());
	}

	/**
	 * isOfferApplicable.
	 * 
	 * @param offerBean
	 * @return
	 */
	public static String offerApplicable(OfferBean offerBean, List<MenuItemBean> foodItems, int amount) {

		if (null == offerBean) {
			return MealConstants.OFFER_DOES_NOT_EXISTS;
		}

		if (amount < offerBean.getMinAmt()) {
			return MealConstants.OFFER_NO_MIN_AMT;
		}

		String offerAppliItems = (null != offerBean.getApplicableItems()) ? offerBean.getApplicableItems().toUpperCase() : "";

		if (offerAppliItems.contains("ALL")) {
			return MealConstants.OFFER_VALID;
		}

		if (null != foodItems && !foodItems.isEmpty()) {
			boolean found = false;
			for (MenuItemBean itmBen : foodItems) {
				if (offerAppliItems.contains(itmBen.getItemName().toUpperCase())) {
					found = true;
					break;
				}
				if (found) {
					return MealConstants.OFFER_VALID;
				} else {
					return MealConstants.OFFER_NO_ITMS_MATCH;
				}
			}
		}

		return MealConstants.OFFER_INVALID;
	}

	public static int parseInt(String str) {
		if (isEmpty(str)) {
			return 0;
		}

		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return 0;
		}

	}
	
	public static String removeOthers(String str) {
		if(MealHelper.isEmpty(str)) {
			return "";
		}
		
		return str.replaceFirst("Others - ", "");
	}
}
