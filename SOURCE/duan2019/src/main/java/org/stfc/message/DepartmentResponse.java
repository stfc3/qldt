/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.dto.Departments;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class DepartmentResponse {
	@SerializedName("list_department")
	private List<Departments> listDept;

	/**
	 * @return the listDept
	 */
	public List<Departments> getListDept() {
		return listDept;
	}

	/**
	 * @param listDept the listDept to set
	 */
	public void setListDept(List<Departments> listDept) {
		this.listDept = listDept;
	}

}
