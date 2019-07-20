/**
 * 
 */
package org.stfc.message;

import com.google.gson.annotations.SerializedName;

/**
 * @author viettx
 *
 */
public class CoursesRequest {

	@SerializedName("courseName")
	private String courseName;
	@SerializedName("fromDate")
	private String startDate;
	@SerializedName("toDate")
	private String endDate;

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
