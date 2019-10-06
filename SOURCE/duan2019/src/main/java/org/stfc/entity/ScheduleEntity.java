/**
 * 
 */
package org.stfc.entity;

/**
 * @author viettx
 *
 */
public class ScheduleEntity {
	private String teacherCode;
	private String teacherName;
	private String className;

	/**
	 * @return the teacherCode
	 */
	public String getTeacherCode() {
		return teacherCode;
	}

	/**
	 * @param teacherCode the teacherCode to set
	 */
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}

	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	public ScheduleEntity(String teacherCode, String teacherName, String className) {
		super();
		this.teacherCode = teacherCode;
		this.teacherName = teacherName;
		this.className = className;
	}

}
