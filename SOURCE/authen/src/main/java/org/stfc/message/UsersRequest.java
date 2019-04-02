/**
 * 
 */
package org.stfc.message;

import com.google.gson.annotations.SerializedName;

/**
 * @author dongdv
 *
 */
public class UsersRequest {
	@SerializedName("lang")
	private String lang;
	@SerializedName("timestamp")
	private Long timestamp;
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
