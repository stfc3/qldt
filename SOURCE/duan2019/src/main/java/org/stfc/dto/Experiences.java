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
@Entity(name = "Experiences")
@Table(name = "Experiences")
public class Experiences {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "experience_id", nullable = false, length = 20, unique = false)
	private Long experienceId;
	@Column(name = "teach_plan")
	private Double teachPlan;
	@Column(name = "teach_effect")
	private Double teachEffect;
	@Column(name = "teach_year")
	private int teachYear;
	@Column(name = "created_date")
	private Date createDate;
	@Column(name = "updated_date")
	private Date modifiedDate;
	@Column(name = "lecturer_id")
	private Long lecturerId;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "lecturer_id", nullable = false)
//	private Lecturers lecturers;

	/**
	 * @return the experienceId
	 */
	public Long getExperienceId() {
		return experienceId;
	}

	/**
	 * @param experienceId the experienceId to set
	 */
	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}

	/**
	 * @return the teachPlan
	 */
	public Double getTeachPlan() {
		return teachPlan;
	}

	/**
	 * @param teachPlan the teachPlan to set
	 */
	public void setTeachPlan(Double teachPlan) {
		this.teachPlan = teachPlan;
	}

	/**
	 * @return the teachEffect
	 */
	public Double getTeachEffect() {
		return teachEffect;
	}

	/**
	 * @param teachEffect the teachEffect to set
	 */
	public void setTeachEffect(Double teachEffect) {
		this.teachEffect = teachEffect;
	}

	/**
	 * @return the teachYear
	 */
	public int getTeachYear() {
		return teachYear;
	}

	/**
	 * @param teachYear the teachYear to set
	 */
	public void setTeachYear(int teachYear) {
		this.teachYear = teachYear;
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

//	/**
//	 * @return the lecturers
//	 */
//	public Lecturers getLecturers() {
//		return lecturers;
//	}
//
//	/**
//	 * @param lecturers the lecturers to set
//	 */
//	public void setLecturers(Lecturers lecturers) {
//		this.lecturers = lecturers;
//	}

}
