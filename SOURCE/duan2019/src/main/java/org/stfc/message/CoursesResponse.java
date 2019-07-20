/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.dto.Courses;

import com.google.gson.annotations.SerializedName;

/**
 * @author viettx
 *
 */
public class CoursesResponse {
	@SerializedName("courses")
	private List<Courses> courses;

	/**
	 * @return the courses
	 */
	public List<Courses> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
}
