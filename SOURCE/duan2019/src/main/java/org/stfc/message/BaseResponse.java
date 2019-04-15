/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.utils.FormatMessage;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author viettx
 */

public class BaseResponse {

	private static final Logger mLog = LoggerFactory.getLogger(BaseResponse.class);
	@SerializedName("code")
	private String code;
	@SerializedName("desc")
	private String desc;
	@SerializedName("total")
	private Integer total;
	@SerializedName("rows")
	private Object data;
	private transient String lang;

	public static BaseResponse parse(String inputMsg, FormatMessage formatMessage) {
		String[] arr = inputMsg.split("\\-");
		if (arr == null) {
			mLog.info("wrong format errorcode: {}", inputMsg);
			return null;
		}
		BaseResponse res = new BaseResponse();
		res.setCode(arr[arr.length - 1]); // it 's normal
		res.setDesc(formatMessage.getMessage(inputMsg));
		return res;
	}

	public static BaseResponse parse(String inputMsg, FormatMessage formatMessage, String language) {
		String[] arr = inputMsg.split("\\-");
		if (arr == null) {
			mLog.info("wrong format errorcode: {}", inputMsg);
			return null;
		}
		BaseResponse res = new BaseResponse();
		res.setCode(arr[arr.length - 1]); // it 's normal
		res.setDesc(formatMessage.getMessage(inputMsg, language));
		return res;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
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
