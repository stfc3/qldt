/**
 * 
 */
package org.stfc.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author viett
 *
 */
@Entity
@Table(name = "position_certificate")
public class CertificatePosition {
	@Id
	@GeneratedValue
	@Column(name = "position_certificate_id", nullable = false, length = 20, unique = true)
	private Long id;
	@Column(name = "position_id", nullable = false, length = 20)
	private Long position;
	@Column(name = "certificate_id", nullable = false, length = 20)
	private Long certificate;
	@Column(name = "status", nullable = false, length = 1)
	private int status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
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

	/**
	 * @return the position
	 */
	public Long getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Long position) {
		this.position = position;
	}

	/**
	 * @return the certificate
	 */
	public Long getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate the certificate to set
	 */
	public void setCertificate(Long certificate) {
		this.certificate = certificate;
	}

}
