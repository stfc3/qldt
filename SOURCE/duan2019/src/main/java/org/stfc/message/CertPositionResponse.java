/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.entity.CertificatePositionEntity;

/**
 * @author viett
 *
 */
public class CertPositionResponse {
	private Long positionId;
	private String positionName;
	private List<CertificatePositionEntity> listCertification;

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
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the listCertification
	 */
	public List<CertificatePositionEntity> getListCertification() {
		return listCertification;
	}

	/**
	 * @param listCertification the listCertification to set
	 */
	public void setListCertification(List<CertificatePositionEntity> listCertification) {
		this.listCertification = listCertification;
	}

}
