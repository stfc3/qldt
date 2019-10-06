/**
 * 
 */
package org.stfc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author viettx
 *
 */
@Entity
@Table(name = "staff")
public class Teacher {
	/**
	 * staff_id BIGINT AUTO_INCREMENT PRIMARY KEY, staff_code VARCHAR(10) NULL,
	 * staff_name VARCHAR(100) NULL, phone VARCHAR(15) NULL, email VARCHAR(50) NULL,
	 * address VARCHAR(200) NULL, birthday DATE NULL, department INT NULL, position
	 * INT NULL, status INT DEFAULT '1' NULL, create_date TIMESTAMP DEFAULT
	 * CURRENT_TIMESTAMP NOT NULL, level DECIMAL NULL, type DECIMAL NULL, organ
	 * DECIMAL NULL, type_tenure VARCHAR(10) NULL
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", nullable = false, unique = false)
	private Long id;
	@Column(name = "staff_code")
	private String code;
	@Column(name = "staff_name")
	private String name;
	@Column(name = "type_tenure", length = 10)
	private String typeTenure;
	@Column(name = "organ")
	private Long department;
	@Column(name = "status")
	private int status;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the typeTenure
	 */
	public String getTypeTenure() {
		return typeTenure;
	}

	/**
	 * @param typeTenure the typeTenure to set
	 */
	public void setTypeTenure(String typeTenure) {
		this.typeTenure = typeTenure;
	}

	/**
	 * @return the department
	 */
	public Long getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Long department) {
		this.department = department;
	}

}
