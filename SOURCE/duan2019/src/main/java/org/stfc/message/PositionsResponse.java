/**
 * 
 */
package org.stfc.message;

import java.util.List;

import org.stfc.dto.Positions;

import com.google.gson.annotations.SerializedName;

/**
 * @author viett
 *
 */
public class PositionsResponse {
	@SerializedName("list_positions")
	private List<Positions> listPos;

	/**
	 * @return the listPos
	 */
	public List<Positions> getListPos() {
		return listPos;
	}

	/**
	 * @param listPos the listPos to set
	 */
	public void setListPos(List<Positions> listPos) {
		this.listPos = listPos;
	}

}
