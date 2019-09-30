/**
 * 
 */
package org.stfc.entity;

/**
 * @author viettx
 *
 */
public class TopLecturer {
	private Long evaluation;
	private Long course;
	private String courseName;
	private Integer point;
	private String comment;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private Long department;



	public TopLecturer(Long evaluation, Long course, String courseName, Integer point, String comment, String firstName,
			String lastName, String fullName, String phone, Long department) {
		super();
		this.evaluation = evaluation;
		this.course = course;
		this.courseName = courseName;
		this.point = point;
		this.comment = comment;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.phone = phone;
		this.department = department;
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

	public Long getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Long evaluation) {
		this.evaluation = evaluation;
	}

	public Long getCourse() {
		return course;
	}

	public void setCourse(Long course) {
		this.course = course;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

}
