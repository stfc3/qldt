/**
 *
 */
package org.stfc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.stfc.utils.Constants;

/**
 * @author viettx
 *
 */
@Entity
@Table(name = "officer_certificate")
public class CertificateOfficers implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "officer_certificate_id", nullable = false, length = 20, unique = true)
    private Long id;
    @Column(name = "officer_id", nullable = false, length = 20)
    private Long officer;
    @Column(name = "certificate_id", nullable = false, length = 20)
    private Long certificate;
    @Column(name = "certificate_number", nullable = false, length = 200)
    private String numberCert;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "certificate_issue_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date dateCert;
    @Column(name = "certificate_issue_place")
    private String placeCert;
    @Column(name = "status", nullable = false, length = 1)
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date modifiedDate;
    @Transient
    private String certificateName;

    public CertificateOfficers(Long id, Long officer, Long certificate, String numberCert, Date dateCert, String placeCert, int status, Date createDate, Date modifiedDate, String certificateName) {
        this.id = id;
        this.officer = officer;
        this.certificate = certificate;
        this.numberCert = numberCert;
        this.dateCert = dateCert;
        this.placeCert = placeCert;
        this.status = status;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.certificateName = certificateName;
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
     * @return the officer
     */
    public Long getOfficer() {
        return officer;
    }

    /**
     * @param officer the officer to set
     */
    public void setOfficer(Long officer) {
        this.officer = officer;
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

    /**
     * @return the numberCert
     */
    public String getNumberCert() {
        return numberCert;
    }

    /**
     * @param numberCert the numberCert to set
     */
    public void setNumberCert(String numberCert) {
        this.numberCert = numberCert;
    }

    /**
     * @return the dateCert
     */
    public Date getDateCert() {
        return dateCert;
    }

    /**
     * @param dateCert the dateCert to set
     */
    public void setDateCert(Date dateCert) {
        this.dateCert = dateCert;
    }

    /**
     * @return the placeCert
     */
    public String getPlaceCert() {
        return placeCert;
    }

    /**
     * @param placeCert the placeCert to set
     */
    public void setPlaceCert(String placeCert) {
        this.placeCert = placeCert;
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

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

}
