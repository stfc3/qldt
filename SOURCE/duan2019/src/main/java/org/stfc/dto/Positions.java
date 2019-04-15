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
 * @author viettx
 *
 */
@Entity
@Table(name = "Positions")
public class Positions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "position_id", unique = false, nullable = false, length = 20)
	private Long id;
	@Column(name = "position_name", length = 200)
	private String posiName;
	@Column(name = "status", length = 1)
	private int status;
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "updated_date")
	private Date modifiedDate;

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
	 * @return the posiName
	 */
	public String getPosiName() {
		return posiName;
	}

	/**
	 * @param posiName the posiName to set
	 */
	public void setPosiName(String posiName) {
		this.posiName = posiName;
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
