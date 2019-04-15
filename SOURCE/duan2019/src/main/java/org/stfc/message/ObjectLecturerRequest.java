/**
 * 
 */
package org.stfc.message;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class ObjectLecturerRequest {
	@SerializedName("lecturer_id")
	private Long lecturerId;

	/**
	 * @return the lecturerId
	 */
	public Long getLecturerId() {
		return lecturerId;
	}

	/**
	 * @param lecturerId the lecturerId to set
	 */
	public void setLecturerId(Long lecturerId) {
		this.lecturerId = lecturerId;
	}

}
