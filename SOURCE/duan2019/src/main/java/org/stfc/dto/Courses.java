/**
 * 
 */
package org.stfc.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author viettx
 *
 */
@Data
@Entity
@Table(name = "Courses")
@AllArgsConstructor
public class Courses implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7033387389326216782L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", nullable = false, length = 20, unique = false)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("course_id")
//	@NotBlank(message = "Name is mandatory")
	private Long courseId;

	@Column(name = "course_name", nullable = false, length = 600)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("course_name")
	@NotBlank(message = "Name is not null")
	private String courseName;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = true)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("end_date")
	private Date endDate;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Expose(serialize = false, deserialize = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	@Expose(serialize = false, deserialize = false)
	private Date modifiedDate;

	@Column(name = "lecturer_id", nullable = false, length = 20)
	@Expose(serialize = false, deserialize = false)
	@SerializedName("lecturer_id")
//	@NotBlank(message = "Lecturer is not null")
	private Long lecturerId;

	/**
	 * @param courseId
	 * @param courseName
	 * @param startDate
	 * @param endDate
	 * @param lecturerId
	 */
	public Courses(Long courseId, String courseName, Date startDate, Date endDate, Long lecturerId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lecturerId = lecturerId;
	}

	/**
	 * 
	 */
	public Courses() {
		super();
	}

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

	/**
	 * @return the courseId
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

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
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
