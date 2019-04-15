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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author viett
 *
 */
@Data
@Entity
@Table(name = "Lecturers")
public class Lecturers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lecturer_id", nullable = false, length = 20, unique = false)
	private Long id;
	@Column(name = "first_name", length = 100)
	private String fistName;
	@Column(name = "last_name", length = 100)
	private String lastName;
	@Column(name = "full_name", length = 200)
	private String fullName;
	@Column(name = "gender", length = 10)
	private String gender;
	@Column(name = "mobile", length = 15)
	private String mobile;
	@Column(name = "email", length = 100)
	private String email;
	@Column(name = "department_id", length = 20)
	private Long deptId;
	@Column(name = "position_id", length = 20)
	private Long posiId;
	@Column(name = "status", length = 1)
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date modifiedDate;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "lecturers")
//	private List<Experiences> experiences;

	/**
	 * @return the experiences
	 */
//	public List<Experiences> getExperiences() {
//		return experiences;
//	}
//
//	/**
//	 * @param experiences the experiences to set
//	 */
//	public void setExperiences(List<Experiences> experiences) {
//		this.experiences = experiences;
//	}

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
	 * @return the fistName
	 */
	public String getFistName() {
		return fistName;
	}

	/**
	 * @param fistName the fistName to set
	 */
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the deptId
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the posiId
	 */
	public Long getPosiId() {
		return posiId;
	}

	/**
	 * @param posiId the posiId to set
	 */
	public void setPosiId(Long posiId) {
		this.posiId = posiId;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
