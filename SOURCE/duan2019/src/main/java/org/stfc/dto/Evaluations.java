/**
 * 
 */
package org.stfc.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author viett
 *
 */
@Entity
@Table(name = "Evaluations")
public class Evaluations {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evaluation_id", nullable = false, length = 20, unique = false)
	private Long evaluationId;
	@Column(name = "course_id", nullable = false, length = 20, unique = false)
	private Long courseId;
	@Column(name = "course_point")
	private Integer coursePoint;
	@Column(name = "lecturer_point")
	private Integer leturerPoint;
	@Column(name = "lecturer_comment")
	private String lecturerComment;
	@Column(name = "created_date")
	private Date createDate;
	@Column(name = "updated_date")
	private Date modifiedDate;
	
	/**
	 * @return the leturerPoint
	 */
	public Integer getLeturerPoint() {
		return leturerPoint;
	}
	/**
	 * @param leturerPoint the leturerPoint to set
	 */
	public void setLeturerPoint(Integer leturerPoint) {
		this.leturerPoint = leturerPoint;
	}
	/**
	 * @return the evaluationId
	 */
	public Long getEvaluationId() {
		return evaluationId;
	}
	/**
	 * @param evaluationId the evaluationId to set
	 */
	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
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
	 * @return the coursePoint
	 */
	public Integer getCoursePoint() {
		return coursePoint;
	}
	/**
	 * @param coursePoint the coursePoint to set
	 */
	public void setCoursePoint(Integer coursePoint) {
		this.coursePoint = coursePoint;
	}
	/**
	 * @return the lecturerComment
	 */
	public String getLecturerComment() {
		return lecturerComment;
	}
	/**
	 * @param lecturerComment the lecturerComment to set
	 */
	public void setLecturerComment(String lecturerComment) {
		this.lecturerComment = lecturerComment;
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
