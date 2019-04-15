/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.entity.LecturersEntity;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class LecturersResponse {
	@SerializedName("list_lecturers")
	private List<LecturersEntity> listLecturers;

	public List<LecturersEntity> getListLecturers() {
		return listLecturers;
	}

	public void setListLecturers(List<LecturersEntity> listLecturers) {
		this.listLecturers = listLecturers;
	}

}
