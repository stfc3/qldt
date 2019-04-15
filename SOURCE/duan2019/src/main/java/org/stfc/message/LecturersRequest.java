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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public Integer getStauts() {
		return stauts;
	}

	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}

}
