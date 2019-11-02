/**
 *
 */
package org.stfc.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author dongdv
 *
 */
public class DepartmentsView implements Serializable {

    private String departmentName;
    private String status;


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DepartmentsView(String departmentName, String status) {
        this.departmentName = departmentName;
        this.status = status;
    }

    

}
