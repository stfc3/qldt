/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.dto.Experiences;
import org.stfc.dto.Lecturers;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class ExpriencesLecturerResponse {
	@SerializedName("lecturer")
	private Lecturers lecturer;
	@SerializedName("experiences")
	private List<Experiences> experiences;

	/**
	 * @return the experiences
	 */
	public List<Experiences> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(List<Experiences> experiences) {
		this.experiences = experiences;
	}

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

}
