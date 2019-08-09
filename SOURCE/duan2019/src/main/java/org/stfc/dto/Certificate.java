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
import javax.persistence.Transient;

/**
 * @author viett
 *
 */
@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue
    @Column(name = "certificate_id", nullable = false, length = 20, unique = true)
    private Long id;
    @Column(name = "certificate_name", nullable = false, length = 200)
    private String certificateName;
    @Column(name = "certificate_type", nullable = false, length = 60)
    private String type;
    @Column(name = "status", nullable = false, length = 1)
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date modifiedDate;

    @Transient
    private int learned;

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
     * @return the certificateName
     */
    public String getCertificateName() {
        return certificateName;
    }

    /**
     * @param certificateName the certificateName to set
     */
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }

}
