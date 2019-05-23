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
@Table(name = "department_position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartmentPosition.findAll", query = "SELECT d FROM DepartmentPosition d")})
public class DepartmentPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "department_position_id")
    private Long departmentPositionId;
    @Column(name = "department_id")
    private BigInteger departmentId;
    @Column(name = "position_id")
    private BigInteger positionId;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public DepartmentPosition() {
    }

    public DepartmentPosition(Long departmentPositionId) {
        this.departmentPositionId = departmentPositionId;
    }

    public Long getDepartmentPositionId() {
        return departmentPositionId;
    }

    public void setDepartmentPositionId(Long departmentPositionId) {
        this.departmentPositionId = departmentPositionId;
    }

    public BigInteger getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(BigInteger departmentId) {
        this.departmentId = departmentId;
    }

    public BigInteger getPositionId() {
        return positionId;
    }

    public void setPositionId(BigInteger positionId) {
        this.positionId = positionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departmentPositionId != null ? departmentPositionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartmentPosition)) {
            return false;
        }
        DepartmentPosition other = (DepartmentPosition) object;
        if ((this.departmentPositionId == null && other.departmentPositionId != null) || (this.departmentPositionId != null && !this.departmentPositionId.equals(other.departmentPositionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.stfc.dto.DepartmentPosition[ departmentPositionId=" + departmentPositionId + " ]";
    }
    
}
