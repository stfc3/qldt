/**
 * 
 */
package org.stfc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author viettx
 *
 */
@Entity
@Table(name = "sch_schedule")
public class Schedule {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 20, unique = true)
	private Long id;
	@Column(name = "staff_id")
	private String staffId;
	@Column(name = "class_code")
	private String classCode;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}

	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

}
