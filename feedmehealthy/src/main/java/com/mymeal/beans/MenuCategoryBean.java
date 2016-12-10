/**
 * 
 */
package com.mymeal.beans;


/**
 * @author Vishal
 *
 */
public class MenuCategoryBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8626192652173468220L;

	private String categoryId;
	private String categoryName;

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
