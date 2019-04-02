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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dongdv
 */
@Entity
@Table(name = "role_object")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleObject.findAll", query = "SELECT r FROM RoleObject r")})
public class RoleObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_object_id")
    private Long roleObjectId;
    @Column(name = "role_id")
    private BigInteger roleId;
    @Column(name = "object_id")
    private BigInteger objectId;
    @Column(name = "status")
    private Integer status;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public RoleObject() {
    }

    public RoleObject(Long roleObjectId) {
        this.roleObjectId = roleObjectId;
    }

    public Long getRoleObjectId() {
        return roleObjectId;
    }

    public void setRoleObjectId(Long roleObjectId) {
        this.roleObjectId = roleObjectId;
    }

    public BigInteger getRoleId() {
        return roleId;
    }

    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }

    public BigInteger getObjectId() {
        return objectId;
    }

    public void setObjectId(BigInteger objectId) {
        this.objectId = objectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (roleObjectId != null ? roleObjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleObject)) {
            return false;
        }
        RoleObject other = (RoleObject) object;
        if ((this.roleObjectId == null && other.roleObjectId != null) || (this.roleObjectId != null && !this.roleObjectId.equals(other.roleObjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.stfc.dto.RoleObject[ roleObjectId=" + roleObjectId + " ]";
    }
    
}
