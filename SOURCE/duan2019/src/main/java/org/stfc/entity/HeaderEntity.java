/**
 * 
 */
package org.stfc.entity;

/**
 * @author viettx
 *
 */
public class HeaderEntity {
	/**
	 * Tong so khoa hoc dang dien ra
	 */
	private Long totalCourses;
	/**
	 * Tong so hoc vien
	 */
	private Long totalOfficer;
	/**
	 * Tong so khao sat
	 */
	private Long totalSurvey;
	/**
	 * Tong so khoa hoc sap mo
	 */
	private Long totalCoursesPlan;
	
	
	public HeaderEntity(Long totalCourses, Long totalOfficer, Long totalSurvey, Long totalCoursesPlan) {
		super();
		this.totalCourses = totalCourses;
		this.totalOfficer = totalOfficer;
		this.totalSurvey = totalSurvey;
		this.totalCoursesPlan = totalCoursesPlan;
	}
	/**
	 * @return the totalCourses
	 */
	public Long getTotalCourses() {
		return totalCourses;
	}
	/**
	 * @param totalCourses the totalCourses to set
	 */
	public void setTotalCourses(Long totalCourses) {
		this.totalCourses = totalCourses;
	}
	/**
	 * @return the totalOfficer
	 */
	public Long getTotalOfficer() {
		return totalOfficer;
	}
	/**
	 * @param totalOfficer the totalOfficer to set
	 */
	public void setTotalOfficer(Long totalOfficer) {
		this.totalOfficer = totalOfficer;
	}
	/**
	 * @return the totalSurvey
	 */
	public Long getTotalSurvey() {
		return totalSurvey;
	}
	/**
	 * @param totalSurvey the totalSurvey to set
	 */
	public void setTotalSurvey(Long totalSurvey) {
		this.totalSurvey = totalSurvey;
	}
	/**
	 * @return the totalCoursesPlan
	 */
	public Long getTotalCoursesPlan() {
		return totalCoursesPlan;
	}
	/**
	 * @param totalCoursesPlan the totalCoursesPlan to set
	 */
	public void setTotalCoursesPlan(Long totalCoursesPlan) {
		this.totalCoursesPlan = totalCoursesPlan;
	}
	
	
	
}
