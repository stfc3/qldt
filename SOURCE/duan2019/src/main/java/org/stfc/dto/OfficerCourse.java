/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dongdv
 */
@Entity
@Table(name = "officer_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OfficerCourse.findAll", query = "SELECT o FROM OfficerCourse o")})
public class OfficerCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "officer_course_id")
    private Long officerCourseId;
    @Column(name = "officer_id")
    private BigInteger officerId;
    @Column(name = "course_id")
    private BigInteger courseId;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public OfficerCourse() {
    }

    public OfficerCourse(Long officerCourseId) {
        this.officerCourseId = officerCourseId;
    }

    public Long getOfficerCourseId() {
        return officerCourseId;
    }

    public void setOfficerCourseId(Long officerCourseId) {
        this.officerCourseId = officerCourseId;
    }

    public BigInteger getOfficerId() {
        return officerId;
    }

    public void setOfficerId(BigInteger officerId) {
        this.officerId = officerId;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officerCourseId != null ? officerCourseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfficerCourse)) {
            return false;
        }
        OfficerCourse other = (OfficerCourse) object;
        if ((this.officerCourseId == null && other.officerCourseId != null) || (this.officerCourseId != null && !this.officerCourseId.equals(other.officerCourseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.stfc.dto.OfficerCourse[ officerCourseId=" + officerCourseId + " ]";
    }
    
}
