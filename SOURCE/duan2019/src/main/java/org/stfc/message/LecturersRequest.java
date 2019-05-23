/**
 * 
 */
package org.stfc.message;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class LecturersRequest {
	@SerializedName("query")
	private String query;
	@SerializedName("full_name")
	private String fullName;
	@SerializedName("gender")
	private String gender;
	@SerializedName("phone_number")
	private String phone;
	@SerializedName("email")
	private String email;
	@SerializedName("department_id")
	private Long depId;
	@SerializedName("position_id")
	private Long posId;
	@SerializedName("status")
	private Integer stauts;

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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the depId
	 */
	public Long getDepId() {
		return depId;
	}

	/**
	 * @param depId the depId to set
	 */
	public void setDepId(Long depId) {
		this.depId = depId;
	}

	/**
	 * @return the posId
	 */
	public Long getPosId() {
		return posId;
	}

	/**
	 * @param posId the posId to set
	 */
	public void setPosId(Long posId) {
		this.posId = posId;
	}

	/**
	 * @return the stauts
	 */
	public Integer getStauts() {
		return stauts;
	}

	/**
	 * @param stauts the stauts to set
	 */
	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

//	@SerializedName("full_name")
//	private String fullName;
//	@SerializedName("gender")
//	private String gender;
//	@SerializedName("phone_number")
//	private String phone;
//	@SerializedName("email")
//	private String email;
//	@SerializedName("department_id")
//	private Long depId;
//	@SerializedName("position_id")
//	private Long posId;
//	@SerializedName("status")
//	private Integer stauts;
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Long getDepId() {
//		return depId;
//	}
//
//	public void setDepId(Long depId) {
//		this.depId = depId;
//	}
//
//	public Long getPosId() {
//		return posId;
//	}
//
//	public void setPosId(Long posId) {
//		this.posId = posId;
//	}
//
//	public Integer getStauts() {
//		return stauts;
//	}
//
//	public void setStauts(Integer stauts) {
//		this.stauts = stauts;
//	}

}
