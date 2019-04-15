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
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author viett
 *
 */
@Data
@Entity
@Table(name = "position_certificate")
public class PositionCertificate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "position_certificate_id", nullable = false, length = 20)
	private Long id;
	@Column(name = "position_id", length = 20)
	@NotBlank(message = "Name is mandatory")
	private Long positionId;
	@Column(name = "certificate_id", length = 20)
	@NotBlank(message = "Name is mandatory")
	private Long certificateId;
	@Column(name = "status", length = 1)
	private int status;
	@Column(name = "created_date")
	private Date createdDate;
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
	 * @return the positionId
	 */
	public Long getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return the certificateId
	 */
	public Long getCertificateId() {
		return certificateId;
	}

	/**
	 * @param certificateId the certificateId to set
	 */
	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
