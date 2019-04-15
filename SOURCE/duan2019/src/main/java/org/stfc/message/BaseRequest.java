/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.message;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author viettx
 *
 */
public class BaseRequest {


	@SerializedName("data")
	private JsonElement data;
	@SerializedName("lang")
	private String lang = "vi";

	

	/**
	 * @return the data
	 */
	public JsonElement getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(JsonElement data) {
		this.data = data;
	}

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



}
