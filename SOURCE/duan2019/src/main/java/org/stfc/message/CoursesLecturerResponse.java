/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.dto.Courses;
import org.stfc.dto.Lecturers;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class CoursesLecturerResponse {
	@SerializedName("lecturer")
	private Lecturers lecturer;
	@SerializedName("coursers")
	private List<Courses> coursers;

	/**
	 * @return the lecturer
	 */
	public Lecturers getLecturer() {
		return lecturer;
	}

	/**
	 * @param lecturer the lecturer to set
	 */
	public void setLecturer(Lecturers lecturer) {
		this.lecturer = lecturer;
	}

	/**
	 * @return the coursers
	 */
	public List<Courses> getCoursers() {
		return coursers;
	}

	/**
	 * @param coursers the coursers to set
	 */
	public void setCoursers(List<Courses> coursers) {
		this.coursers = coursers;
	}

}
