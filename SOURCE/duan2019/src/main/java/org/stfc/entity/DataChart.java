/**
 * 
 */
package org.stfc.entity;

import java.util.List;

/**
 * @author viettx
 *
 */
public class DataChart {
	private String category;
	private List<Long> data;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the data
	 */
	public List<Long> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Long> data) {
		this.data = data;
	}

}
