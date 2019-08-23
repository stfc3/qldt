/**
 *
 */
package org.stfc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.stfc.utils.Constants;

/**
 * @author dongdv
 *
 */
public class CertificateView {

    private String certificateName;
    private String certificateType;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT.DD_MM_YYYY_HH_MM_SS, timezone = Constants.DATE_FORMAT.TIMEZONE_HCM)
    private Date certificateIssueDate;
    private String certificateIssuePlace;
    private String certificateNumber;
    private int learned;

    public CertificateView(String certificateName, String certificateType, int status, int learned) {
        this.certificateName = certificateName;
        this.certificateType = certificateType;
        this.status = status;
        this.learned = learned;
    }

    public CertificateView(String certificateName, String certificateType, int status, int learned, Date certificateIssueDate, String certificateIssuePlace, String certificateNumber) {
        this.certificateName = certificateName;
        this.certificateType = certificateType;
        this.status = status;
        this.certificateIssueDate = certificateIssueDate;
        this.certificateIssuePlace = certificateIssuePlace;
        this.certificateNumber = certificateNumber;
        this.learned = learned;
    }

    
    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCertificateIssueDate() {
        return certificateIssueDate;
    }

    public void setCertificateIssueDate(Date certificateIssueDate) {
        this.certificateIssueDate = certificateIssueDate;
    }

    public String getCertificateIssuePlace() {
        return certificateIssuePlace;
    }

    public void setCertificateIssuePlace(String certificateIssuePlace) {
        this.certificateIssuePlace = certificateIssuePlace;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }

}
